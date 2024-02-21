package combat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class WorkBookUpdate {
    public static void setWorkbookData(ArrayList<String[]> arrayList) throws IOException {
        String[] headList={"测试编号","测试人员","测试用例名称","请求url","请求方法","请求Body","请求Cookie","预期响应码",
                "预期响应体","实际响应码","实际响应体","其他报错","前置sql","sql执行结果","测试结果"};
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet=workbook.createSheet("测试结果合集");
        Row rowHead=sheet.createRow(0);
        //设置首行高度
        rowHead.setHeightInPoints(20);
        //创建cell样式
        CellStyle cellStyle1=workbook.createCellStyle();
        CellStyle cellStyle2=workbook.createCellStyle();
        //自动换行、垂直、水平
        cellStyle2.setWrapText(true);
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
/*
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
*/
        //创建字体样式对象
        Font font=workbook.createFont();
        //设置字体大小、样式
        font.setFontHeightInPoints((short) 15);
        font.setFontName("宋体");
        //把字体对象给cell样式对象
        cellStyle1.setFont(font);
        //创建表头
        for (int i = 0; i < headList.length; i++) {
            Cell cellHead = rowHead.createCell(i);
            cellHead.setCellValue(headList[i]);
            sheet.setColumnWidth(i, 15*256);
            cellHead.setCellStyle(cellStyle1);
        }
        sheet.setColumnWidth(10, 30*256);
        sheet.setColumnWidth(11, 30*256);
        //写入表内容
        for(int i=1;i<=arrayList.size();i++){
            Row rowBody=sheet.createRow(i);
            for(int j=0;j<14;j++){
                Cell cellBody=rowBody.createCell(j);
                cellBody.setCellValue(arrayList.get(i-1)[j]);
                cellBody.setCellStyle(cellStyle2);
            }
            if(arrayList.get(i-1)[9].equals(arrayList.get(i-1)[7]) && arrayList.get(i-1)[10].contains(arrayList.get(i-1)[8])){
                Cell cellBody2=rowBody.createCell(14);
                cellBody2.setCellValue("通过");
                cellBody2.setCellStyle(cellStyle2);
            }
            else {
                Cell cellBody2=rowBody.createCell(14);
                cellBody2.setCellValue("失败");
                cellBody2.setCellStyle(cellStyle2);
            }
        }
        //测试输出文件通过时间戳命名
        String time_str;
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        time_str=simpleDateFormat.format(date);
        FileOutputStream fileOutputStream=new FileOutputStream("测试结果"+time_str+"output.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }
}
