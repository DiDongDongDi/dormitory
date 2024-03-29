import java.util.*;
import Manage.StudentManage.*;
import Manage.Search.*;
import Manage.ExchangeDormitoryManage.*;
import Manage.BuildingManage.*;
import Manage.FloorManage.*;
import Manage.RoomManage.*;
import Manage.SuperiorManage.*;

public class DormitoryManage{

	StudentManage SM;
	Search search;
	ExchangeDormitoryManage EDM;
	BuildingManage BM;
	FloorManage FM;
	RoomManage RM;
	SuperiorManage SRM;

	DormitoryManage(){
		SM = new StudentManage();
		search = new Search();
		EDM = new ExchangeDormitoryManage();
		BM = new BuildingManage();
		FM = new FloorManage();
		RM = new RoomManage();
		SRM = new SuperiorManage();
	}
	
	public void start(){//进入宿舍管理系统
		System.out.println("欢迎进入宿舍管理系统!");
		while(true){
			try{
				System.out.println("请选择:");
				System.out.println("1. 学生管理");
				System.out.println("2. 宿舍楼管理");
				System.out.println("3. 调换宿舍管理");
				System.out.println("4. 查询");
				System.out.println("5. 职工管理");
				System.out.println("6. 宿舍楼层管理");
				System.out.println("7. 宿舍房间管理");
				System.out.println("0. 退出系统");
				
				Scanner sc = new Scanner(System.in);
				int op = sc.nextInt();
				switch(op){
					case 1:
						SM.start();
						break;
					case 2:
						BM.start();
						break;
					case 3:
						EDM.start();
						break;
					case 4:
						search.start();
						break;
					case 5:
						SRM.start();
						break;
					case 6:
						FM.start();
						break;
					case 7:
						RM.start();
						break;
					case 0:
						System.out.println("成功退出宿舍管理系统!");
						return;
					default:
						System.out.println("您的输入有误, 请重新输入!");
				}
			}
			catch(InputMismatchException e){
				System.out.println("您的输入与要求不符, 强制回到主界面!\n请谨慎输入!");
			}
		}
	}
}
