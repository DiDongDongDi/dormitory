//数据库使用说明:本数据库全部方法为静态,可以用类名直接调用
//主函数启动后调用 DataBase_connect() 方法,结束前调用DataBase_Disconnect()方法
//使用时 直接用DataBase.方法名调用

package DataBase;

import java.sql.*;
import java.util.*;

public class Database
{
    static Statement statement;
    static Connection connection;

     public Database()
    {
        //connect();

    }

    public static void DataBase_Connect(){//连接数据库
         try{
             try{
                 Class.forName( "com.mysql.jdbc.Driver");
                 System.out.println("数据库驱动加载成功");//可以注释掉
             }catch(ClassNotFoundException e){}
             //连接数据库
             connection=DriverManager.getConnection("jdbc:mysql://wangqy.top:3306/words?characterEncoding=utf8","root","");//TODO:域名等信息不详
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
    public static boolean DataBase_AddStudent(Student s){//添加学生 TODO: Student类尚未获得,可能出现错误
         boolean success=true;
         try
         {
             PreparedStatement pstmt = null;
             String sql="INSERT INTO student VALUES('?','?','?')";
             pstmt=connection.prepareStatement(sql);
             pstmt.setString(1,s.number);
             pstmt.setString(2,s.name);
             pstmt.setString(2,s.sex);
             pstmt.executeUpdate();
         } catch (SQLException e) {
            System.out.println("学号不能重复!");
            success=false;
            e.printStackTrace();
            }
         finally {
             if(success){
                 System.out.println("学生添加成功.");
             }

         }
        return success;
    }
    public static boolean DataBase_UpdateStudent(Student s){//修改数据库已有的学生信息
         if(Database_IfNumberExists(s.number)){//学生存在
             try
             {
                 PreparedStatement pstmt = null;
                 String sql="update student set name=?,sex=? where number=?";
                 pstmt=connection.prepareStatement(sql);
                 pstmt.setString(1,s.name);
                 pstmt.setString(2,s.sex);
                 pstmt.setString(2,s.number);
                 if(1==pstmt.executeUpdate()){
                     return true;
                 }
             } catch (SQLException e) {


                 e.printStackTrace();
                 return false;
             }
         }
         else{
             System.out.println("此学生学号不存在于数据库中");
             return false;
         }
    }
    public static boolean Database_IfNumberExists(int num){ //给定学号判断是否存在于数据库
        try{
            PreparedStatement pstmt = null;
            String sql="select * from student where number=?";//查找的sql
            pstmt=connection.prepareStatement(sql);
            pstmt.setString(1,String.valueOf(num));

            return pstmt.execute(sql);//是否找到学生?(boolean)

        }catch (SQLException e) {
            System.out.println("查找number存在过程出现异常");//此处最后可以注释掉
            e.printStackTrace();                 //此处最后可以注释掉
            return false;
        }
     }
    public static int Database_DeleteStudent(int num){//给定学号删除对应学生

        try{
            if(Database_IfNumberExists(num)){//找到了学生

                Database_SearchStudentByNumberAndPrintToScreen(num);
                while(true){
                    System.out.println("是否确定删除?(y/n)");
                    Scanner sc = new Scanner(System.in);
                    String choose = sc.next();
                    if(choose.equals("y")){
                        String sql="delete from student where number=?";//删除的sql
                        PreparedStatement pstmt=connection.prepareStatement(sql);
                        pstmt.setString(1,String.valueOf(num));
                        if(1==pstmt.executeUpdate()){//删除一个,返回1
                            return 0;
                        }
                    }
                    else if(choose.equals("n")){
                        return 2;//取消删除返回2
                    }
                    else{
                        System.out.println("您的输入有误, 请重新输入!");
                    }
                }
            }
            else{ //未找到学生
                return 3;
            }
        } catch (SQLException e) {//删除过程中出现异常
            System.out.println("删除过程出现异常");//此处最后可以注释掉
            e.printStackTrace();                 //此处最后可以注释掉
            return 1;
        }


    }
    public static int Database_SearchStudentByNumberAndPrintToScreen(int num){//查找学生,直接在控制台打印
         if(!Database_IfNumberExists(num)){//不存在
             return 3;
         }
         else{
             try{
                 String sql="select * from student where number=?";//查找的sql
                 PreparedStatement pstmt=connection.prepareStatement(sql);
                 pstmt.setString(1,String.valueOf(num));
                 ResultSet rs=pstmt.executeQuery(sql);//查找学生,放入ResultSet内
                 while(rs.next()){//打印学生信息
                     System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
                 }
                 return 0;//正常打印了学生信息
             }catch (SQLException e) {//删除过程中出现异常
                 System.out.println("查找学生时出现异常");//此处最后可以注释掉
                 e.printStackTrace();                 //此处最后可以注释掉
                 return 1;
             }
         }
    }
    public static int Database_loadStudent(Student stu){// 给出仅有学号的学生,从数据库获得另外两个信息,(自己写的类传参默认为引用)
         int num=stu.number;
         if(!Database_IfNumberExists(num)){//号码不存在!
             return 3;
         }
         else{
             try{
                 String sql="select * from student where number=?";//查找的sql
                 PreparedStatement pstmt=connection.prepareStatement(sql);
                 pstmt.setString(1,String.valueOf(num));
                 ResultSet rs=pstmt.executeQuery(sql);//查找学生,放入ResultSet内
                 rs.next();
                 stu.setName(rs.getString(2));//TODO: 写代码时尚未获得student类信息.可能要修改
                 stu.setSex(rs.getString(3));


                 return 0;//修改成功
             }catch (SQLException e) {//删除过程中出现异常
                 System.out.println("load学生时出现异常");//此处最后可以注释掉
                 e.printStackTrace();                 //此处最后可以注释掉
                 return 1;
             }
         }
    }
}