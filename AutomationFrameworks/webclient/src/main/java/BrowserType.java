import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserType {
    public WebDriver chromBrowser(String BrowserName){
        if (BrowserName.equals("谷歌浏览器")){
            //找到浏览器驱动，并启动驱动打开浏览器文件
            System.setProperty("webdriver.chrome.driver", "F:\\tu231025\\chromedriver.exe");
        }
        return new ChromeDriver();
    }
}
