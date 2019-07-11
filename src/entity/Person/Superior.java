package entity.Person;

import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.util.Scanner;

public class Superior extends Person {
    private int no;
    public Superior(){}
    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }
    public void create_super()
    {
        Scanner in=new Scanner(System.in);
        int no;
        int sex;
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
        setNo(no);
        System.out.println("登记成功");
    }
}
