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
			System.out.println("楼号\t\t楼层号\t\t水机损坏数\t\t灯损坏数");
			if (rs.next()) {
				rs.previous();
				while (rs.next()) {
					int BuildId = rs.getInt("buildId");
					int FloorId = rs.getInt("floorId");
					int BrokenwaterNum = rs.getInt("brokenWaterNum");
					int BrokenLightNum = rs.getInt("brokenLightNum");
					System.out.println(BuildId + "\t\t" + FloorId + "\t\t" + BrokenwaterNum + "\t\t" + BrokenLightNum);//输出信息
				}
			} else
				System.out.println("        "); //输出空格表示没有查到
		} catch (SQLException e) {
			System.out.println("查询异常!");
		}
	}

	public static void showRoomStatus(int buildId) {
		try {
			PreparedStatement pstmt = null;
			String sql = "select * from rooms where buildId=?";//查找的sql
			pstmt = DataBase.getConnection().prepareStatement(sql);
			pstmt.setInt(1, buildId);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("楼号\t\t楼层号\t\t电费余额\t\t卫生分数");
			if (rs.next()) {
				rs.previous();
				while (rs.next()) {
					int BuildId = rs.getInt("buildId");
					int FloorId = rs.getInt("floorId");
					int RoomId = rs.getInt("roomId");
					double BrokenwaterNum = rs.getDouble("elecBillBala");
					double BrokenLightNum = rs.getDouble("healScore");
					System.out.println(BuildId + "\t\t" + FloorId + "\t\t" +RoomId+"\t\t"+ BrokenwaterNum + "\t\t" + BrokenLightNum);//输出信息
				}
			} else
				System.out.println("        "); //输出空格表示没有查到
		} catch (SQLException e) {
			System.out.println("查询异常!");
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
			System.out.println("查询异常!");
			return " ";
		}
	}
}
