package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;
import 객체모음.RspData;

public class RspGG {
	public int selectPlayLog(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select no from gamelog order by no desc limit 1;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("no");
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
		return -1;
	}
	
	public int insert(RspData rd, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO `team4`.`rsplog` (`playLog`, `playTime`, `studentId`, `myChoice`, `comChoice`) "
					+ "VALUES (?, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rd.getPlayLog());
			stmt.setInt(2, rd.getPlayTime());
			stmt.setString(3, rd.getStudentId());
			stmt.setString(4, rd.getMyChoice());
			stmt.setString(5, rd.getComChoice());
			return stmt.executeUpdate();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
	}
}
