package entity.building;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataBase.*;
import java.util.Scanner;

public class Floor {
    private int buildId;
    private int floorId;
    private int brokenWaterNum;
    private int brokenLightNum;
    public Floor(){
        
    }

	public void change(){
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("请输入您要更新的信息:\n1. 损坏水机数\n2. 损坏灯数");
            int choose=scanner.nextInt();
            if(choose==2){
                while(true){
                    System.out.println("请输入损坏的灯数:");
                    int ch=scanner.nextInt();
                    if(ch>=0){
                        setBrokenLightNum(ch);
                        break;
                    }
                    System.out.println("您的输入有误, 请重新输入!");
                }
                break;
            }
            if(choose==1){
                while(true){
                    System.out.println("请输入损坏的水机数:");
                    int ch=scanner.nextInt();
                    if(ch<0){
                        System.out.println("您的输入有误, 请重新输入!");
                    }
                    else{
                        setBrokenWaterNum(ch);
                        break;
                    }
                }
                break;
            }

        }
	}

    public Floor(int bI,int fI)
    {
        this.buildId=bI;
        this.floorId=fI;
    }

    public int getBrokenLightNum() {
        return brokenLightNum;
    }

    public int getBrokenWaterNum() {
        return brokenWaterNum;
    }

    public void setBrokenLightNum(int brokenLightNum) {
        this.brokenLightNum = brokenLightNum;
    }

    public void setBrokenWaterNum(int brokenWaterNum) {
        this.brokenWaterNum = brokenWaterNum;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }


    private static boolean If_FloorExists(int blockId,int floorId){ //给定楼层信息判断是否存在于数据库,静态,仅供操作数据库的方法内部使用
        try{
            PreparedStatement pstmt = null;
            String sql="select * from floors where buildId=? and floorId=?";//查找的sql
            pstmt=DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,blockId);
            pstmt.setInt(2,floorId);
            ResultSet rs=pstmt.executeQuery();

            return rs.next();//是否找到floor ?(boolean)

        }catch (SQLException e) {
            System.out.println("查找楼层是否存在异常!");//此处最后可以注释掉
            //e.printStackTrace();                 //此处最后可以注释掉
            return false;
        }
    }

    public int display(int blockId,int floorId){//给定楼层查找并打印--hwt
        //查找失败返回1
        //未找到返回3
        //直接从数据库查找显示即可
        if(!If_FloorExists(blockId,floorId)){//楼层不存在
            System.out.println("该楼层不存在!");
            return 3;
        }
        try{
            PreparedStatement pstmt = null;
            String sql="select * from floors where buildId=? and floorId=? ";//查找的sql
            pstmt=DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,blockId);
            pstmt.setInt(2,floorId);

            ResultSet rs=pstmt.executeQuery();
            System.out.println("宿舍楼号\t\t 楼层号\t\t已坏的水机数\t\t已坏的灯泡数");

            while(rs.next()){//打印floor信息
                System.out.println(
                                rs.getInt(1)+"\t\t"+
                                rs.getInt(2)+"\t\t"+
                                rs.getInt(3)+"\t\t"+
                                rs.getInt(4));
            }
            return 0;//正常打印了floor信息
        }catch (SQLException e) {//过程中出现异常
            System.out.println("尝试显示楼层信息...\n查找楼层异常!");//此处最后可以注释掉
            //e.printStackTrace();                 //此处最后可以注释掉
            return 1;
        }
    }
    public int load(){//已有主码的房间,从数据库获得其他信息

        if(!If_FloorExists(getBuildId(),getFloorId())){//号码不存在!
            return 2;
        }
        else{
            try{
                PreparedStatement pstmt = null;
                String sql="select * from floors where buildId=? and floorId=?";//查找的sql
                pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,getBuildId());
                pstmt.setInt(2,getFloorId());

                ResultSet rs=pstmt.executeQuery();
                rs.next();
                setBrokenWaterNum(rs.getInt(3));
                setBrokenLightNum(rs.getInt(4));//


                return 0;//load successfully
            }catch (SQLException e) {//load floor过程中出现异常
                System.out.println("加载楼层信息异常!");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
    }
    public int update(){
        if(!If_FloorExists(getBuildId(),getFloorId())){//号码不存在!
            System.out.println("该楼层不存在!");
            return 2;
        }
        else{
            try{
                PreparedStatement pstmt = null;
                String sql="update floors set brokenWaterNum=?,brokenLightNum=? where buildId=? and floorId=? ";//查找的sql
                pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,getBrokenWaterNum());
                pstmt.setInt(2,getBrokenLightNum());
                pstmt.setInt(3,buildId);
                pstmt.setInt(4,floorId);

                pstmt.executeUpdate();

                return 0;//update successfully
            }catch (SQLException e) {//update floor过程中出现异常
                System.out.println("加载楼层信息异常!");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
    }
}
