import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dbutil.DBUtil;
import 메소드모음.Login;

public class Test {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO `team4`.`equipment` (`studentId`, `itemNo`) VALUES (? , '1');";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "ee");
			stmt.setString(1, "aa");
			int i = stmt.executeUpdate();
			System.out.println(i);
		} catch (SQLException e) {
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
}
