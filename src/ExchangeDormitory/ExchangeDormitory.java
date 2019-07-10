package ExchangeDormitory;

import java.util.*;

public class ExchangeDormitory{
	public ExchangeDormitory(){
		
	}

	public void start(){
		while(true){
			System.out.println("请选择:");
			System.out.println("1. 分配宿舍");
			System.out.println("2. 调换宿舍");
			System.out.println("3. 离开宿舍");
			System.out.println("0. 退出");	

			Scanner sc = new Scanner(System.in);
			int op = sc.nextInt();
			switch(op){
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:

					break;
				case 0:
					System.out.println("成功退出调换宿舍管理!");
					return;
				default:
					System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}

	private void allocateDormitory(){
		System.out.println("请输入需要分配宿舍的学号:");
		Scanner sc = new Scanner(System.in);
		int stuID = sc.nextInt();
		if(stuID<0){
			System.out.println("您的输入有误!");
			return;
		}
		int res = allocateDormitoryFromDB(stuID);
		if(res==0)
			System.out.println("成功分配宿舍!");
		else if(res==1)
			System.out.println("找不到该学号对应的学生信息!");
		else if(res==2)
			System.out.println("该学号已分配宿舍!");
		else if(res==3)
			System.out.println("已经没有空床位可分配!");
	}

	private int allocateDormitoryFromDB(int stuID){
		//查询学生信息数据库, 确认在其中, 否则找不到学生信息, 无法分配宿舍
		//
		//确保该学号还未分配宿舍
		//
		//根据该学号的性别, 分配对应楼层中的任意一个宿舍床位
		//成功分配返回0, 同时显示分配的信息 学号 楼号 楼层好 房间号 床位号
		//找不到学生信息返回1
		//该学号已经分配宿舍返回2
		//已经没有空床位了返回3
		
	}
}
