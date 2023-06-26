package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pack.DBUtil;
import 객체모음.Student;

// 타입별 뽑기 메소드
public class Random {
	Student s = new Student();
	int[] st;

	// 타입나누는 곳
	public int[] random(String type) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "select * from item type = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, type);
			rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				st[count] = rs.getInt("no");
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return st;
	}

	// 사용자 인벤에 넣는것
	public void st(int[] st) {
		int a = st.length;
		int ran = (int) Math.random() * a;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "insert into inventory (id, itemNo) values (?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getId);
			stmt.setInt(2, a);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

	}
}