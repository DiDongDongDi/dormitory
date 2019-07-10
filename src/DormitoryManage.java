import java.util.*;
import StudentManage.*;
import Search.*;

public class DormitoryManage{

	StudentManage SM;
	Search search;

	DormitoryManage(){
		SM = new StudentManage();
		search = new Search();
	}
	
	public void start(){//进入宿舍管理系统
		System.out.println("欢迎进入宿舍管理系统!");
		while(true){
			System.out.println("请选择:");
			System.out.println("1. 学生管理");
			System.out.println("2. 宿舍楼管理");
			System.out.println("3. 调换宿舍管理");
			System.out.println("4. 查询");
			System.out.println("0. 退出系统");
			
			Scanner sc = new Scanner(System.in);
			int op = sc.nextInt();
			switch(op){
				case 1:
					SM.start();
					break;
				case 2:

					break;

				case 3:

					break;
				case 4:
					search.start();
					break;
				case 0:
					System.out.println("成功退出宿舍管理系统!");
					return;
				default:
					System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}
}
