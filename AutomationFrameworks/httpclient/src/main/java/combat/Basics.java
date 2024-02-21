package combat;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Basics {
    private Object[] testResult =new Object[4]; //用于接受返回的结果
    public String actualResponseCode;//实际响应码
    public String actualResponseCodeBody;//实际响应参数
    public String testOtherErrors;//其他报错
    public String sqlResult;//sql执行结果

    public ArrayList<String[]> arrayListOutput =new ArrayList<>();



    @DataProvider(name = "a")
    public Object[][] getCellTestData() throws IOException {
        return  GetTestExcelTestData.getCellTestData();
    }
    @Test(dataProvider="a")
    public void a(String testNum,String testPeople,String testName,String testUrl,String testSql,String testMethod,String testContentType,
                  String testRequestBody,String testCookie,String expectedResponseCode,String ExpectedResponseBody)
            throws SQLException, ClassNotFoundException {
        //先对sql进行执行
        if(RunTestSql.getExecuteRow(testSql)!=0){
            sqlResult="sql执行成功";
        }
        else{
            sqlResult="无SQL语句执行，或者sql语句错误";
            System.out.println(sqlResult);
        }
        if (testMethod.equals("get")) {
            testResult = TestMethod.getMethodCall(testUrl);
        } else if (testMethod.equals("post")) {
            testResult = TestMethod.postMethodCall(testUrl, testRequestBody, testContentType, testCookie);
        }
        //把返回的响应内容进行转换String类型并赋值新字段
        actualResponseCode = testResult[0].toString();
        actualResponseCodeBody = testResult[2].toString();
        testOtherErrors=testResult[3].toString();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(expectedResponseCode,actualResponseCode,"(测试员："+testPeople+")-(用例编号："+testNum+")-(用例名称："+ testName+")-响应码不一致");
        softAssert.assertTrue(actualResponseCodeBody.contains(ExpectedResponseBody),"(测试员："+testPeople+")-(用例编号："+testNum+")-(用例名称："+ testName+")-和预期结果不一致");
        /*对响应和测试数据做写入excel*/
        arrayListOutput.add(new String[]{testNum, testPeople,testName,testUrl,testMethod,testRequestBody,testCookie,expectedResponseCode,
                ExpectedResponseBody,actualResponseCode,actualResponseCodeBody,testOtherErrors,testSql,sqlResult});
        softAssert.assertAll();
    }
    @AfterTest
    public void b() throws IOException {
        WorkBookUpdate.setWorkbookData(arrayListOutput);
    }
}

