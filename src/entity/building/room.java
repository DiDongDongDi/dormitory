package entity.building;

public class room {
    private int floorId;
    private int roomId;
    private int contain;
    room()
    {contain=4;}
    room(int fI,int rI)
    {
        contain=4;
        this.floorId=fI;
        this.roomId=rI;
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
