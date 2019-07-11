package Manage.ExchangeDormitoryManage;

import java.util.*;
import entity.Person.*;
import DataBase.*;

public class ExchangeDormitoryManage{
	public ExchangeDormitoryManage(){
		
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
					allocateDormitory();
					break;
				case 2:
					exchangeDormitory();
					break;
				case 3:
					deleteDormitory();
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
		int res = allocateDormitoryInDB(stuID);
		if(res==0)
			System.out.println("成功分配宿舍!");
		else if(res==1)
			System.out.println("找不到该学号对应的学生信息!");
		else if(res==2)
			System.out.println("该学号已分配宿舍!");
		else if(res==3)
			System.out.println("已经没有空床位可分配!");
	}

	private int allocateDormitoryInDB(int stuID){
		//查询学生信息数据库, 确认在其中, 否则找不到学生信息, 无法分配宿舍
		//
		//确保该学号还未分配宿舍
		//
		//根据该学号的性别, 分配对应楼层中的任意一个宿舍床位
		//成功分配返回0, 同时显示分配的信息 学号 楼号 楼层号 房间号 床位号
		//找不到学生信息返回1
		//该学号已经分配宿舍返回2
		//已经没有空床位了返回3
		Student stu = new Student(stuID);
		if(stu.load()==0){
			if(StudentAndRoom.isAllocated(stuID))
				return 2;
			else{
				if(StudentAndRoom.allocate(stu)){
					System.out.println("分配的宿舍信息如下:");
					StudentAndRoom.displayInDB(stuID);
					return 0;
				}
				else
					return 3;
			}
		}
		else
			return 1;
	}
	
	private void exchangeDormitory(){
		System.out.println("请输入需要调换宿舍的学生的学号:");
		Scanner sc = new Scanner(System.in);
		int stuID = sc.nextInt();
		if(stuID<0){
			System.out.println("您的输入有误!");
			return;
		}
		Student stu = new Student(stuID);
		int res = stu.load();
		if(res==1){
			System.out.println("加载学生信息失败!");
			return;
		}
		else if(res==2){
			System.out.println("未找到该学号对应的学生信息!");
			return;
		}
		System.out.println("请输入目标宿舍楼号:");
		int building = sc.nextInt();
		System.out.println("请输入目标宿舍楼层号:");
		int floor = sc.nextInt();
		System.out.println("请输入目标宿舍房间号:");
		int room = sc.nextInt();
		System.out.println("请输入目标宿舍床位号:");
		int bed = sc.nextInt();

		res = exchangeDromitoryInDB(stu, building, floor, room, bed);
		if(res==0)
			System.out.println("成功交换宿舍!");
		else if(res==1)
			System.out.println("该学号还未分配宿舍!");
		else if(res==2)
			System.out.println("调换的目标宿舍不存在!");
		else if(res==3)
			System.out.println("该学号性别与目标宿舍要求性别不符!");
		else if(res==4)
			System.out.println("取消操作成功!");
		else if(res==5)
			System.out.println("调换宿舍失败!");
	}

	private int exchangeDromitoryInDB(Student stu, int building, int floor, int room, int bed){
		//成功交换宿舍返回0
		//该学号还没有分配宿舍返回1
		//目标宿舍不存在返回2
		//性别不符返回3
		//取消操作返回4
		//调换失败返回5
		//区分目标床位是否已经有人
		int stuID = stu.getStuNo();
		if(StudentAndRoom.isAllocated(stuID)){
			if(StudentAndRoom.isExists(building, floor, room, bed)){
				if(stu.getSex().equals()){//TODO 性别对比
					System.out.println("该学号床位信息为:");
					StudentAndRoom.displayInDB(stuID);
					System.out.println("目标床位信息为:");
					StudentAndRoom.displayInDB(building, floor, room, bed);
					System.out.println("确认是否调换?(y/n)");
					Scanner sc = new Scanner(System.in);
					String choose = sc.next();
					while(true){
						if(choose.equals("y")){
							if(StudentAndRoom.exchange(stuID, building, floor, room, bed))//由于交换前已经确认性别是吻合的, 所以不需要传Student对象
								return 0;
							else
								return 5;
						}
						else if(choose.equals("n")){
							return 4;
						}
						else{
							System.out.println("您的输入有误, 请重新输入!");
						}
					}
				}
				return 3;
			}
			else
				return 2;
		}
		else
			return 1;
	}

	private void deleteDormitory(){
		System.out.println("请输入需要离开宿舍的学号:");
		Scanner sc = new Scanner(System.in);
		int stuID = sc.nextInt();
		if(stuID<0){
			System.out.println("您的输入有误!");
			return;
		}
		int res = deleteDormitoryInDB(stuID);
		if(res==0)
			System.out.println("删除成功!");
		else if(res==1)
			System.out.println("该学号还未分配宿舍!");
		else if(res==2)
			System.out.println("删除失败!");
		else if(res==3)
			System.out.println("取消操作成功!");
	}

	private int deleteDormitoryInDB(int stuID){
		//成功删除返回0
		//未分配宿舍返回1
		//删除失败返回2
		//取消操作返回3
		if(StudentAndRoom.isAllocated(stuID)){
			System.out.println("您要删除的住宿信息为:");
			StudentAndRoom.displayInDB(stuID);
			System.out.println("确认删除吗?(y/n)");
			Scanner sc = new Scanner(System.in);
			String choose = sc.next();
			while(true){
				if(choose.equals("y")){
					if(StudentAndRoom.delete(stuID))
						return 0;
					else
						return 2;
				}
				else if(choose.equals("n")){
					return 3;
				}
				else{
					System.out.println("您的输入有误, 请重新输入!");
				}
			}
		}
		else
			return 1;	
	}
}
