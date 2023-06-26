package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;
import 객체모음.Student;

// 타입별 뽑기 메소드
public class PickItem {
	Student s;
	String type;

	public PickItem(Student s, String type) {
		super();
		this.s = s;
		this.type = type;
	}

	// 타입나누는 곳
	public List<Integer> random() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Integer> l = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "select * from item where type = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, this.type);
			rs = stmt.executeQuery();
			while (rs.next()) {
				l.add(rs.getInt("no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return l;
	}

	/**
	 * 뽑기 메소드, 자동 인설트됨
	 * 
	 * @return 아이템 pk 번호
	 */
	public int pickItem() {
		List<Integer> l = random();
		int a = l.size();
		int ran = (int) (Math.random() * a);
		int q = l.get(ran);
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "insert into inventory (studentid, itemNo) values (?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getId());
			stmt.setInt(2, q);
			stmt.executeUpdate();
			return q;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return -1;
	}
}