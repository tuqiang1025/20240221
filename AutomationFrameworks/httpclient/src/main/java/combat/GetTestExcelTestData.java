package combat;

import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.util.ArrayList;

public class GetTestExcelTestData {
    private static int sheetMax;
    private static Object[][] cellTestDataFromSheet;
    private static Object[][] cellTestDataTotal;
    public static Object[][] getCellTestData() throws IOException {
        //用于接收方法返回的workbook对象集合
        ArrayList<Workbook> workbookList2 = GetTestExcelWorkBook.getWorkBook();
        //用于接收每行的测试数据，每行有8列数据，所以用Object[]数组
        ArrayList<Object[]> testDataList=new ArrayList<>();
        //遍历每个workbook对象
        for (int i = 0; i < workbookList2.size(); i++) {
            Workbook workbook=workbookList2.get(i);
            sheetMax = workbook.getNumberOfSheets();
            //遍历每个sheet页
            for (int k = 0; k < sheetMax; k++) {
                //从sheet第一页开始循环
                Sheet sheet = workbook.getSheetAt(k);
                //获取最大行数
                int rowMAX = sheet.getLastRowNum();
                //用于接收读取到excel的数据，有多少行测试用例，则有rowMAX个数组，8代表取前8列数据
                cellTestDataFromSheet= new String[rowMAX][11];
                //通过循环，遍历每行，从第2行开始
                for (int p = 0; p < rowMAX; p++) {
                    //遍历每个cell读取数据，先通过stringData接收
                    for (int j = 0; j <= 10; j++) {
                        //对是空的表格做特殊处理,并获取前8列的测试数据（因为有些表格数据是空。）
                        Cell cell = sheet.getRow(p+1).getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        cell.setCellType(CellType.STRING);
                        cellTestDataFromSheet[p][j] = cell.getStringCellValue();
                    }
                    testDataList.add(cellTestDataFromSheet[p]);
                }
            }
        }
        //重新定义总数据cellTestDataTotal二维数组的长度
        cellTestDataTotal=new Object[testDataList.size()][11];
        //把testDataList集合的值给cellTestDataTotal，方便方法返回
        for (int i=0;i<cellTestDataTotal.length;i++){
            cellTestDataTotal[i]=testDataList.get(i);
        }
        return cellTestDataTotal;
    }
}
