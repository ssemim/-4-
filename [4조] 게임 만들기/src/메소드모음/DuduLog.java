package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import GUI게임.Dudu;
import dbutil.DBUtil;
import 객체모음.Dudug;
import 객체모음.Student;

public class DuduLog {
	private List<Dudug> list = new ArrayList<>();
	private int[] arr = new int[12];
	int result = 0;
	
	public void selectDudu(Student s) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM dudulog where studentid = ?;";
		List<Integer> number = new ArrayList<Integer>();
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getId());

			rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("studentId");
				int success = rs.getInt("success");
				int failure = rs.getInt("failure");
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
	
	public void printPlayLog() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM team4.dudulog order by play desc limit 1;";
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("play");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void insertDudu(Student s, int countAll, int count, List<Integer> number, HashMap<Integer, Integer> num) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO `team4`.`dudulog` (`studentId`, `success`, `failure`, `ttoe`, `stof`, `ftoo`) VALUES (?, ?, ?, ?, ?, ?);";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, s.getId());
			stmt.setInt(2, count);
			stmt.setInt(3, countAll - count);
			stmt.setInt(4, num.get(0));
			stmt.setInt(5, num.get(1));
			stmt.setInt(6, num.get(2));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
	
	public void duduGameLog(Student s, List<Integer> number, HashMap<Integer, Boolean> num, int index) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO `dudustatistics` (`log`, `id`, `number`, `result`) VALUES (?, ?, ?, ?);";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, result);
			stmt.setString(2, s.getId());
			stmt.setInt(3,number.get(index));
			stmt.setBoolean(4, num.get(index));
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
