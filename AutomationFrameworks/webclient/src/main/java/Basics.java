import org.openqa.selenium.WebDriver;

public class Basics {
    public static void main (String[] args) throws InterruptedException {
        BrowserType browserType=new BrowserType();
        WebDriver webDriver=browserType.chromBrowser("谷歌浏览器");
        PageOperation pageOperation=new PageOperation();
        pageOperation.project(webDriver);
    }
}
