package com.kdmins.xls;

import com.kdmins.model.Person;
import com.kdmins.xls.model.ReadResultModel;
import com.kdmins.xls.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class XlsTest {
    public static void main(String[] args) throws IOException {
        ExcelUtil.test();
        FileInputStream fis = new FileInputStream("C:\\Users\\cool\\Desktop\\123.xlsx");
        // 将输入流转换为工作簿对象
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // 获取第一个工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        ReadResultModel data = ExcelUtil.readTable(sheet, Person.class,null,(Object a, int rowNo,int colNo) -> {
            System.out.println("第" + rowNo + "行第"+colNo+"列");
            System.out.println(a);
            return a;
        });
        System.out.println(data);
       /* */
    }
}
