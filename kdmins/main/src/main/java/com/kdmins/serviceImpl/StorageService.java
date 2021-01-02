/*
package com.kdmins.serviceImpl;

import com.kdmins.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * @author ：lsy
 * @date ：Created in 2019/12/9 17:43
 * @modified By：
 *//*

@Service
public class StorageService {

    @Autowired
    StorageMapper storageMapper;

    @Autowired
    StorageService storageService;



    //获取存储过程里的参数
    public List getStorageParam(String storageName) {
        List<LinkedHashMap<String, Object>> list = storageMapper.queryStorage(storageName);
        List resultList = new ArrayList();
        int c = 1;
        for (int i = 0; i < list.size(); i++) {
            Map resultMap = new LinkedHashMap();

            Map hashMap = (Map) list.get(i);
            //System.out.println("1111"+hashMap);
            String string = hashMap.toString();
            //寻找 带有In 或者 in 的行
            if (string.indexOf(" In ") != -1 || string.indexOf(" in ") != -1 || string.indexOf("out") != -1 || string.indexOf("Out") != -1) {
                resultMap.put("index", c);
                c++;
                //resultList.add(resultMap);
                //寻找带(的第一行的
                if (string.indexOf("{TEXT=(") != -1) {
                    String[] split = string.split("\\{");
                    String[] s11 = split[1].split("\\(");
                    String s111 = s11[1];
                    String substring = s111.substring(0, s111.indexOf(" "));
                    resultMap.put("parname", substring.toUpperCase());
                    //System.out.println(substring);
                    //寻找第一行以后的
                } else if (string.indexOf("{TEXT=") != -1) {
                    String[] split = string.split("\\{");
                    String[] split1 = split[1].split("TEXT=");
                    String s111 = split1[1];
                    String substring = s111.substring(0, s111.indexOf(" "));
                    resultMap.put("parname", substring.toUpperCase());
                    //System.out.println(substring);
                }
                if (string.indexOf(".") != -1) {
                    int tablesIndex = string.lastIndexOf(" ") + 1;
                    int tablesEnd = string.indexOf(".");
                    int paramIndex = string.indexOf(".") + 1;
                    int paramEnd = string.indexOf("%");
                    String substring = string.substring(paramIndex, paramEnd).toUpperCase();
                    resultMap.put("column", substring.toUpperCase());
                    //获取到的表名
                    String substringTables = string.substring(tablesIndex, tablesEnd);
                    resultMap.put("tables", substringTables.toUpperCase());
                    //System.out.println("获取到的表名"+substringTables);
                }
                if (string.indexOf(" In ") != -1 || string.indexOf(" in ") != -1 || string.indexOf("out") != -1 || string.indexOf("Out") != -1) {
                    int index = string.indexOf(" ");
                    int end = string.lastIndexOf(" ");
                    String substring = string.substring(index, end).trim();
                    resultMap.put("type", substring.toUpperCase());
                }
                resultList.add(resultMap);
            }
        }
        return resultList;
    }



    */
/**
     * //用存储过程的集合 和 本地表的列名比对 一致则加入本地列名的类型 (获取字段类型)
     * @param tableName    本地库表名
     * @param storageName  存储过程名称
     * @return
     *//*

    */
/*public List getParamType(String tableName,String storageName) {
        List resultList = storageMapper.getStorageParam(storageName);
        for (int i = 0; i < resultList.size(); i++) {
            LinkedHashMap map1 = (LinkedHashMap) resultList.get(i);
            Object column = map1.get("column");
            String tables = map1.get("tables").toString().toUpperCase();
            LinkedHashMap randomSqlMap = storageMapper.getTablesParamAndType(tableName, map1);
            Object column_name = randomSqlMap.get("COLUMN_NAME");
            Object data_type = randomSqlMap.get("DATA_TYPE");
            if (column.equals(column_name)) {
                map1.put("par_type", data_type);
            }
        }
        return resultList;
    }*//*





    */
/**
     * 生成mybatis调用的存储过程的sql语句
     * @param tableName   表名
     * @param storageName 存储过程名
     * @return
     *//*

    public String getFinalSql(String tableName,String storageName) {
        //用存储过程的集合 和 本地表的列名比对 一致则加入本地列名的类型 (添加获取到的字段类型)
        List resultList = storageService.getStorageParam(storageName);
        for (int i = 0; i < resultList.size(); i++) {
            LinkedHashMap map1 = (LinkedHashMap) resultList.get(i);
            Object column = map1.get("column");
            String tables = map1.get("tables").toString().toUpperCase();
            LinkedHashMap randomSqlMap = storageMapper.getTablesParamAndType(tableName, map1);
            Object column_name = randomSqlMap.get("COLUMN_NAME");
            Object data_type = randomSqlMap.get("DATA_TYPE");
            if (column.equals(column_name)) {
                map1.put("par_type", data_type);
            }
        }
        //传入存储过程名称
        String tables = storageName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{ call "+tables+" (");
        //System.out.println(stringBuffer);
        int w = 1;
        for (int i = 0; i <resultList.size() ; i++) {
            if(w==1){
                Map map = (Map) resultList.get(i);
                String parname = (String) map.get("column");
                String type = (String) map.get("type");
                String par_type = (String) map.get("par_type");
                stringBuffer.append("#{"+parname+",mode="+type+",jdbcType="+par_type+"}");
                w++;
            }else {
                Map map = (Map) resultList.get(i);
                String parname = (String) map.get("column");
                String type = (String) map.get("type");
                String par_type = (String) map.get("par_type");
                stringBuffer.append(",\n#{"+parname+",mode="+type+",jdbcType="+par_type+"}");
                w++;
            }
        }
        stringBuffer.append(")}");
       // System.out.println("最终sql: "+stringBuffer);
        return stringBuffer.toString();
    }

    */
/**
     * 生成存储过程 (根据表名)
     * @return
     *//*

    public String getStorage(String tableName){
        List<LinkedHashMap<String, Object>> sys_beds = storageMapper.getTablesAllParamAndType(tableName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE OR REPLACE \n" +
                "procedure "+tableName+" \n" +
                "(\n");
        for (int i = 0; i <sys_beds.size() ; i++) {
            if (i!=sys_beds.size()-1){
                LinkedHashMap<String, Object> hashMap = sys_beds.get(i);
                String column_name = hashMap.get("COLUMN_NAME").toString();
                stringBuffer.append(column_name+"_in   in   "+tableName+"."+column_name+"%Type,\n");
            }else {
                LinkedHashMap<String, Object> hashMap = sys_beds.get(i);
                String column_name = hashMap.get("COLUMN_NAME").toString();
                stringBuffer.append(column_name+"_in   in   "+tableName+"."+column_name+"%Type\n");
            }
        }
        stringBuffer.append(")" +
                "is\n" +
                "begin\n" +
                "  update  "+tableName+"  set\n");
        for (int i = 0; i <sys_beds.size() ; i++) {
            if (i!=sys_beds.size()-1){
                LinkedHashMap<String, Object> hashMap = sys_beds.get(i);
                String column_name = hashMap.get("COLUMN_NAME").toString();
                stringBuffer.append(column_name+"="+column_name+"_in,\n");
            }else{
                LinkedHashMap<String, Object> hashMap = sys_beds.get(i);
                String column_name = hashMap.get("COLUMN_NAME").toString();
                stringBuffer.append(column_name+"="+column_name+"_in\n");
            }

        }
        stringBuffer.append(" where ID=ID_in ;\n"+
                "  If SQL%ROWCOUNT =0 Then\n" +
                "     Insert Into "+tableName+" (\n"  );
        for (int i = 0; i <sys_beds.size() ; i++) {
            if (i!=sys_beds.size()-1){
                LinkedHashMap<String, Object> hashMap = sys_beds.get(i);
                String column_name = hashMap.get("COLUMN_NAME").toString();
                stringBuffer.append(column_name+",\n");
            }else {
                LinkedHashMap<String, Object> hashMap = sys_beds.get(i);
                String column_name = hashMap.get("COLUMN_NAME").toString();
                stringBuffer.append(column_name+"\n");
            }

        }
        stringBuffer.append(")\n" +
                "     Values (");
        for (int i = 0; i <sys_beds.size() ; i++) {
            if (i!=sys_beds.size()-1){
                LinkedHashMap<String, Object> hashMap = sys_beds.get(i);
                String column_name = hashMap.get("COLUMN_NAME").toString();
                stringBuffer.append(column_name+"_in,\n");
            }else {
                LinkedHashMap<String, Object> hashMap = sys_beds.get(i);
                String column_name = hashMap.get("COLUMN_NAME").toString();
                stringBuffer.append(column_name+"_in\n");
            }

        }
        stringBuffer.append(");\n" +
                "End If;\n" +
                "\n" +
                "  Exception\n" +
                "  When Others Then\n" +
                "    hlpt_ErrorCenter(SQLCode, SQLErrM);\n" +
                "End "+tableName+";");

       // System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
    }

}
*/
