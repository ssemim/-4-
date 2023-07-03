package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GUI게임.Dudu;
import dbutil.DBUtil;
import 객체모음.Dudug;
import 객체모음.Student;

public class DuduLog {
	private List<Dudug> list = new ArrayList<>();
	private int[] arr = new int[12];
	
	public void selectDudu(Student s, int countAll, int count) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM dudulog where studentid = ?;";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getId());

			rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("studentId");
				int success = count;
				int failure = countAll - count;
				
				list.add(new Dudug(id, success, failure));
			}
			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void insertDudu(Student s, int countAll, int count, List<Integer> number) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into dudulog (studentId, success, failure) values (?, ?, ?);";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, s.getId());
			stmt.setInt(2, count);
			stmt.setInt(3, countAll - count);
			
			stmt.executeUpdate();
			sumResult(number);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
	
	public void sumResult(List<Integer> number) {
		for(int i = 0 ; i <= number.size(); i++) {
			for (int num : number) {
				if(num == i) {
					arr[i]++;
				}
			}
		}
		
		for(int i = 0 ; i < 12 ; i++) {
			System.out.println(i + "의 개수 : " + arr[i]);
		}
	}
}
