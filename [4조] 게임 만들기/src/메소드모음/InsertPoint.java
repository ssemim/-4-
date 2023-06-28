package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbutil.DBUtil;
import 객체모음.Student;

public class InsertPoint {
	public static int test(Student s, int point) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE `team4`.`student` SET `point` = `point` + ? WHERE (`id` = ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, point);
			stmt.setString(2, s.getId());
			System.out.println("저장되는 포인트 : " + point);
			return stmt.executeUpdate();

		} catch (SQLException e) {
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return -1;
	}
}
