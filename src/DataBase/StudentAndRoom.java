package DataBase;

import java.sql.*;
import entity.Person.*;
public class StudentAndRoom {
    public static void displayInDB(int StuID){//根据学生学号查找宿舍信息并输出
        try{
            PreparedStatement pstmt = null;
            String sql="select * from student_and_room where stuId=?";//查找的sql
            pstmt = DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,StuID);
            ResultSet rs =pstmt.executeQuery();
            System.out.println("学号\t\t楼号\t\t楼层号\t\t房间号\t\t床位号");
            if(rs.next()){
                rs.previous();
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
                System.out.println("        "); //输出空格表示没有查到
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
            pstmt.setInt(1,buildId);
            pstmt.setInt(2,FloorId);
            pstmt.setInt(3,RoomId);
            ResultSet rs =pstmt.executeQuery();
            System.out.println("学号\t\t床位");
            if(rs.next()) {
                rs.previous();
                while (rs.next()) {
                    int stuId = rs.getInt("stuId");
                    int BedId = rs.getInt("bedId");
                    System.out.println(stuId==0?"     ":stuId + "\t\t" + BedId);//输出信息
                    //System.out.println("dsa");
                }
            }
            else
                System.out.println("        "); //输出空格表示没有查到
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
            pstmt.setInt(1,buildId);
            pstmt.setInt(2,FloorId);
            pstmt.setInt(3,RoomId);
            pstmt.setInt(4,BedId);
            ResultSet rs =pstmt.executeQuery();
            System.out.println("学号");
            if(rs.next()) {
                rs.previous();
                while (rs.next()) {
                    int stuId = rs.getInt("stuId");
                    System.out.println(stuId );//输出信息
                }
            }
            else
                System.out.println("        "); //输出空格表示没有查到
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
            pstmt.setInt(1,stuId);
            ResultSet rs =pstmt.executeQuery();
            return (rs.next());
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //给student根据性别和宿舍空闲情况分配宿舍，成功分配返回true
    public static boolean allocated(Student student)
    {
        //TODO:
        try {
            int build = 0;
            int room = 0;
            int floor = 0;
            int bed = 0;
            if (student.getSex().equals("男")) {
                String sql = "select * from boy_empty_rooms";
                PreparedStatement psmst = DataBase.getConnection().prepareStatement(sql);
                ResultSet rs = psmst.executeQuery();
                if(!rs.next())
                    return false;
                else {
                    rs.previous();
                    while (rs.next())
                    {
                        build = rs.getInt("buildId");
                        room = rs.getInt("roomId");
                        floor = rs.getInt("floorId");
                        bed = rs.getInt("bedId");
                    }
                    String sql2 = "update student_and_room set stuId = ? where buildId = ? and floorId = ? and roomId = ? and bedId = ?  ";
                    psmst = DataBase.getConnection().prepareStatement(sql2);
                    psmst.setInt(2,build);
                    psmst.setInt(3,floor);
                    psmst.setInt(4,room);
                    psmst.setInt(5,bed);
                    psmst.setInt(1,student.getStuNo());
                    if(1==psmst.executeUpdate())
                        return true;
                    return false;
                }
            }
            else if(student.getSex().equals("女"))
            {
                String sql = "select * from girl_empty_rooms";
                PreparedStatement psmst = DataBase.getConnection().prepareStatement(sql);
                ResultSet rs = psmst.executeQuery();
                if(!rs.next())
                    return false;
                else {
                    rs.previous();
                    while (rs.next())
                    {
                        build = rs.getInt("buildId");
                        room = rs.getInt("roomId");
                        floor = rs.getInt("floorId");
                        bed = rs.getInt("bedId");
                    }
                    String sql2 = "update student_and_room set stuId = ? where buildId = ? and floorId = ? and roomId = ? and bedId = ?  ";
                    psmst = DataBase.getConnection().prepareStatement(sql2);
                    psmst.setInt(2,build);
                    psmst.setInt(3,floor);
                    psmst.setInt(4,room);
                    psmst.setInt(5,bed);
                    psmst.setInt(1,student.getStuNo());
                    if(1==psmst.executeUpdate())
                        return true;
                    return false;
                }
            }
            else
                return false;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    //根据宿舍具体信息判断是否存在学生，存在返回true
    public static boolean isExists(int buildId,int FloorId,int RoomId,int BedId)
    {
        try{
            PreparedStatement pstmt = null;
            String sql ="select stuId  from student_and_room where buildId = ? and floorId = ? and roomId =? and bedId = ?";
            pstmt = DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,buildId);
            pstmt.setInt(2,FloorId);
            pstmt.setInt(3,RoomId);
            pstmt.setInt(4,BedId);
            ResultSet rs =pstmt.executeQuery();
            return (rs.next());
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
                int ID = 0;
                int build = 0;
                int room = 0;
                int floor = 0;
                int bed = 0;
                PreparedStatement pstmt = null;
                String sql = "select stuId from student_and_room where buildId=? and floorId=? and roomId=? and bedId=?";
                pstmt = DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,buildId);
                pstmt.setInt(2,FloorId);
                pstmt.setInt(3,RoomId);
                pstmt.setInt(4,BedId);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next())
                {
                    ID = rs.getInt("stuId");
                }
                String sql2 = "select * from student_and_room where stuId =?";
                pstmt = DataBase.getConnection().prepareStatement(sql2);
                pstmt.setInt(1,stuID);
                ResultSet rs1 = pstmt.executeQuery();
                while(rs1.next())
                {
                    build = rs1.getInt("buildId");
                    room = rs1.getInt("roomId");
                    floor = rs1.getInt("floorId");
                    bed = rs1.getInt("bedId");
                }
                String sql3 = "update student_and_room set stuId = ? where buildId = ? and floorId = ? and roomId = ? and bedId = ? ";
                pstmt=DataBase.getConnection().prepareStatement(sql3);
                pstmt.setInt(1,stuID);
                pstmt.setInt(2,buildId);
                pstmt.setInt(3,FloorId);
                pstmt.setInt(4,RoomId);
                pstmt.setInt(5,BedId);
                pstmt.executeUpdate();
                String sql4 = "update student_and_room set stuId = ?  where buildId = ? and floorId = ? and roomId = ? and bedId = ? ";
                pstmt=DataBase.getConnection().prepareStatement(sql4);
                pstmt.setInt(1,ID);
                pstmt.setInt(2,build);
                pstmt.setInt(3,floor);
                pstmt.setInt(4,room);
                pstmt.setInt(5,bed);
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


    //学生退房，退房成功返回true
    public static boolean delete(int stuID)
    {
        try {
            if(isAllocated(stuID))
            {
                String sql ="update  student_and_room  set stuId = null where stuId = ? ";
                PreparedStatement pstmt = DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,stuID);
                pstmt.executeUpdate();
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
