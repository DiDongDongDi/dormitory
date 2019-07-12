package Manage.FloorManage;

import java.util.*;
import entity.Person.*;
import entity.*;
import DataBase.*;

public class FloorManage {
       public FloorManage(){}
        public  void start()   //start函数
        {
            while(true)
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("请选择你要对楼层进行的操作：");
                System.out.println("1.查询楼层水机和灯的损坏个数");
                System.out.println("2.维修楼层已损坏水机和灯");
                System.out.println("0.退出");
                int op = sc.nextInt();
                switch (op){
                    case 1:
                        searchFloor();
                        break;
                    case 2:
                        repairFloor();
                        break;
                    case 0:
                        System.out.println("成功退出楼层操作！");
                        return;
                    default:
                        System.out.println("输入有误，请重新输入！");
                        }
            }


        }

        private void searchFloor()  //进行start里的1操作
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你想操作的楼号：");
            int buildId = sc.nextInt();
            System.out.println("请输入你想操作的楼层号：");
            int floorId = sc.nextInt();
            System.out.println("你想要查询的楼层水机和灯的损坏个数为：");
//            Floor floor = new Floor();
//            floor.display(buildId,floorId);
        }

        private void repairFloor()  // 进行start里的2操作
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你想操作的楼号：");
            int buildId = sc.nextInt();
            System.out.println("请输入你想操作的楼层号：");
            int floorId = sc.nextInt();
          /*  Floor floor = new Floor(buildId,floorId);
            floor.load();
            floor.change();
            int res = floor.update();
            if(res==1)
                System.out.println("维修成功！");
            else
                System.out.println("维修失败！");*/
        }
}
