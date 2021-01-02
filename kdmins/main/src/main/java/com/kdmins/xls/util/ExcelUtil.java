package com.kdmins.xls.util;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import com.kdmins.xls.inter.dealColData;
import com.kdmins.xls.inter.dealRowData;
import com.kdmins.xls.model.ReadResultModel;
import com.kdmins.xls.model.XlsColHeadConfigModel;
import com.kdmins.xls.model.XlsConfig;
import com.kdmins.xls.annotation.XlsClassInfo;
import com.kdmins.xls.annotation.XlsColInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
public class ExcelUtil {
    private final static String BASE_PACKAGE = "com.kdmins.model";
    private final static  String RESOURCE_PATTERN = "/**/*.class";
    private static Log log = LogFactory.getLog(ExcelUtil.class);
    private static  Map<String, XlsConfig> concurrentHashMap=new ConcurrentHashMap<String, XlsConfig>();
    public static void test() {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(BASE_PACKAGE) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = readerfactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                //判断是否有指定主解
                XlsClassInfo anno = clazz.getAnnotation(XlsClassInfo.class);
                if (anno != null) {
                    XlsConfig handlerMap = new XlsConfig(anno.name(),excelNum2Digit(anno.startCol()),excelNum2Digit(anno.endCol()),anno.dataStartRow(),anno.tableHeadRow());
                    readXlsCol(handlerMap,clazz);
                    concurrentHashMap.put(clazz.getName(),handlerMap);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }
    public static void readXlsCol(XlsConfig handlerMap, Class target){
        Field[] fields = target.getDeclaredFields();
        Map<Integer, XlsColHeadConfigModel> list = new HashMap<>();
        for(Field filed : fields){
            XlsColInfo xlsColInfo= filed.getAnnotation(XlsColInfo.class);
            if(xlsColInfo != null){
                list.put(xlsColInfo.sort(),new XlsColHeadConfigModel(xlsColInfo.label(),filed.getName(),xlsColInfo.sort(),xlsColInfo.verify(),xlsColInfo.blank(),filed.getType())) ;
            }
        }
        handlerMap.setXlsColHeadConfig(list);
    }
    public static ReadResultModel readTable(XSSFSheet sheet, Class dataClass, dealRowData<?> dealRowData,dealColData<?> dealColData){
        ReadResultModel result=new ReadResultModel();
        result.setMessage("");
        XlsConfig config = concurrentHashMap.get(dataClass.getName());
        XSSFRow headRow = sheet.getRow(config.getTableHeadRow());
/*
        String[] head=readHead(config.getXlsColHeadConfig(),headRow,config.getStartCol(),config.getEndCol());
*/
        readBody(result,config.getXlsColHeadConfig(), sheet, config.getDataStartRow(),  config.getStartCol(),config.getEndCol(), dataClass, dealRowData,dealColData);
        return result;
    }
    public static String[]  readHead(Map<Integer, XlsColHeadConfigModel> config, XSSFRow headRow, int startColNum, int endColNum){
        String[] head=new String[endColNum-startColNum+1];
        for(int i=startColNum;i<=endColNum;i++){
            head[i-startColNum]= headRow.getCell(i).getStringCellValue();
        }
        return head;
    }
    public static void setValue(Object object,Object value,String fieldName){
        Class<? extends Object> objectClass = object.getClass();
        try {
          Field  field=  objectClass.getDeclaredField(fieldName);
          field.setAccessible(true);
          field.set(object,value);
        } catch (NoSuchFieldException  | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void setFieldValue(int col, int row, ReadResultModel result, XlsColHeadConfigModel xlsColHeadConfigModel, Object target, Object value, dealColData dealColData){
        if(dealColData != null){
          value = dealColData.dealData(value,row,col);
        }
        String message= result.getMessage();
        if(xlsColHeadConfigModel.isBlank()){
            if(value==null || "".equals(value) || value=="null"){
                message=message+"第"+row+"行"+xlsColHeadConfigModel.getLabel()+"不能为空";
                result.setMessage(message);
            }
        }
        setValue(target,value,xlsColHeadConfigModel.getProperty());
    }
    public static void readBody( ReadResultModel result,Map<Integer, XlsColHeadConfigModel> config, XSSFSheet sheet, int tableStartRow, int startColNum, int endColNum, Class dataClass, dealRowData dealRowData,dealColData<?> dealColData) {
        sheet.getMergedRegions();
        List<Object> list= new ArrayList<>();
        while (true) {
            XSSFRow row = sheet.getRow(tableStartRow-1);
            if (row == null) {
                break;
            }
            boolean flag = false;
            Object t= null;
            try {
                t = dataClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (int i = startColNum; i <= endColNum; i++) {
               XlsColHeadConfigModel headConfig = config.get(i - startColNum + 1);
               Object value = null;
               XSSFCell cell = row.getCell(i);

               if(cell != null){
                   CellType cellType = cell.getCellType();
                   if(!headConfig.isVerity()){
                       if(cellType==CellType.STRING){
                               setFieldValue(i,tableStartRow, result,headConfig,t,cell.getStringCellValue(),dealColData);

                       }
                       if(cellType==CellType.NUMERIC){
                          if(headConfig.getFieldType()==String.class){
                               setFieldValue(i,tableStartRow,result,headConfig,t,cell.getNumericCellValue()+"",dealColData);
                          }
                           if(headConfig.getFieldType()==Date.class){
                               setFieldValue(i,tableStartRow,result,headConfig,t,cell.getDateCellValue(),dealColData);
                           }
                       }
                       if(cellType==CellType.BLANK){
                               setFieldValue(i,tableStartRow,result,headConfig,t,null,dealColData);
                       }
                   }
               }else{
                   setFieldValue(i,tableStartRow, result,headConfig,t,null,dealColData);
               }
            }
            if(dealRowData != null){
                list.add(dealRowData.dealData(t,tableStartRow));
            }else{
                list.add(t);
            }

            tableStartRow++;
        }
        result.setData(list);

    }
    XSSFSheet getSheet(XSSFWorkbook workbook,int index){
        return workbook.getSheetAt(index);
    }
    public static int excelNum2Digit(String excelNum) {
        char[] chs = excelNum.toCharArray();
        int digit = 0;
        for (char ch : chs) {
            digit = digit * 26 + (ch - 'A' + 1);
        }
        return digit-1;
    }

}