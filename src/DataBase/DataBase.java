//数据库使用说明:本数据库全部方法为静态,可以用类名直接调用
//主函数启动后调用 DataBase_connect() 方法,结束前调用DataBase_Disconnect()方法
//使用时 直接用DataBase.方法名调用

package DataBase;

import java.sql.*;
import java.util.*;
import entity.Person.*;
public class DataBase
{
    static Statement statement;
    static Connection connection;

     public DataBase()
    {
        //connect();

    }

    public static Connection getConnection() {
        return connection;
    }

    public static void DataBase_Connect(){//连接数据库
         try{
             try{
                 Class.forName( "com.mysql.jdbc.Driver");
                 System.out.println("数据库驱动加载成功");//可以注释掉
             }catch(ClassNotFoundException e){}
             //连接数据库
             connection=DriverManager.getConnection("jdbc:mysql://wangqy.top:3306/university","didong","wangqytop");//域名应该已经能直接用了
             statement=connection.createStatement();
         }catch(Exception e){
             e.printStackTrace();
             System.out.println("连接失败");
         }
    }
    public static void DataBase_Disconnect(){
        try {
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}