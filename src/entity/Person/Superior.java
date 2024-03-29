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
        // System.out.println("欢迎办理入住手续！");
        System.out.println("请输入职工姓名:");
        String names=in.next();
        setName(names);
        while(flg){
            System.out.println("请选择职工性别\n1. 男\n2. 女");
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
                System.out.println("您的输入有误!");
                break;
        }}
        System.out.println("请输入职工工号:");
        no=in.nextInt();
        setSupId(no);
		System.out.println("请输入职工电话号码:");
		setPhoneNum(in.next());
        // System.out.println("登记成功");
    }
    public void show()
    {
        // System.out.println("学生信息如下");
        System.out.println("姓名\t\t工号\t\t性别\t\t电话号码");
        System.out.println(getName()+"\t\t"+getSupId()+"\t\t"+getSex()+"\t\t"+getPhoneNum());
    }
    public void change()
    {
		while(true){
			System.out.println("请选择:\n1. 姓名\n2. 性别\n3. 电话号码\n0. 退出");
			Scanner in = new Scanner(System.in);
			switch (in.nextInt())
			{
				case 1:
					System.out.println("请输入职工姓名:");
					this.setName(in.next());
					break;
				case 2:
					System.out.println("请输入职工性别:\n1. 男\n2. 女");
					if(in.nextInt()==1)
						setSex(true);
					else
						setSex(false);
					break;
				case 3:
					System.out.println("请输入职工电话号码:");
					setPhoneNum(in.next());
					break;
				case 0:
					return;
				default:
					System.out.println("您的输入有误, 请重新输入!");
			}	
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
            System.out.println("查找工号是否存在异常!");//此处最后可以注释掉
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
            System.out.println("存储职工信息异常!");
            e.printStackTrace();
            return 1;

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
        if(!If_SuperNumberExists(num)){//是否找到管理员?(boolean)
            return 3;
        }
        else{
            try{
                String sql="select * from super where supId=?";//查找的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                // System.out.println("查找管理员");
                ResultSet rs=pstmt.executeQuery();//查找管理员,放入ResultSet内
                while(rs.next()){//打印管理员信息
					System.out.println("姓名\t\t工号\t\t性别\t\t电话号码");
                    System.out.println(
                            rs.getInt(1)+"\t\t"+
                                    rs.getString(2)+"\t\t"+
                                    rs.getString(3)+"\t\t"+
                                    rs.getString(4));
                    }
                    return 0;//正常打印了管理员信息
                }catch (SQLException e) {//删除过程中出现异常
                    System.out.println("尝试显示职工信息...\n查找职工信息异常!");//此处最后可以注释掉
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
                System.out.println("加载职工信息异常!");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
    }

    public int delete(int num){//给定学号删除对应管理员,成功返回0,失败返回1

        try{
            if(If_SuperNumberExists(num)){//找到了管理员

                // display(num);

                String sql="delete from super where supId=?";//删除的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                if(1==pstmt.executeUpdate()){//删除一个,返回0
                    return 0;
                }
                System.out.println("恭喜你发现彩蛋了, 我是DiDong.");
                return 1;
            }
            else{ //未找到管理员
                return 1;
            }
        } catch (SQLException e) {//删除过程中出现异常
            System.out.println("删除职工失败, 请检查该职工是否管理宿舍楼!");//此处最后可以注释掉
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
                System.out.println("更新管理员信息异常!");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
        //return 0;
    }
}

