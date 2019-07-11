import DataBase.DataBase;

public class Main{
	public static void main(String[] args){
		DataBase.DataBase_Connect();
		DormitoryManage DM = new DormitoryManage();
		DM.start();
		DataBase.DataBase_Disconnect();
	}
}
