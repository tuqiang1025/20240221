import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
//小笔头app滑动
@Test
public class Le01 {
    public  AndroidDriver<WebElement> androidDriver;
    public void first() throws MalformedURLException {
        //创建配置对象
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        //添加配置 deviceName测试设备
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");
        //设备类型 android和ios
        desiredCapabilities.setCapability("platformName","Android");
        //测试包名
        desiredCapabilities.setCapability("appPackage","com.pen");
        //测试app启动入口
        desiredCapabilities.setCapability("appActivity","io.dcloud.PandoraEntry");
        //创建驱动
        //传入2个参数  1：appium的通讯地址 2：配置对象
        //需要启动appium 服务
        androidDriver=new AndroidDriver<WebElement>(
                new URL("http:127.0.0.1:4723/wd/hub"),desiredCapabilities
        );
    }
    public void second() throws InterruptedException {
        Thread.sleep(2000);

        androidDriver.findElementByClassName("android.webkit.WebView").click();
        androidDriver.findElementByXPath("//android.view.View[@text=\"同意并继续\"]").click();
        Thread.sleep(2000);


        //滑动操作  首先创建TouchAction对象 实例化传参是驱动对象androidDriver
        TouchAction touchAction=new TouchAction(androidDriver);
        //定义屏幕的起始坐标和结束坐标 (把原始的坐标转换成PointOption类型) --如果坐标值超出屏幕可能滑动效果失效
        PointOption startPointOption=PointOption.point(600,300);
        PointOption endPointOption=PointOption.point(200,300);
        //定义waitAction等待时间（把原始时间转换成Duration类型）
        Duration duration=Duration.ofMillis(600);
        //把Duration类型转换waitOptions类型
        WaitOptions waitOptions=WaitOptions.waitOptions(duration);
        //滑动动作定义并执行
        touchAction.press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).release().perform();
        Thread.sleep(1000);
        touchAction.press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).release().perform();
        Thread.sleep(1000);
        touchAction.press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).release().perform();
        Thread.sleep(1000);
        androidDriver.findElementByClassName("android.widget.Button").click();
        Thread.sleep(2000);

        //进入主界面



    }
}
