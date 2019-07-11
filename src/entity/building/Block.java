package entity.building;

import java.util.ArrayList;
import java.util.Scanner;

public class Block {
    private int buildId;
    private ArrayList rommList=new ArrayList<room>();

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    void create_building()
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
