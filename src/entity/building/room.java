package entity.building;

public class room {
    private int floorId;
    private int roomId;
    private int contain;
    private boolean []bedId;
    room()
    {contain=4;}
    room(int fI,int rI)
    {
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
}
