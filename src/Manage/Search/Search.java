package Manage.Search;

import java.util.*;

public class Search{
	public Search(){
		
	}
	
	public void start(){
		while(true){
			System.out.println("请选择查找方式:");
			System.out.println("1. 根据学号查找");
			System.out.println("2. 根据宿舍查找");
			System.out.println("0. 退出");

			Scanner sc = new Scanner(System.in);
			int op = sc.nextInt();
			switch(op){
				case 1:
					searchByStu();
					break;
				case 2:
					searchByDormitory();
					break;
				case 0:
					System.out.println("成功退出查找!");
					return;
				default:
					System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}

	private void searchByStu(){
		System.out.println("请输入需要查找的学号:");
		Scanner sc = new Scanner(System.in);
		int stuID = sc.nextInt();
		if(stuID < 0){
			System.out.println("您的输入有误!");
			return;
		}
		//下面直接根据学号在数据库中查找并显示出来
		//显示 学号 楼号 楼层号 房间号 床号
		//没有找到的话, 需要提示
		System.out.println("该学号对应的住宿信息为:");
		StudentAndRoom.displayInDB(stuID);
	}

	private void searchByDormitory(){
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入楼号:");
		int building = sc.nextInt();
		System.out.println("请输入楼层号:");
		int floor = sc.nextInt();
		System.out.println("请输入房间号:");
		int room = sc.nextInt();
		//根据以上这三个参数
		//显示 楼号 楼层号 房间号 床号 学号
		//没有找到的话, 需要提示
		System.out.println("该宿舍的学生信息为:");
		StudentAndRoom.displayInDB(building, floor, room);
	}
}
