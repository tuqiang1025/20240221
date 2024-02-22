package combat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.ArrayList;

public class GetTestExcelWorkBook {
    private static  String fileName;
    private static String fileFgf= File.separator;/*自适应当前系统的文件分隔符*/

    public static ArrayList<String> getExcelFile() {
        //获取当前项目的路径
        String projectPath = System.getProperty("user.dir");
        //拼接测试excel路径（这样文件对象获取文件路径时才是相对文件）
        String excelTestPath = projectPath + fileFgf+"src"+fileFgf+"main"+fileFgf+"java"+fileFgf+"testdata";
        //创建文件对象,得到文件清单集合
        File file = new File(excelTestPath);
        ArrayList<String> arrayList = getStrings(file, excelTestPath);
        return arrayList;
    }
    //筛选出excel
    private static ArrayList<String> getStrings(File file, String excelTestPath) {
        File[] files = file.listFiles();
        //通过循环找到是xls类型的文件,创建局部变量接收数据
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            //获取文件名称
            fileName = files[i].getName();
            //通过循环筛选出.xls后缀的文件
            if (files[i].isFile() && fileName.endsWith(".xls")) {
                //把拼接后的文件路径+文件名给list集合
                arrayList.add(excelTestPath + fileFgf + fileName);
            }
        }
        return arrayList;
    }
    public static ArrayList<Workbook> getWorkBook() throws IOException {
        //通过getExcelFile方法获取到多个文件名
        ArrayList<String> excelFileList = getExcelFile();
        //设置workbook的集合，用来存储得到workbook集合
        ArrayList<Workbook> workbookList = new ArrayList<>(getExcelFile().size());
        for (int i = 0; i < excelFileList.size(); i++) {
            FileInputStream fileInputStream = new FileInputStream(excelFileList.get(i));
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            workbookList.add(i, workbook);
            workbook.close();
            fileInputStream.close();
        }
        return workbookList;
    }
}

