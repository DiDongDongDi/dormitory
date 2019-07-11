package Manage.BuildingManage;

import java.util.*;
import entity.factory.*;
import entity.building.*;

public class BuildingManage{
	public BuildingManage(){
		
	}

	public void start(){
		System.out.println("请选择:");
		System.out.println("1. 新建宿舍楼");
		System.out.println("2. 拆除宿舍楼");
		System.out.println("3. 修改宿舍楼信息");
		System.out.println("4. 查询宿舍楼信息");
		System.out.println("5. 宿舍楼消息");
		System.out.println("0. 退出");

		Scanner sc = new Scanner(System.in);
		int op = sc.nextInt();
		switch(op){
			case 1:
				addBuilding();
				break;
			case 2:
				deleteBuilding();
				break;
			case 3:
				changeBuilding();
				break;
			case 4:
				searchBuilding();
				break;
			case 5:
				messageManage();
				break;
			case 0:
				System.out.println("成功退出宿舍楼管理!");
				return;
			default:
				System.out.println("您的输入有误, 请重新输入!");
		}
	}

	private void addBuilding(){
		//调用创建一个宿舍楼的函数, 宿舍楼的具体属性值, 在其中输入
		//属性值包括 管理者的id 男舍还是女舍 楼层数 每层的房间数
		//返回一个宿舍楼的对象
		//宿舍楼的楼号不需要指定是因为数据库会自增
		//
		//调用store函数, 将数据加载到building数据库
		//除此之外, student_and_room floors rooms 需要添加新的内容
		//即新的空间
		factory fac = new factory();
		Block building = fac.addBlock();
		System.out.println("您新建的宿舍楼的信息如下:");
		//building.show();
		System.out.println("确认新建吗?(y/n)");
		Scanner sc = new Scanner(System.in);
		String choose = sc.next();
		while(true){
			if(choose.equals("y")){
				/* if(building.store()==0) */
					// System.out.println("新建宿舍楼成功!");
				// else
					// System.out.println("新建宿舍楼失败!");
				break;
			}
			else if(choose.equals("n")){
				System.out.println("取消操作成功!");
				break;
			}
			else{
				System.out.println("您的输入有误, 请重新输入!");
			}
		}
	}

	private void deleteBuilding(){
		System.out.println("请输入需要拆除的宿舍楼楼号:");
		Scanner sc = new Scanner(System.in);
		int buildId = sc.nextInt();
		if(buildId<=0){
			System.out.println("您的输入有误!");
			return;
		}
		//传入楼号, 删除这栋宿舍楼的所有数据
		Block building = new Block();
		//int res = building.search(buildId);
		//if(res == 0){
		//	System.out.println("该栋宿舍楼的信息如下:");
		//	building.display(buildId);
			System.out.println("确认拆除吗?(y/n)");
			String choose = sc.next();
			while(true){
				if(choose.equals("y")){
				//	if(building.delete(buildId) == 0)
				//		System.out.println("拆除宿舍楼成功!");
				//	else
				//		System.out.println("拆除宿舍楼失败!");
					break;
				}
				else if(choose.equals("n")){
					System.out.println("取消操作成功!");
					break;
				}
				else{
					System.out.println("您的输入有误, 请重新输入!");
				}
			}
		//}
		//else
		//	System.out.println("未找到该楼号对应的宿舍楼!");
	}

	private void changeBuilding(){
		System.out.println("请输入需要修改信息的宿舍楼的楼号:");
		Scanner sc = new Scanner(System.in);
		int buildId = sc.nextInt();
		if(buildId<=0){
			System.out.println("您的输入有误!");
			return;
		}
		//先加载数据库内容到内存
		//再修改
		//再update
		// Block building = new Block(buildId);
		//int res = building.load();
		//if(res == 0){
		//	building.change();
		//	System.out.println("修改后的信息如下:");
		//	building.show();
			System.out.println("确认修改吗?(y/n)");
			String choose = sc.next();
			while(true){
				if(choose.equals("y")){
				//	if(building.update() == 0)
				//		System.out.println("修改宿舍楼信息成功!");
				//	else
				//		System.out.println("修改宿舍楼信息失败!");
					break;
				}
				else if(choose.equals("n")){
					System.out.println("取消操作成功!");
					break;
				}
				else{
					System.out.println("您的输入有误, 请重新输入!");
				}
			}
		//}
		//else if(res ==1)
		//	System.out.println("加载该楼号对应的宿舍楼信息失败!");
		//else
		//	System.out.println("未找到该楼号对应的宿舍楼!");
	}

	private void searchBuilding(){
		System.out.println("请输入需要查找的宿舍楼信息的楼号:");
		Scanner sc = new Scanner(System.in);
		int buildId = sc.nextInt();
		if(buildId<=0){
			System.out.println("您的输入有误!");
			return;
		}
		Block building = new Block();
		System.out.println("您查找的宿舍楼信息如下:");
		//int res = building.display(buildId);
		//if(res == 1)
		//	System.out.println("查找失败!");
		//else if(res == 2)
		//	System.out.println("未找到该楼号对应的宿舍楼!");
	}

	private void messageManage(){
		System.out.println("请输入需要管理消息的宿舍楼楼号:");
		Scanner sc = new Scanner(System.in);
		int buildId = sc.nextInt();
		if(buildId<=0){
			System.out.println("您的输入有误!");
			return;
		}
		Block building = new Block();
		//if(building.search()==0)
			MessageManage.start(buildId);
		//else
			System.out.println("未找到该楼号对应的宿舍楼!");
		
	}
}
