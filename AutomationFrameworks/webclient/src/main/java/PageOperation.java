import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageOperation {
    private  String username="admin"; //用户名
    private String password="Tu123456";  //密码
    private String projectname="项目名称20";  //项目名称
    private boolean passresualt;   //断言结果



    public String project(WebDriver webDriver)  {
        try {
            //打开网站
            webDriver.get("http://127.0.0.1:81/zentao/user-login.html");
            //基本元素定位 1、ID 2、name 3、tagname 4、classname 5、linkText 6、partialLinkText
            //登录页面进行输入用户名和密码，并点击登录按钮
            webDriver.findElement(By.name("account")).sendKeys(username);
            webDriver.findElement(By.name("password")).sendKeys(password);
            webDriver.findElement(By.id("submit")).click();
            Thread.sleep(2000);

            //放大浏览器最大化
            webDriver.manage().window().maximize();
            Thread.sleep(2000);




//        //点击进入项目-创建项目
            webDriver.findElement(By.xpath("//*[@id=\"menuMainNav\"]/li[4]/a/span")).click();
            Thread.sleep(2000);
//        //因为页面是被嵌套，所以需要切换到iframe页面上才能找到元素 ，做完操作后，对后续流程看是否需要重新切换到原来默认的页面
//        //否则也会出现找不到元素      webDriver.switchTo().defaultContent();
//        //下面这个是进入到 iframe name="app-project" 的页面
            webDriver.switchTo().frame("app-project");
//
            webDriver.findElement(By.xpath("//*[@id=\"mainMenu\"]/div[3]/a[2]")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//*[@id=\"guideDialog\"]/div/div/div/div[2]/div/a/img")).click();

            webDriver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(projectname);

            webDriver.findElement(By.xpath("//*[@id=\"dataform\"]/table/tbody/tr[3]/td/label[2]")).click();
            webDriver.findElement(By.xpath("//*[@id=\"PM_chosen\"]/a/span\n")).click();
            webDriver.findElement(By.xpath("//*[@id=\"PM_chosen\"]/div/ul/li[2]")).click();
            webDriver.findElement(By.xpath("//*[@id=\"budget\"]")).sendKeys("20000");
            webDriver.findElement(By.xpath("//*[@id=\"budgetUnit\"]\n")).click();
            webDriver.findElement(By.xpath("//*[@id=\"budgetUnit\"]/option[2]\n")).click();
            webDriver.findElement(By.xpath("//*[@id=\"end\"]")).click();
            webDriver.findElement(By.xpath("//*[@id=\"end\"]")).sendKeys("2050-01-01");
//        //iframe标签里没有name和id 可以用父级定位参考文档 https://blog.csdn.net/m0_60008263/article/details/129123848
            webDriver.switchTo().frame(webDriver.findElement(By.cssSelector("#dataform > table > tbody > tr:nth-child(10) > td > div > div.ke-edit > iframe")));

            webDriver.findElement(By.xpath("/html/body")).sendKeys("2050-01-01");
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame("app-project");
            webDriver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
            Thread.sleep(6000);

            webDriver.findElement(By.xpath("//*[@id=\"begin0\"]")).sendKeys("2023-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"end0\"]")).sendKeys("2025-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"begin1\"]")).sendKeys("2023-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"end1\"]")).sendKeys("2025-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"begin2\"]")).sendKeys("2023-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"end2\"]")).sendKeys("2025-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"begin3\"]")).sendKeys("2023-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"end3\"]")).sendKeys("2025-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"begin4\"]")).sendKeys("2023-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"end4\"]")).sendKeys("2025-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"begin5\"]")).sendKeys("2023-10-27");
            webDriver.findElement(By.xpath("//*[@id=\"end5\"]")).sendKeys("2025-10-27");

            webDriver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
            Thread.sleep(3000);
            //进行断言（获取页面上的文本结果）

            WebElement result=webDriver.findElement(By.xpath("//*[@id=\"currentItem\"]/span[1]"));
            passresualt=result.getText().equals(projectname);

            if(passresualt){
                System.out.println("恭喜测试通过");
            }
            return "测试执行成功";

        }catch(NoSuchElementException noSuchElementException){
            System.out.println("失败报错了");
            return "测试执行失败";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
//        断言的三种形式：assert 、verify、waitfor
//        Assert 失败时，该测试将终止。
//        Verify 失败时，该测试将继续执行，并将错误记入日显示屏 。也就是说允许此单个 验证通过。确保应用程序在正确的页面上。
//        Waitfor用于等待某些条件变为真。可用于AJAX应用程序的测试。
//        如果该条件为真，他们将立即成功执行。如果该条件不为真，则将失败并暂停测试。直到超过当前所设定的超时时间。 一般跟setTimeout时间一起用