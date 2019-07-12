package Manage.RoomManage;

import java.util.*;
import entity.building.*;
public class RoomManage {
   public RoomManage(){}

    public void start()
    {
        while (true)
        {
            System.out.println("请输入你要对房间进行的操作：");
            System.out.println("1.查询宿舍电费余额和卫生情况：");
            System.out.println("2.修改宿舍电费余额与卫生评分：");
            System.out.println("0.退出");
            Scanner sc = new Scanner(System.in);
            int op = sc.nextInt();
            switch (op)
            {
                case 1:
                    searchRoom();
                    break;
                case 2:
                    modifiRoom();
                    break;
                case 0:
                    return;
                 default:
                     System.out.println("输入有误，请重新输入");
            }
        }
    }

    private void searchRoom() //实现start 1操作
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你想操作的楼号：");
        int buildId = sc.nextInt();
        System.out.println("请输入你想操作的楼层号：");
        int floorId = sc.nextInt();
        System.out.println("请输入你想操作的房间号:");
        int roomId = sc.nextInt();
        room room = new room();
        int res=room.display(buildId,floorId,roomId);
        if(1==res){
            System.out.println("查找出现异常");
        }
        if(3==res){
            System.out.println("您输入的房间不存在");
        }

    }

    private void modifiRoom() //实现 start 2 操作
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你想操作的楼号：");
        int buildId = sc.nextInt();
        System.out.println("请输入你想操作的楼层号：");
        int floorId = sc.nextInt();
        System.out.println("请输入你想操作的房间号:");
        int roomId = sc.nextInt();
        room room = new room(buildId,floorId,roomId);
        room.load();
        room.change();
        int res = room.update();
        if(res==2){
            System.out.println("您输入的房间不存在");
        }
        if(res==0)
            System.out.println("修改成功！");
        else
            System.out.println("修改失败！");

    }
}
