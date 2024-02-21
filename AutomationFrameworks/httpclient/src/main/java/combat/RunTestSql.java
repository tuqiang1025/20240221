package combat;

import utils.ConfigFile;

import java.sql.*;

public class RunTestSql {

        private static int len;
        public static int getExecuteRow(String sql) throws RuntimeException, SQLException, ClassNotFoundException {
            //Class.forName("com.mysql.cj.jdbc.Driver");作用：加载Mysql驱动类在内存中，这行代码在连接Mysql数据库的时候可以不加，
            // 如果连接其他数据库需要加，因为Mysql特有的可以自动导入驱动类
            Class.forName(ConfigFile.getData("mysqlDriver"));
            String url = ConfigFile.getData("mysqlConnectUrl");

            //创建连接
            Connection connection = DriverManager.getConnection(url,ConfigFile.getData("user"),ConfigFile.getData("password"));
            //2.获取PreparedStatement对象,并把sql语句传入
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //获取返回值，影响的记录数

            try {
                len=preparedStatement.executeUpdate();
            } catch (SQLException e) {
                len =0;
            }
   /*

            while(resultSet.next()) {//一次读取一行数据
                //注意：这里的Object都可以替换为具体的数据类型，我为了演示才用的Object
                Object value1 = resultSet.getObject(1);//数据中表的字段值序号，第1列
                Object value2 = resultSet.getObject(2);//数据中表的字段值序号，第2列
                Object value3 = resultSet.getObject(3);//数据中表的字段值序号，第3列
                Object value4 = resultSet.getObject(4);//数据中表的字段值序号，第3列

                //将获取到的一行的不同字段值依次打印出来看看
                System.out.println(value1 + "\t" + value2 + "\t" + value3+"\t"+value4);
            }*/
            preparedStatement.close();
            connection.close();
            return len;
        }
    }


