package DataBase;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import entity.Person.*;
public class StudentAndRoom {
    public static void displayInDB(int StuID){//根据学生学号查找宿舍信息并输出
        try{
            PreparedStatement pstmt = null;
            String sql="select * from student_and_room where stuId=?";//查找的sql
            pstmt = DataBase.getConnection().prepareStatement(sql);
            pstmt.setString(1,String.valueOf(StuID));
            ResultSet rs =pstmt.executeQuery();
            System.out.println("ID\t\t\tBuildId\t\t\tFloorId\t\t\tRoomId\t\t\tBedId");
            if(!rs.wasNull()){
                while (rs.next())
                {
                    int stuId = rs.getInt("stuId");
                    int BuildId = rs.getInt("buildId");
                    int FloorId = rs.getInt("floorId");
                    int RoomId = rs.getInt("roomId");
                    int BedId = rs.getInt("bedId");
                    System.out.println(stuId+"\t\t\t"+BuildId+"\t\t\t"+FloorId+"\t\t\t"+RoomId+"\t\t\t"+BedId);//输出信息
                }
            }
            else
                System.out.println("      "); //输出空格表示没有查到
           DataBase.DataBase_Disconnect();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //根据宿舍信息（楼号，层号，宿舍号）寻找宿舍学生ID和对应床号
    public static void displayInDB(int buildId,int FloorId,int RoomId)
    {
        try{
            PreparedStatement pstmt = null;
            String sql ="select * from student_and_room where buildId = ? and floorId = ? and roomId =?";
            pstmt = DataBase.getConnection().prepareStatement(sql);
            pstmt.setString(1,String.valueOf(buildId));
            pstmt.setString(2,String.valueOf(FloorId));
            pstmt.setString(3,String.valueOf(RoomId));
            ResultSet rs =pstmt.executeQuery();
            System.out.println("ID\t\t\tBedId");
            if(!rs.wasNull()) {
                while (rs.next()) {
                    int stuId = rs.getInt("stuId");
                    int BedId = rs.getInt("bedId");
                    System.out.println(stuId + "\t\t\t" + BedId);//输出信息

                }
            }
            else
                System.out.println("      "); //输出空格表示没有查到
            DataBase.DataBase_Disconnect();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


//根据宿舍信息（楼号，层号，宿舍号，床号）寻找宿舍学生ID
    public static void displayInDB(int buildId,int FloorId,int RoomId,int BedId)
    {
        try{
            PreparedStatement pstmt = null;
            String sql ="select * from student_and_room where buildId = ? and floorId = ? and roomId =? and bedid = ?";
            pstmt = DataBase.getConnection().prepareStatement(sql);
            pstmt.setString(1,String.valueOf(buildId));
            pstmt.setString(2,String.valueOf(FloorId));
            pstmt.setString(3,String.valueOf(RoomId));
            pstmt.setString(4,String.valueOf(BedId));
            ResultSet rs =pstmt.executeQuery();
            System.out.println("ID ");
            if(!rs.wasNull()) {
                while (rs.next()) {
                    int stuId = rs.getInt("stuId");
                    System.out.println(stuId );//输出信息
                }
            }
            else
                System.out.println("      "); //输出空格表示没有查到
            DataBase.DataBase_Disconnect();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    //判断学生是否被分配宿舍,分配成功返回true
    public static boolean isAllocated(int stuId)
    {
        try {
            PreparedStatement pstmt = null;
            String sql="select * from student_and_room where stuId=?";//查找的sql
            pstmt = DataBase.getConnection().prepareStatement(sql);
            pstmt.setString(1,String.valueOf(stuId));
            ResultSet rs =pstmt.executeQuery();
            return (!rs.wasNull());
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //给student根据性别和宿舍空闲情况分配宿舍，成功分配返回true
    public static boolean allocted(Student student)
    {
        //TODO:
        return true;
    }


    //根据宿舍具体信息判断是否存在学生，存在返回true
    public static boolean isExists(int buildId,int FloorId,int RoomId,int BedId)
    {
        try{
            PreparedStatement pstmt = null;
            String sql ="select stuId  from student_and_room where buildId = ? and floorId = ? and roomId =? and bedId = ?";
            pstmt = DataBase.getConnection().prepareStatement(sql);
            pstmt.setString(1,String.valueOf(buildId));
            pstmt.setString(2,String.valueOf(FloorId));
            pstmt.setString(3,String.valueOf(RoomId));
            pstmt.setString(4,String.valueOf(BedId));
            ResultSet rs =pstmt.executeQuery();
            return (!rs.wasNull());
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


//判断学生是否可以调换换心仪的宿舍
    public static boolean exchange(int stuID,int buildId,int FloorId,int RoomId,int BedId)
    {
        //TODO:
        try {
            if(isAllocated(stuID))
            {
                String ID=" ";
                String build = " ";
                String room = " ";
                String floor = " ";
                String bed = " ";
                PreparedStatement pstmt = null;
                String sql = "select stuId from student_and_room where buildId=? and floorId=? and roomId=? and bedId=?";
                pstmt = DataBase.getConnection().prepareStatement(sql);
                pstmt.setString(1,String.valueOf(buildId));
                pstmt.setString(2,String.valueOf(FloorId));
                pstmt.setString(3,String.valueOf(RoomId));
                pstmt.setString(4,String.valueOf(BedId));
                ResultSet rs = pstmt.executeQuery();
                while (rs.next())
                {
                     ID = rs.getString("stuId");
                }
                String sql2 = "select * from student_and_room where stuId =?";
                pstmt = DataBase.getConnection().prepareStatement(sql2);
                pstmt.setString(1,String.valueOf(stuID));
                ResultSet rs1 = pstmt.executeQuery();
                while(rs1.next())
                {
                     build = rs1.getString("buildId");
                     room = rs1.getString("roomId");
                     floor = rs1.getString("floorId");
                     bed = rs1.getString("bedId");
                }
                String sql3 = "update student_and_room set stuId = ? where buildId = ? and floorId = ? and roomId = ? and bedId = ? ";
                pstmt=DataBase.getConnection().prepareStatement(sql3);
                pstmt.setString(1,String.valueOf(stuID));
                pstmt.setString(2,String.valueOf(buildId));
                pstmt.setString(3,String.valueOf(FloorId));
                pstmt.setString(4,String.valueOf(RoomId));
                pstmt.setString(5,String.valueOf(BedId));
                pstmt.executeUpdate();
                String sql4 = "update student_and_room set buildId = ? and floorId = ? and roomId = ? and bedId = ? where stuId = ?";
                pstmt=DataBase.getConnection().prepareStatement(sql4);
                pstmt.setString(1,build);
                pstmt.setString(2,floor);
                pstmt.setString(3,room);
                pstmt.setString(4,bed);
                pstmt.setString(5,ID);
                pstmt.executeUpdate();
                return true;
            }
            else
                return false;
        }catch (SQLException e)
        {
            return false;
        }


    }


    //在数据库中删除学生信息,删除成功返回true
    public static boolean delete(Student student)
    {
        try {
            if(isAllocated(student.getStuNo()))
            {
                String sql ="delete from student_and_room where stuId = ? ";
                PreparedStatement pstmt = DataBase.getConnection().prepareStatement(sql);
                pstmt.setString(1,String.valueOf(student.getStuNo()));
                pstmt.executeUpdate();
                DataBase.DataBase_Disconnect();
                return true;
            }
            else
                return false;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
