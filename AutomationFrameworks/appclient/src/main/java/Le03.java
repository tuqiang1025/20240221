import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Test
public class Le03 {
    public  AndroidDriver<WebElement> androidDriver;
    public void first() throws MalformedURLException {
        //创建配置对象
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        //添加配置 deviceName测试设备
        desiredCapabilities.setCapability("deviceName","c785c499");
        //设备类型 android和ios
        desiredCapabilities.setCapability("platformName","Android");
        //测试包名
        desiredCapabilities.setCapability("appPackage","com.handsgo.jiakao.android");
        //测试app启动入口
        desiredCapabilities.setCapability("appActivity","cn.mucang.android.jiakao.default");
        //创建驱动
        //传入2个参数  1：appium的通讯地址 2：配置对象
        //需要启动appium 服务
        androidDriver=new AndroidDriver<WebElement>(
                new URL("http:127.0.0.1:4723/wd/hub"),desiredCapabilities
        );
    }
/*    public void second() throws InterruptedException {
        Thread.sleep(2000);

        androidDriver.findElementById("com.handsgo.jiakao.android:id/btn_agree").click();
        Thread.sleep(15000);
        androidDriver.findElementById("com.handsgo.jiakao.android:id/agree").click();
        Thread.sleep(1000);
        WebDriverWait webDriverWait=new W

        //处理安装系统的弹窗（访问手机照片权限）com.android.packageinstaller:id/permission_allow_button
        androidDriver.switchTo().alert().accept(); //找到弹窗并同意
        Thread.sleep(5000);
        *//*androidDriver.findElementById("com.mhh.daytimeplay:id/TIANJIA").click();
        Thread.sleep(2000);
        androidDriver.findElementById("com.mhh.daytimeplay:id/biaoti").sendKeys("哈哈哈");
        androidDriver.findElementByClassName("android.widget.EditText").sendKeys("ddddddddddddd");
        androidDriver.findElementById("com.mhh.daytimeplay:id/baocun").click();
        Thread.sleep(2000);*//*

        //进入主界面

    }*/
}
