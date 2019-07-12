package DataBase;
import java.sql.*;

public class BuildingStatus {
	public static void showFloorStatus(int buildId) {
		try {
			PreparedStatement pstmt = null;
			String sql = "select * from floors where buildId=?";//查找的sql
			pstmt = DataBase.getConnection().prepareStatement(sql);
			pstmt.setInt(1, buildId);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("BuildId\t\t\tFloorId\t\t\tBrokenwaterNum\t\t\tBrokenLightNum");
			if (!rs.wasNull()) {
				while (rs.next()) {
					int BuildId = rs.getInt("buildId");
					int FloorId = rs.getInt("floorId");
					int BrokenwaterNum = rs.getInt("brokenWaterNum");
					int BrokenLightNum = rs.getInt("brokenLightNum");
					System.out.println(BuildId + "\t\t\t" + FloorId + "\t\t\t" + BrokenwaterNum + "\t\t\t" + BrokenLightNum);//输出信息
				}
			} else
				System.out.println("      "); //输出空格表示没有查到
		} catch (SQLException e) {
			System.out.println("查询异常！");
		}
	}

	public static void showRoomStatus(int buildId) {
		try {
			PreparedStatement pstmt = null;
			String sql = "select * from rooms where buildId=?";//查找的sql
			pstmt = DataBase.getConnection().prepareStatement(sql);
			pstmt.setInt(1, buildId);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("BuildId\t\t\tFloorId\t\t\telecBill\t\t\thealScore");
			if (!rs.wasNull()) {
				while (rs.next()) {
					int BuildId = rs.getInt("buildId");
					int FloorId = rs.getInt("floorId");
					int RoomId = rs.getInt("roomId");
					double BrokenwaterNum = rs.getDouble("elecBillBala");
					double BrokenLightNum = rs.getDouble("healScore");
					System.out.println(BuildId + "\t\t\t" + FloorId + "\t\t\t" +RoomId+"\t\t\t"+ BrokenwaterNum + "\t\t\t" + BrokenLightNum);//输出信息
				}
			} else
				System.out.println("      "); //输出空格表示没有查到
		} catch (SQLException e) {
			System.out.println("查询异常！");
		}
	}
	public static String getGender(int buildId)
	{
		try {
			String gender = " ";
			PreparedStatement pstmt = null;
			String sql = "select * from buildings where buildId=?";//查找的sql
			pstmt = DataBase.getConnection().prepareStatement(sql);
			pstmt.setInt(1, buildId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				return gender = rs.getString("gender");
			else
				return gender;
		}catch (SQLException e)
		{
			System.out.println("查询异常");
			return " ";
		}
	}
}
