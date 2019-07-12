package entity.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import DataBase.*;
import implement.implement;

public class Student extends Person implements implement{
    private int stuNo;
    public Student(){}
    public Student(int stuNo)
    {
        this.stuNo=stuNo;
    }
    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }
    public void create_student()
    {
        Scanner in=new Scanner(System.in);
        int no;
        boolean flg=true;
        // System.out.println("欢迎办理入住手续！");
        System.out.println("请输入学生姓名:");
        String names=in.next();
        setName(names);
        while(flg){
        System.out.println("请选择学生性别:\n1. 男\n2. 女");
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
                flg=true;
               break;
        }
        }
        System.out.println("请输入学生学号:");
        no=in.nextInt();
        setStuNo(no);
        // System.out.println("登记成功，正在查找房间");
    }

    private static boolean Student_IfNumberExists(int num){ //给定学号判断是否存在于数据库,静态,仅供操作数据库的方法内部使用
        try{
            PreparedStatement pstmt = null;
            String sql="select * from student where stuId=?";//查找的sql
            pstmt=DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,num);
            ResultSet rs=pstmt.executeQuery();

            return rs.next();//是否找到学生?(boolean)

        }catch (SQLException e) {
            System.out.println("查找学号是否存在过程出现异常");//此处最后可以注释掉
            //e.printStackTrace();                 //此处最后可以注释掉
            return false;
        }
    }
    public int store(){
        int success=0;
        try
        {
            PreparedStatement pstmt = null;

            //DataBase.getConnection().setAutoCommit(false);//关闭自动提交

            String sql="INSERT INTO student VALUES(?,?,?)";
            pstmt= DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,this.getStuNo());
            pstmt.setString(2,this.getName());
            pstmt.setString(3,this.getSex());


            pstmt.executeUpdate();

            //DataBase.getConnection().commit();//手动提交
            //DataBase.getConnection().setAutoCommit(true);//打开自动提交

        } catch (SQLException e) {
            // System.out.println("学号不能重复!");

            success=2;
            //e.printStackTrace();

            /*try {
                DataBase.getConnection().rollback();//出错就回滚
                DataBase.getConnection().setAutoCommit(true);//打开自动提交
            } catch (SQLException e1) {
                e1.printStackTrace();
            }*/

        }

        /*finally {
            if(success){
                System.out.println("学生添加成功.");
            }

        }*/
        return success;
    }
    public int search(int num){//todo:失败返回1未实现
        if(!(Student_IfNumberExists(num))){//号码不存在!
            return 2;
        }
        else{
            return 0;
        }
    }
    public int display(int stuID){//给定学号查找并打印--hwt
        //查找失败返回1
        //未找到返回3
        //直接从数据库查找显示即可

        try{
            PreparedStatement pstmt = null;
            String sql="select * from student where stuId=?";//查找的sql
            pstmt = DataBase.getConnection().prepareStatement(sql);
            pstmt.setInt(1,stuID);

            if(!pstmt.execute()){//是否找到学生?(boolean)
                return 3;
            }
            else{
                try{
                    sql="select * from student where stuId=?";//查找的sql
                    pstmt=DataBase.getConnection().prepareStatement(sql);
                    pstmt.setInt(1,stuID);
                    ResultSet rs=pstmt.executeQuery();//查找学生,放入ResultSet内
                    while(rs.next()){//打印学生信息
                        System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
                    }
                    return 0;//正常打印了学生信息
                }catch (SQLException e) {//删除过程中出现异常
                    System.out.println("尝试显示学生信息:查找学生时出现异常");//此处最后可以注释掉
                    //e.printStackTrace();                 //此处最后可以注释掉
                    return 1;
                }
            }

        }catch (SQLException e) {
            System.out.println("尝试显示学生信息:查找学生时出现异常_2");//此处最后可以注释掉
            //e.printStackTrace();                 //此处最后可以注释掉
            return 1;
        }

    }
    public int load(){// 给出仅有学号的学生,从数据库获得另外两个信息,(自己写的类传参默认为引用)
        int num=getStuNo();
        if(!Student_IfNumberExists(num)){//号码不存在!
            return 2;
        }
        else{
            try{
                String sql="select * from student where stuId=?";//查找的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                ResultSet rs=pstmt.executeQuery();//查找学生,放入ResultSet内
                rs.next();
                setName(rs.getString(2));
                setSex(rs.getString(3).equals("男"));//TODO:数据类型有错误 by郝文韬


                return 0;//修改成功
            }catch (SQLException e) {//删除过程中出现异常
                System.out.println("load学生时出现异常");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
    }
    public int delete(int num){//给定学号删除对应学生,成功返回0,失败返回1

        try{
            if(Student_IfNumberExists(num)){//找到了学生

                // display(num);

                String sql="delete from student where stuId=?";//删除的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setInt(1,num);
                if(1==pstmt.executeUpdate()){//删除一个,返回0
                    return 0;
                }
                System.out.println("搜索到好几个,这句话理论上不应该被执行");
                return 1;
            }
            else{ //未找到学生
                return 1;
            }
        } catch (SQLException e) {//删除过程中出现异常
            System.out.println("删除学生失败,请检查学生是否已经退出宿舍");//此处最后可以注释掉
            e.printStackTrace();                 //此处最后可以注释掉
            return 1;
        }
    }


    public int update() {//学号不变.修改数据库中性别和姓名  返回0为正常
        if(!(Student_IfNumberExists(getStuNo()))){//号码不存在!
            return 1;
        }
        else{
            try{
                String sql="update student set name=?,gender=? where stuId=?";//查找的sql
                PreparedStatement pstmt=DataBase.getConnection().prepareStatement(sql);
                pstmt.setString(1,getName());
                pstmt.setString(2,getSex());
                pstmt.setInt(3,getStuNo());
                if (1==pstmt.executeUpdate()){
                    return 0;
                }
                else{
                    return 1;
                }


            }catch (SQLException e) {//删除过程中出现异常
                System.out.println("load学生时出现异常");//此处最后可以注释掉
                e.printStackTrace();                 //此处最后可以注释掉
                return 1;
            }
        }
        //return 0;
    }
    public void show()
    {
        // System.out.println("学生信息如下");
        System.out.println("姓名\t\t学号\t\t性别");
        System.out.println(getName()+"\t\t"+getStuNo()+"\t\t"+getSex());
    }
    public void change()
    {
		while(true){
			System.out.println("请选择:\n1. 姓名\n2. 性别\n0. 退出");
			Scanner in = new Scanner(System.in);
			switch (in.nextInt())
			{
				case 1:
					System.out.println("请输入学生姓名:");
					this.setName(in.next());
					break;
				case 2:
					System.out.println("请输入学生性别:\n1. 男\n2. 女");
					if(in.nextInt()==1)
						setSex(true);
					else
						setSex(false);
					break;
				case 0:
					return;
				default:
					System.out.println("您的输入有误, 请重新输入!");
			}	
		}
    }
}
