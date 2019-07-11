package entity.building;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Block {
    private static int buildId=1;
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

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public int getSuperId() {
        return superId;
    }

    public void WriteProblemFile(String str)
    {
        String filename="res/Problem"+buildId+".txt";
        try{
        problemFile=new File(filename);
        if(!problemFile.exists())
            problemFile.createNewFile();
            FileWriter fr=new FileWriter(filename,true);
            BufferedWriter bw=new BufferedWriter(fr);
            bw.write(str+"\r\n");
            bw.flush();
            bw.close();
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println("写入失败！");
        }
        catch (Exception e)
        {
            System.out.println("未知错误！");
        }
    }
    public void loadProblemFile()
    {
        System.out.println(getBuildId()+"号大楼问题板如下");
        try{
        FileReader fr=new FileReader(problemFile);
        BufferedReader br=new BufferedReader(fr);
        String str;
        while((str=br.readLine())!=null)
            System.out.println(str);
        }
        catch (Exception e)
        {
            System.out.println("未知错误！");
        }
    }
   public void create_building()//自动创建大楼
    {
        Scanner in=new Scanner(System.in);
        setBuildId(getBuildId()+1);
        System.out.println("请输入这栋楼有多少楼层");
        setMaxFloor(in.nextInt());
        System.out.println("请输入一层有多少房间");
        setMaxRoom(in.nextInt());
        for(int i=1;i<=getMaxFloor();i++)
        {
            for(int j=1;j<=getMaxRoom();j++)
                rommList.add(new room(i,j));
        }
    }


}
