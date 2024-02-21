package utils;


import java.util.Locale;
import java.util.ResourceBundle;


public class ConfigFile {
    //用于读取application.properties配置文件

    public static String getData(String data){

        //创建resourceBundle对象，用于读取resource文件下的application文件
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);

        if(data.equals("mysqlDriver")){
            return resourceBundle.getString("mysqlDriver");
        }
        if(data.equals("mysqlConnectUrl")){
            return resourceBundle.getString("mysqlConnectUrl");
        }
        if(data.equals("user")){
            return resourceBundle.getString("user");
        }
        if(data.equals("password")){
            return resourceBundle.getString("password");
        }
        return null;
    }
}
