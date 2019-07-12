package entity.building;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DataBase.*;


public class room {
    private int blockId;
    private int floorId;
    private int roomId;
    private int contain;
    private boolean []bedId;
    private double elecBillBala;
    private double healScore;

	public void change(){
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("请输入您要更新的信息 1.电费余量 2.卫生分数  ");
            int choose=scanner.nextInt();
            if(choose==1){
                while(true){
                    System.out.println("请输入新的电费余量 ");
                    double ch=scanner.nextDouble();
                    setElecBillBala(ch);
                    break;
                }
                break;
            }
            if(choose==2){
                while(true){
                    System.out.println("请输入新的卫生分数 ");
                    double ch=scanner.nextDouble();
                    if(ch<0){
                        System.out.println("输入错误,请重新输入");
                    }
                    else{
                        setHealScore(ch);
                        break;
                    }
                }
                break;
            }

        }
	}

    public void setElecBillBala(double elecBillBala) {
        this.elecBillBala = elecBillBala;
    }

    public double getElecBillBala() {
        return elecBillBala;
    }

    public void setHealScore(double healScore) {
        this.healScore = healScore;
    }

    public double getHealScore() {
        return healScore;
    }

    public room()
    {contain=4;}

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
    public room(int fI,int rI)
    {
        floorId=fI;
        roomId=rI;
        bedId=new boolean[4];
        for(int i=0;i<4;i++)
        {
            bedId[i]=false;//初始化，四个床都没有人住进来哪个床哪个true
        }
        contain=4;//一个宿舍最多住四个人，住进去一个--，出来一个++
    }
    public room(int bI, int fI, int rI)
    {
        blockId=bI;
        bedId=new boolean[4];
        for(int i=0;i<4;i++)
        {
            bedId[i]=false;//初始化，四个床都没有人住进来哪个床哪个true
        }
        contain=4;//一个宿舍最多住四个人，住进去一个--，出来一个++
        this.floorId=fI;
        this.roomId=rI;
    }

    public boolean[] getBedId() {
        return bedId;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getContain() {
        return contain;
    }

    public void setContain(int contain) {
        this.contain = contain;
    }
    public void gotoBed(int bedid)//调用这个函数来入住具体床位
    {
        if(bedId[bedid]==true)
        {
            System.out.println("这个床位已经有人了");
            return;
        }
        else
        bedId[bedid]=true;
        System.out.println("入住成功！");
    }

    private static boolean If_roomExists(int blockId,int floorId,int roomId){ //给定房间信息判断是否存在于数据库,静态,仅供操作数据库的方法内部使用
        try{
            PreparedStatement pstmt = null;
            String sql="select * from rooms where buildId=? and floorId=? and roomId=?";//查找的sql
            pstmt=DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,blockId);
            pstmt.setInt(2,floorId);
            pstmt.setInt(3,roomId);
            ResultSet rs=pstmt.executeQuery();

            return rs.next();//是否找到room ?(boolean)

        }catch (SQLException e) {
            System.out.println("查找房间是否存在过程出现异常");//此处最后可以注释掉
            //e.printStackTrace();                 //此处最后可以注释掉
            return false;
        }
    }
    public int display(int blockId,int floorId,int roomId){//给定房间查找并打印--hwt
        //查找失败返回1
        //未找到返回3
        //直接从数据库查找显示即可
        if(!If_roomExists(blockId,floorId,roomId)){//房间不存在
            return 3;
        }
        try{
            PreparedStatement pstmt = null;
            String sql="select * from rooms where buildId=? and floorId=? and roomId=?";//查找的sql
            pstmt=DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,blockId);
            pstmt.setInt(2,floorId);
            pstmt.setInt(3,roomId);
            ResultSet rs=pstmt.executeQuery();
            System.out.println("宿舍楼号\t\t楼层号\t\t房间号\t\t电费余额\t\t卫生评分");
            while(rs.next()){//打印房间信息
                System.out.println(
                        rs.getInt(1)+"\t\t"+
                        rs.getInt(2)+"\t\t"+
                        rs.getInt(3)+"\t\t"+
                        rs.getDouble(4)+"\t\t"+
                        rs.getDouble(5));
            }
            return 0;//正常打印了学生信息
        }catch (SQLException e) {//过程中出现异常
            System.out.println("尝试显示学生信息:查找学生时出现异常");//此处最后可以注释掉
            //e.printStackTrace();                 //此处最后可以注释掉
            return 1;
        }
    }

    public int load(){//已有主码的房间,从数据库获得其他信息

        if(!If_roomExists(getBlockId(),getFloorId(),getRoomId())){//号码不存在!
            return 2;
        }
        else{
            try{
                PreparedStatement pstmt = null;
                String sql="select * from rooms where buildId=? and floorId=? and roomId=?";//查找的sql
                pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,blockId);
                pstmt.setInt(2,floorId);
                pstmt.setInt(3,roomId);
                ResultSet rs=pstmt.executeQuery();
                rs.next();
                setElecBillBala(rs.getDouble(4));
                setHealScore(rs.getDouble(5));//


                return 0;//load successfully
            }catch (SQLException e) {//load room过程中出现异常
                System.out.println("load房间时出现异常");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
    }
    public int update(){
        if(!If_roomExists(getBlockId(),getFloorId(),getRoomId())){//号码不存在!

            return 2;
        }
        else{
            try{
                PreparedStatement pstmt = null;
                String sql="update rooms set elecBillBala=?,healScore=? where buildId=? and floorId=? and roomId=?";//查找的sql
                pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setDouble(1,getElecBillBala());
                pstmt.setDouble(2,getHealScore());
                pstmt.setInt(3,blockId);
                pstmt.setInt(4,floorId);
                pstmt.setInt(5,roomId);
                pstmt.executeUpdate();

                return 0;//update successfully
            }catch (SQLException e) {//load room过程中出现异常
                System.out.println("update房间时出现异常");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
    }

}
