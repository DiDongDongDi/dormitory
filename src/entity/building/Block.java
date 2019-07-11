package entity.building;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import implement.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import DataBase.*;

public class Block implements implement{
    private static int buildId=1;
    private int maxFloor;
    private int maxRoom;
    private int superId;
    private boolean gender;
    private File problemFile;
    private File postFile;
    public String getGender(){
        return gender?"男":"女";
    }
    public void setGender(String newGender){
        gender=newGender.equals("男");
    }
    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getMaxRoom() {
        return maxRoom;
    }

    public void setMaxRoom(int maxRoom) {
        this.maxRoom = maxRoom;
    }

    private ArrayList roomList=new ArrayList<room>();

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }
    public String getSex()
    {
        if(gender==true)
            return "男";
        else
            return "女";
    }
    public int getSuperId() {
        return superId;
    }
    public void WritePostFile(String str)
    {
        String filename="res/Post"+buildId+".txt";
        try{
            postFile=new File(filename);
            if(!postFile.exists())
                postFile.createNewFile();
            FileWriter fr=new FileWriter(filename,true);
            BufferedWriter bw=new BufferedWriter(fr);
            bw.write(str+"\r\n");
            bw.flush();
            bw.close();
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println("写入失败！");
        }
        catch (Exception e)
        {
            System.out.println("未知错误！");
        }
    }
    public void WriteProblemFile(String str)
    {
        String filename="res/Problem"+buildId+".txt";
        try{
        problemFile=new File(filename);
        if(!problemFile.exists())
            problemFile.createNewFile();
            FileWriter fr=new FileWriter(filename,true);
            BufferedWriter bw=new BufferedWriter(fr);
            bw.write(str+"\r\n");
            bw.flush();
            bw.close();
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println("写入失败！");
        }
        catch (Exception e)
        {
            System.out.println("未知错误！");
        }
    }
    public void loadPostFile()
    {
        System.out.println(getBuildId()+"号大楼公告板如下");
        try{
            FileReader fr=new FileReader(postFile);
            BufferedReader br=new BufferedReader(fr);
            String str;
            while((str=br.readLine())!=null)
                System.out.println(str);
            br.close();
        }

        catch (Exception e)
        {
            System.out.println("未知错误！");
        }
    }
    public void loadProblemFile()
    {
        System.out.println(getBuildId()+"号大楼问题板如下");
        try{
        FileReader fr=new FileReader(problemFile);
        BufferedReader br=new BufferedReader(fr);
        String str;
        while((str=br.readLine())!=null)
            System.out.println(str);
            br.close();
        }

        catch (Exception e)
        {
            System.out.println("未知错误！");
        }
    }
   public void create_building()//自动创建大楼,但是要在factory里面盖楼
    {
        Scanner in=new Scanner(System.in);
        System.out.println("这是男生宿舍还是女生宿舍 1.男生 2.女生");
        if(in.nextInt()==1)
            gender=true;
        else
            gender=false;
        setBuildId(getBuildId()+1);
        System.out.println("请输入这栋楼有多少楼层");
        setMaxFloor(in.nextInt());
        System.out.println("请输入一层有多少房间");
        setMaxRoom(in.nextInt());
        for(int i=1;i<=getMaxFloor();i++)
        {
            for(int j=1;j<=getMaxRoom();j++)
                roomList.add(new room(i,j));
        }
        System.out.println("请输入管理员id");
        setSuperId(in.nextInt());
    }

    private static boolean IfBuildingExists(int OneBuildingId){
        //传入一个方法检测宿舍楼号在不在数据库中,静态,仅供本类方法内部使用
        try {
            PreparedStatement pstmt = null;
            String sql="select * from building where buildId=?";
            pstmt= DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,OneBuildingId);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("数据库查找宿舍楼是否存在异常");
            return false;//异常返回false
        }
    }
    private static boolean IfBuildingHasStudent(int OneBuildingId) {
        //传入一个楼号判断楼内是否有学生居住
        try {
            PreparedStatement pstmt = null;
            String sql="select * from student_and_room where buildId= ? and stuId is not null";
            pstmt= DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,OneBuildingId);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("数据库查找宿舍楼是否有学生居住出现异常");
            return false;//异常返回false
        }
    }

    public int store(){

        try {//1.存入宿舍楼
            PreparedStatement pstmt = null;

            //DataBase.getConnection().setAutoCommit(false);//关闭自动提交

            String sql="INSERT INTO buildings (supId,gender,maxFloor,maxRoom,supId )VALUES(?,?,?,?,?)";
            pstmt= DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,getBuildId());
            pstmt.setString(2,getGender());
            pstmt.setInt(3,getMaxFloor());
            pstmt.setInt(4,getMaxRoom());
            pstmt.setInt(5,getSuperId());
            if(1==pstmt.executeUpdate()){//成功
                System.out.println("数据库写入宿舍楼成功");
            }
        } catch (SQLException e) {
            System.out.println("数据库添加宿舍楼异常");
            return 1;//异常返回1
        }
        for(int i=1;i<=getMaxFloor();i++){//每层楼循环
            for(int j=1;j<=getMaxRoom();j++){//一层楼的每个房间循环
                try {//添加一个房间
                    PreparedStatement pstmt = null;

                    String sql="INSERT INTO rooms (buildId,floorId,roomId )VALUES(?,?,?)";
                    pstmt= DataBase.getConnection().prepareStatement(sql);
                    pstmt.setInt(1,getBuildId());
                    pstmt.setInt(2,i);
                    pstmt.setInt(3,j);
                    //pstmt.setInt(4,getMaxRoom());
                    if(1==pstmt.executeUpdate()){//成功
                        //System.out.println("数据库写入房间成功");
                    }
                } catch (SQLException e) {
                    System.out.println("数据库添加房间异常");
                    return 1;//异常返回1
                }
                for(int k=1;k<=4;k++){//添加四个床位
                    try {
                        PreparedStatement pstmt = null;

                        String sql="INSERT INTO student_and_room (buildId,floorId,roomId,bedId )VALUES(?,?,?,?)";
                        pstmt= DataBase.getConnection().prepareStatement(sql);
                        pstmt.setInt(1,getBuildId());
                        pstmt.setInt(2,i);
                        pstmt.setInt(3,j);
                        pstmt.setInt(4,k);
                        if(1==pstmt.executeUpdate()){//成功
                            //
                        }
                    } catch (SQLException e) {
                        System.out.println("数据库添加床位异常");
                        return 1;//异常返回1
                    }
                }
            }
        }
        return 0;//全程没有异常,返回0
    }
    public int delete(int OneBuildingId){
        if(!IfBuildingExists(OneBuildingId)){//数据库中不存在这个楼号
            //System.out.println();
            return 1;
        }
        else if(IfBuildingHasStudent(OneBuildingId)){//数据库有楼号,但是楼内住有人,阻止删除
            return 1;
        }
        else {//存在楼号,并且有学生,开始删除
            try {//删除床位和学生的关系
                PreparedStatement pstmt = null;

                String sql="delete from student_and_room where buildId= ? ";
                pstmt= DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,OneBuildingId);

                pstmt.executeUpdate();


            } catch (SQLException e) {
                System.out.println("数据库删除student_and_room异常");
                return 1;//异常返回1
            }
            try {//删除房间
                PreparedStatement pstmt = null;
                String sql="delete from rooms where buildId= ? ";
                pstmt= DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,OneBuildingId);
                pstmt.executeUpdate();//实际上不知道这句话的操作结果是什么..
            } catch (SQLException e) {
                System.out.println("数据库删除房间异常");
                return 1;//异常返回1
            }
            try {//删除宿舍楼
                PreparedStatement pstmt = null;
                String sql="delete from buildings where buildId= ? ";
                pstmt= DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,OneBuildingId);

                if(1==pstmt.executeUpdate()){//成功,楼只有一栋
                    System.out.println("数据库写入宿舍楼成功");
                }
            } catch (SQLException e) {
                System.out.println("数据库添加宿舍楼异常");
                return 1;//异常返回1
            }
            return 0;
        }
    }
    public int search(int OneBuildingId){
        if(!IfBuildingExists(OneBuildingId)){//建筑不存在
            return 2;
        }
        else{
            return 0;
        }
    }
    public int display(int OneBuildingId){
        if(!IfBuildingExists(OneBuildingId)){//不存在这个楼
            return 3;
        }
        try{
            String sql="select * from building where buildingId=?";//查找的sql
            PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,OneBuildingId);
            ResultSet rs=pstmt.executeQuery();//查找宿舍楼,放入ResultSet内
            while(rs.next()){//打印宿舍楼信息
                System.out.println(
                        rs.getInt(1)+
                        rs.getInt(2)+
                        rs.getString(3)+
                        rs.getInt(4)+
                        rs.getInt(5));
            }
            return 0;//正常打印了宿舍楼信息
        }catch (SQLException e) {//查找宿舍楼出现异常
            System.out.println("尝试显示学生信息:查找学生时出现异常");//此处最后可以注释掉
            //e.printStackTrace();                 //此处最后可以注释掉
            return 1;
        }
    }
    public int load(){// 给出仅有楼号的宿舍楼从数据库获得的另外的信息
        int num=getBuildId();
        if(!IfBuildingExists(num)){//号码不存在!
            return 2;
        }
        else{
            try{
                String sql="select * from building where buildId=?";//查找的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                ResultSet rs=pstmt.executeQuery();//查找宿舍楼,放入ResultSet内
                rs.next();
                setSuperId(rs.getInt(2));
                setGender(rs.getString(3));
                setMaxFloor(rs.getInt(4));
                setMaxRoom(rs.getInt(5));
                return 0;//load完毕
            }catch (SQLException e) {//load宿舍楼过程中出现异常
                System.out.println("load宿舍楼时出现异常");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
    }

    public int update() {
        int num=getBuildId();
        if(!IfBuildingExists(num)){//号码不存在!
            return 1;
        }
        else{
            try{
                String sql="select * from building where buildId=?";//查找的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                ResultSet rs=pstmt.executeQuery();//查找
                rs.next();

                if(!rs.getString(3).equals(this.getGender())&&IfBuildingHasStudent(num)){
                    //如果宿舍里面有人并且试图(更换)性别,阻止更新
                    return 1;
                }
            }catch (SQLException e) {
                System.out.println("update宿舍楼检查更新条件时出现异常");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
        try{
            String sql="update student set supId=?,gender=? where buildId= ?";//查找的sql
            PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,getSuperId());
            pstmt.setString(2,getGender());
            pstmt.setInt(3,getBuildId());
            if (1==pstmt.executeUpdate()){
                return 0;
            }
            else{
                return 1;
            }
        }catch (SQLException e) {//更新过程中出现异常
            System.out.println("update,检测完毕,更新宿舍楼时出现异常");//此处最后可以注释掉
            e.printStackTrace();                 //此处最后可以注释掉
            return 1;
        }
    }
    public void show()
    {
        System.out.println(buildId+"号宿舍楼信息如下：");
        System.out.println("性别\t\t楼层数\t\t总房间数\t\t管理员id");
        System.out.println(getSex()+"\t\t"+maxFloor+"\t\t"+maxFloor*maxRoom+"\t\t"+superId);

    }
}
