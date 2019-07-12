package entity.building;

public class Floor {
    private int buildId;
    private int floorId;
    public Floor(int bI,int fI)
    {
        this.buildId=bI;
        this.floorId=fI;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
    
}
