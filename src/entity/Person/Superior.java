package entity.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import DataBase.*;
import implement.implement;
public class Superior extends Person implements implement{

    private int supId;
    private String phoneNum;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public Superior(){}

	public Superior(int supId){
		this.supId = supId;
	}

    public void create_super()
    {
        Scanner in=new Scanner(System.in);
        int no;
        boolean flg=true;
        System.out.println("欢迎办理入住手续！");
        System.out.print("请输入您的姓名：");
        String names=in.next();
        setName(names);
        while(flg){
            System.out.print("请输入您的性别 1.男性 2.女性");
        switch (in.nextInt())
        {
            case 1:
                setSex(true);
                flg=false;
                break;
            case 2:
                setSex(false);
                flg=false;
                break;
            default:
                System.out.println("输入错误");
                break;
        }}
        System.out.print("请输入您的工号：");
        no=in.nextInt();
        setSupId(no);
        System.out.println("登记成功");
    }
    public void show()
    {
        System.out.println("学生信息如下");
        System.out.println("姓名\t\t学号\t\t性别");
        System.out.println(getName()+"\t\t"+getSupId()+"\t\t"+getSex());
    }
    public void change()
    {
        System.out.println("请选择修改的选项 1.姓名 2.性别");
        Scanner in = new Scanner(System.in);
        switch (in.nextInt())
        {
            case 1:
                System.out.println("请输入姓名");
                this.setName(in.next());
                break;
            case 2:
                System.out.println("请输入性别 1.male 2.female");
                if(in.nextInt()==1)
                    setSex(true);
                else
                    setSex(false);
                break;
            default:
                System.out.println("输入错误");
                break;
        }
    }
    private static boolean If_SuperNumberExists(int num){ //给定工号判断是否存在于数据库,静态,仅供操作数据库的方法内部使用
        try{
            PreparedStatement pstmt = null;
            String sql="select * from super where supId=?";//查找的sql
            pstmt=DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,num);
            ResultSet rs=pstmt.executeQuery();

            return rs.next();//是否找到管理员?(boolean)

        }catch (SQLException e) {
            System.out.println("查找工号是否存在过程出现异常");//此处最后可以注释掉
            //e.printStackTrace();                 //此处最后可以注释掉
            return false;
        }
    }
    public int store(){
        if(If_SuperNumberExists(getSupId())){
            return 2;
        }

        try
        {
            PreparedStatement pstmt = null;
            String sql="INSERT INTO super VALUES(?,?,?,?)";
            pstmt= DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,this.getSupId());
            pstmt.setString(2,this.getName());
            pstmt.setString(3,this.getSex());
            pstmt.setString(4,this.getPhoneNum());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("管理员store时出现异常");
            return 1;
            //e.printStackTrace();
        }
        return 0;
    }
    public int search(int num){//todo:失败返回1未实现
        if(!(If_SuperNumberExists(num))){//号码不存在!
            return 2;
        }
        else{
            return 0;
        }
    }
    public int display(int num){//给定工号查找并打印--hwt
        //查找失败返回1
        //未找到返回3
        //直接从数据库查找显示即可
        if(If_SuperNumberExists(num)){//是否找到管理员?(boolean)
            return 3;
        }
        else{
            try{
                String sql="select * from super where supId=?";//查找的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                ResultSet rs=pstmt.executeQuery();//查找管理员,放入ResultSet内
                while(rs.next()){//打印管理员信息
                    System.out.println(
                            rs.getInt(1)+
                                    rs.getString(2)+
                                    rs.getString(3)+
                                    rs.getInt(4));
                    }
                    return 0;//正常打印了管理员信息
                }catch (SQLException e) {//删除过程中出现异常
                    System.out.println("尝试显示管理员信息:查找管理员时出现异常");//此处最后可以注释掉
                    //e.printStackTrace();                 //此处最后可以注释掉
                    return 1;
                }
        }
    }
    public int load(){// 给出仅有学号的管理员,从数据库获得另外的信息
        int num=getSupId();
        if(!If_SuperNumberExists(num)){//号码不存在!
            return 2;
        }
        else{
            try{
                String sql="select * from super where supId=?";//查找的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                ResultSet rs=pstmt.executeQuery();//查找管理员,放入ResultSet内
                rs.next();
                setName(rs.getString(2));
                setSex(rs.getString(3).equals("男"));
                setPhoneNum(rs.getString(4));


                return 0;//修改成功
            }catch (SQLException e) {//load过程中出现异常
                System.out.println("load管理员时出现异常");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
    }

    public int delete(int num){//给定学号删除对应管理员,成功返回0,失败返回1

        try{
            if(If_SuperNumberExists(num)){//找到了管理员

                display(num);

                String sql="delete from super where supId=?";//删除的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                if(1==pstmt.executeUpdate()){//删除一个,返回0
                    return 0;
                }
                System.out.println("搜索到好几个,这句话理论上不应该被执行");
                return 1;
            }
            else{ //未找到管理员
                return 1;
            }
        } catch (SQLException e) {//删除过程中出现异常
            System.out.println("删除管理员失败,请检查管理员是否有仍在管理的宿舍楼");//此处最后可以注释掉
            e.printStackTrace();                 //此处最后可以注释掉
            return 1;
        }
    }

    public int update() {//学号不变.修改数据库中性别和姓名  返回0为正常
        if(!(If_SuperNumberExists(getSupId()))){//号码不存在!
            return 1;
        }
        else{
            try{
                String sql="update super set name=?,gender=?,phoneNum=? where supId=?";//查找的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setString(1,getName());
                pstmt.setString(2,getSex());
                pstmt.setString(3,getPhoneNum());
                pstmt.setInt(4,getSupId());
                if (1==pstmt.executeUpdate()){
                    return 0;
                }
                else{
                    return 1;
                }


            }catch (SQLException e) {//update过程中出现异常
                System.out.println("update管理员时出现异常");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
        //return 0;
    }
}

