package Manage.BuildingManage;

import java.util.*;
import entity.building.*;

public class MessageManage{
	public static void start(int buildId){
		Block building = new Block(buildId);
		while(true){
			System.out.println("请选择:");
			System.out.println("1. 发布消息");
			System.out.println("2. 查看历史消息");
			System.out.println("0. 退出");
			Scanner sc = new Scanner(System.in);
			int op = sc.nextInt();
			switch(op){
				case 1:
					building.postMessage();
					break;
				case 2:
					building.showHistoryMessage();
					break;
				case 0:
					System.out.println("成功退出宿舍楼消息管理!");
					return;
				default:
					System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}
}
