package entity.Person;
import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.util.Scanner;

public class Student extends Person {
    private int stuNo;
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
        int sex;
        System.out.println("欢迎办理入住手续！");
        System.out.print("请输入您的姓名：");
        String names=in.next();
        setName(names);
        tt:System.out.print("请输入您的性别 1.男性 2.女性");
        switch (in.nextInt())
        {
            case 1:
                setSex(true);
                break;
            case 2:
                setSex(false);
                break;
            default:
                System.out.println("输入错误");
                GOTO tt;
        }
        System.out.print("请输入您的学号：");
        no=in.nextInt();
        setStuNo(no);
        System.out.println("登记成功，正在查找房间");
    }
}
