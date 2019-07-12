package entity.building;

import Manage.RoomManage.RoomManage;

public class room {
    private int blockId;
    private int floorId;
    private int roomId;
    private int contain;
    private boolean []bedId;

    public room()
    {contain=4;}

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
    public room(int fI,int rI)
    {
        floorId=fI;
        roomId=rI;
        bedId=new boolean[4];
        for(int i=0;i<4;i++)
        {
            bedId[i]=false;//初始化，四个床都没有人住进来哪个床哪个true
        }
        contain=4;//一个宿舍最多住四个人，住进去一个--，出来一个++
    }
    public room(int bI, int fI, int rI)
    {
        blockId=bI;
        bedId=new boolean[4];
        for(int i=0;i<4;i++)
        {
            bedId[i]=false;//初始化，四个床都没有人住进来哪个床哪个true
        }
        contain=4;//一个宿舍最多住四个人，住进去一个--，出来一个++
        this.floorId=fI;
        this.roomId=rI;
    }

    public boolean[] getBedId() {
        return bedId;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getContain() {
        return contain;
    }

    public void setContain(int contain) {
        this.contain = contain;
    }
    public void gotoBed(int bedid)//调用这个函数来入住具体床位
    {
        if(bedId[bedid]==true)
        {
            System.out.println("这个床位已经有人了");
            return;
        }
        else
        bedId[bedid]=true;
        System.out.println("入住成功！");
    }
}
