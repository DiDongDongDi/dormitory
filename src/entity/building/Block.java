package entity.building;

// import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import entity.Person.Superior;
import implement.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import DataBase.*;

public class Block implements implement{
    private int buildId;
    private int maxFloor;
    private int maxRoom;
    private int superId;
    private boolean gender;
    private File problemFile;
    private File postFile;
	public Block(){
		
	}
	public Block(int buildId){
		this.buildId = buildId;
	}
	public void change(){//TODO:
	    Scanner scanner=new Scanner(System.in);
		while(true){
            System.out.println("请选择:\n1. 性别\n2. 管理员工号\n0. 退出");
            int choose=scanner.nextInt();
            if(choose==1){
                while(true){
                    System.out.println("请选择宿舍性别:\n1. 男\n2. 女");
                    int ch=scanner.nextInt();
                    if(ch==1){
                        setGender("男");
                        break;
                    }
                    if(ch==2){
                        setGender("女");
                        break;
                    }
                    System.out.println("您的输入有误, 请重新输入!");
                }
            }
            else if(choose==2){
                while(true){
                    System.out.println("请输入管理员工号:");
                    int ch=scanner.nextInt();
                    if(ch<0){
                        System.out.println("您的输入有误, 请重新输入!");
                    }
                    else{
                        setSuperId(ch);
                        break;
                    }
                }
            }
			else if(choose==0)
				return;
			else
				System.out.println("您的输入有误, 请重新输入!");
        }
	}
	public void postMessage(){
        System.out.println("请输入要发布的消息:");
        Scanner in=new Scanner(System.in);
        String post=in.next();
        WritePostFile(post);
        System.out.println("消息发布成功!");
	}
	public void showHistoryMessage(){
		loadPostFile();
	}
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
        String filename="Post"+buildId+".txt";
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
            System.out.println("写入失败!");
        }
        catch (Exception e)
        {
            System.out.println("未知错误!");
        }
    }
    public void WriteProblemFile(String str)
    {
        String filename="Problem"+buildId+".txt";
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
            System.out.println("写入失败!");
        }
        catch (Exception e)
        {
            System.out.println("未知错误!");
        }
    }
    public void loadPostFile()
    {
        System.out.println(getBuildId()+"号宿舍楼历史消息为:");
        try{
            FileReader fr=new FileReader("Post"+getBuildId()+".txt");
            BufferedReader br=new BufferedReader(fr);
            String str;
            while((str=br.readLine())!=null)
                System.out.println(str);
            br.close();
        }

        catch (Exception e)
        {
            System.out.println("未知错误!");
        }
    }
    public void loadProblemFile()
    {
        System.out.println(getBuildId()+"号宿舍楼消息为:");
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
            System.out.println("未知错误!");
        }
    }
   public void create_building()//自动创建大楼,但是要在factory里面盖楼
    {
        Scanner in=new Scanner(System.in);
        System.out.println("请选择宿舍性别:\n1. 男\n2. 女");
        if(in.nextInt()==1)
            gender=true;
        else
            gender=false;
        System.out.println("请输入楼号:");
        setBuildId(in.nextInt());
        System.out.println("请输入楼层数目:");
        setMaxFloor(in.nextInt());
        System.out.println("请输入每层房间数:");
        setMaxRoom(in.nextInt());
        for(int i=1;i<=getMaxFloor();i++)
        {
            for(int j=1;j<=getMaxRoom();j++)
                roomList.add(new room(i,j));
        }
        System.out.println("请输入宿舍管理员工号:");
        setSuperId(in.nextInt());
    }

    private static boolean IfBuildingExists(int OneBuildingId){
        //传入一个方法检测宿舍楼号在不在数据库中,静态,仅供本类方法内部使用
        try {
            PreparedStatement pstmt = null;
            String sql="select * from buildings where buildId=?";
            pstmt= DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,OneBuildingId);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库查找宿舍楼异常!");
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
            System.out.println("数据库查找宿舍楼是否有学生居住异常!");
            return false;//异常返回false
        }
    }

    public int store(){
        if(IfBuildingExists(getBuildId())){
            System.out.println("您输入的楼号已经存在, 新建宿舍楼失败!");
            return 2;
        }
        try {//1.存入宿舍楼
            PreparedStatement pstmt = null;

            //DataBase.getConnection().setAutoCommit(false);//关闭自动提交
            Superior supTemp=new Superior();
            boolean ex=true;
            if(supTemp.search(getSuperId())==2){
                System.out.println("无法找到您输入的管理员工号, 该工号将不被记录!");
                ex=false;
            }
            String sql;
            if(ex){
                sql="INSERT INTO buildings (buildId,gender,maxFloor,maxRoom,supId )VALUES(?,?,?,?,?)";
            }
            else{
                sql="INSERT INTO buildings (buildId,gender,maxFloor,maxRoom )VALUES(?,?,?,?)";
            }
            pstmt= DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,getBuildId());
            pstmt.setString(2,getGender());
            pstmt.setInt(3,getMaxFloor());
            pstmt.setInt(4,getMaxRoom());
            if(ex){
            pstmt.setInt(5,getSuperId());
            }
            if(1==pstmt.executeUpdate()){//成功
                System.out.println("正在建楼中, 请稍候...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库添加宿舍楼异常!");
            return 1;//异常返回1
        }
        for(int i=1;i<=getMaxFloor();i++){//每层楼循环

            try {//添加一个楼层
                PreparedStatement pstmt = null;

                String sql="INSERT INTO floors (buildId,floorId )VALUES(?,?)";
                pstmt= DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,getBuildId());
                pstmt.setInt(2,i);
                //pstmt.setInt(3,j);
                //pstmt.setInt(4,getMaxRoom());
                if(1==pstmt.executeUpdate()){//成功
                    //System.out.println("数据库写入房间成功");
                }
            } catch (SQLException e) {
                System.out.println("数据库添加楼层异常!");
                e.printStackTrace();
                return 1;//异常返回1
            }

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
                    System.out.println("数据库添加房间异常!");
                    e.printStackTrace();
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
                        System.out.println("数据库添加床位异常!");
                        e.printStackTrace();
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
            System.out.println("楼内住有人, 禁止拆除!");
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
                System.out.println("数据库删除student_and_room表异常!");
                e.printStackTrace();
                return 1;//异常返回1
            }
            try {//删除房间
                PreparedStatement pstmt = null;
                String sql="delete from rooms where buildId= ? ";
                pstmt= DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,OneBuildingId);
                pstmt.executeUpdate();//实际上不知道这句话的操作结果是什么..
            } catch (SQLException e) {
                System.out.println("数据库删除房间异常!");
                e.printStackTrace();
                return 1;//异常返回1
            }
            try {//删除楼层
                PreparedStatement pstmt = null;
                String sql="delete from floors where buildId= ? ";
                pstmt= DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,OneBuildingId);

                pstmt.executeUpdate();//实际上不知道这句话的操作结果是什么..
            } catch (SQLException e) {
                System.out.println("数据库删除楼层异常!");
                e.printStackTrace();
                return 1;//异常返回1
            }
            try {//删除宿舍楼
                PreparedStatement pstmt = null;
                String sql="delete from buildings where buildId= ? ";
                pstmt= DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,OneBuildingId);

                if(1==pstmt.executeUpdate()){//成功,楼只有一栋
                    System.out.println("正在拆楼中, 请稍候...");
                }
            } catch (SQLException e) {
                System.out.println("数据库删除宿舍楼异常!");
                e.printStackTrace();
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
            return 2;
        }
        try{
            String sql="select * from buildings where buildId=?";//查找的sql
            PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,OneBuildingId);
            ResultSet rs=pstmt.executeQuery();//查找宿舍楼,放入ResultSet内
            System.out.println("楼号\t\t管理员工号\t\t性别\t\t楼层数\t\t每层房间数");
            while(rs.next()){//打印宿舍楼信息
                System.out.println(
                                rs.getInt(1)+"\t\t"+
                                rs.getInt(2)+"\t\t"+
                                rs.getString(3)+"\t\t"+
                                rs.getInt(4)+"\t\t"+
                                rs.getInt(5));
            }
            return 0;//正常打印了宿舍楼信息
        }catch (SQLException e) {//查找宿舍楼出现异常
            System.out.println("尝试显示宿舍楼信息...\n查找宿舍楼时出现异常!");//此处最后可以注释掉
            e.printStackTrace();                 //此处最后可以注释掉
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
                String sql="select * from buildings where buildId=?";//查找的sql
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
                System.out.println("加载宿舍楼信息异常!");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
    }

    public int update() {//TODO:
        int num=getBuildId();
        if(!IfBuildingExists(num)){//号码不存在!
            return 2;
        }
        else{
            try{
                String sql="select * from buildings where buildId=?";//查找的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                ResultSet rs=pstmt.executeQuery();//查找
                rs.next();

                if(!rs.getString(3).equals(this.getGender())&&IfBuildingHasStudent(num)){
                    //如果宿舍里面有人并且试图(更换)性别,阻止更新
                    return 3;
                }
            }catch (SQLException e) {
                System.out.println("更新宿舍楼, 检查更新条件时异常!");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
        try{//todo:supId为外码
            Superior suptemp=new Superior();
            boolean existSuper=true;
            String sql=null;
            PreparedStatement pstmt=null;
            if(2==suptemp.search(getSuperId())){
                existSuper=false;
            }
            if(existSuper) {
                sql = "update buildings set supId=?,gender=? where buildId= ?";//查找的sql
                pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,getSuperId());
                pstmt.setString(2,getGender());
                pstmt.setInt(3,getBuildId());
            }
            else{
                System.out.println("无法找到您输入的管理员工号, 该工号将不被记录!");
                sql = "update buildings set gender=? where buildId= ?";//查找的sql
                pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setString(1,getGender());
                pstmt.setInt(2,getBuildId());
            }

            if (1==pstmt.executeUpdate()){
                return 0;
            }
            else{
                return 1;
            }
        }catch (SQLException e) {//更新过程中出现异常
            System.out.println("更新检测完毕, 更新宿舍楼时出现异常!");//此处最后可以注释掉
            e.printStackTrace();                 //此处最后可以注释掉
            return 1;
        }
    }
    public void show()
    {
        // System.out.println(buildId+"号宿舍楼信息如下：");
        System.out.println("楼号\t\t性别\t\t楼层数\t\t每层房间数\t\t管理员工号");
        System.out.println(getBuildId()+"\t\t"+getSex()+"\t\t"+maxFloor+"\t\t"+maxRoom+"\t\t"+superId);

    }
}
