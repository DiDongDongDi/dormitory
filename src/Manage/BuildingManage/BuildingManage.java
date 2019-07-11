package Manage.BuildingManage;

import java.util.*;

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

				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

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
		//
	}


}
