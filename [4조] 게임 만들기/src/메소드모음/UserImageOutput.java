package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

// ui에 나오는 모든 유저의 캐릭터 이미지랑 포인트 반환하는 메소드
public class UserImageOutput {

	// 유저의 아이디를 파라미터로 받아 해당하는 유저가 가진 포인트를 반환하는 메소드
	public int printPoint(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("select point from student where id = ?;");
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int point = rs.getInt("point");
				return point;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return -1;
	}
}
