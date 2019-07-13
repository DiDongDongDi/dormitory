package Manage.FloorManage;

import java.util.*;
import entity.Person.*;
import entity.building.*;
import DataBase.*;

public class FloorManage {
       public FloorManage(){}
        public  void start()   //start函数
        {
            while(true)
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("请选择:");
                System.out.println("1. 查询楼层状态");
                System.out.println("2. 更改楼层状态");
                System.out.println("0. 退出");
                int op = sc.nextInt();
                switch (op){
                    case 1:
                        searchFloor();
                        break;
                    case 2:
                        repairFloor();
                        break;
                    case 0:
                        System.out.println("成功退出宿舍楼层管理!");
                        return;
                    default:
                        System.out.println("您输入有误, 请重新输入!");
                        }
            }


        }

        private void searchFloor()  //进行start里的1操作
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入宿舍楼号:");
            int buildId = sc.nextInt();
            System.out.println("请输入宿舍楼层号:");
            int floorId = sc.nextInt();
            System.out.println("该楼层水机和灯的损坏状况为:");
            Floor floor = new Floor();
            int res = floor.display(buildId,floorId);
			if(res == 1||res==3)
				System.out.println("查找失败!");
        }

        private void repairFloor()  // 进行start里的2操作
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入宿舍楼号:");
            int buildId = sc.nextInt();
            System.out.println("请输入宿舍楼层号:");
            int floorId = sc.nextInt();
            Floor floor = new Floor(buildId,floorId);
            floor.load();
            floor.change();
            int res = floor.update();
            if(res==0)
                System.out.println("修改成功！");
            else
                System.out.println("修改失败！");
        }
}
