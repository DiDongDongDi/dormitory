package entity.building;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Block {
    private static int buildId;
    private int maxFloor;
    private int maxRoom;
    private int superId;
    private File problemFile;
    private File postFile;
    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getMaxRoom() {
        return maxRoom;
    }

    public void setMaxRoom(int maxRoom) {
        this.maxRoom = maxRoom;
    }

    private ArrayList rommList=new ArrayList<room>();

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

   public void create_building()//自动创建大楼
    {
        Scanner in=new Scanner(System.in);
        int floorNum=0;
        int roomNum=0;
        System.out.println("请输入这栋楼有多少楼层");
        floorNum=in.nextInt();
        System.out.println("请输入一层有多少房间");
        roomNum=in.nextInt();
        for(int i=1;i<=floorNum;i++)
        {
            for(int j=1;j<=roomNum;j++)
                rommList.add(new room(i,j));
        }
    }

}
