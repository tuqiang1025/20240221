import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Test
public class Le02 {
    public  AndroidDriver<WebElement> androidDriver;
    public void first() throws MalformedURLException {
        //创建配置对象
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        //添加配置 deviceName测试设备
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");
        //设备类型 android和ios
        desiredCapabilities.setCapability("platformName","Android");
        //测试包名
        desiredCapabilities.setCapability("appPackage","com.mhh.daytimeplay");
        //测试app启动入口
        desiredCapabilities.setCapability("appActivity","com.mhh.daytimeplay.Start_Up_Activity.Privacy_activity");
        //创建驱动
        //传入2个参数  1：appium的通讯地址 2：配置对象
        //需要启动appium 服务
        androidDriver=new AndroidDriver<WebElement>(
                new URL("http:127.0.0.1:4723/wd/hub"),desiredCapabilities
        );
    }
    public void second() throws InterruptedException {
        Thread.sleep(2000);

        androidDriver.findElementById("com.mhh.daytimeplay:id/tv_consent").click();
        Thread.sleep(1000);

        //处理安装系统的弹窗（访问手机照片权限）com.android.packageinstaller:id/permission_allow_button
        androidDriver.switchTo().alert().accept(); //找到弹窗并同意
        Thread.sleep(5000);
        androidDriver.findElementById("com.mhh.daytimeplay:id/TIANJIA").click();
        Thread.sleep(2000);
        androidDriver.findElementById("com.mhh.daytimeplay:id/biaoti").sendKeys("哈哈哈");
        androidDriver.findElementByClassName("android.widget.EditText").sendKeys("ddddddddddddd");
        androidDriver.findElementById("com.mhh.daytimeplay:id/baocun").click();
        Thread.sleep(2000);



        //进入主界面


    }
}
