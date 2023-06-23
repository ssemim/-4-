import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dbutil.DBUtil;

public class GameLogin implements GameInterface{

	@Override
	public int insert(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "insert into student (id, password, school) values (?,?,?);";
		
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, student.getId());
			stmt.setString(2, student.getPassword());
			stmt.setString(3, student.getSchool());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return 0;
	}

	@Override
	public Student login(String id, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select `password` from student where id = ? and password = ?";
		
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			while(rs.next())	{
				String inputId = rs.getString("id");
				String school = rs.getString("school");
				int point = rs.getInt("point");
				Student student = new Student(inputId, school, point);
				return student;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		
		return null;
	}

	@Override
	public int 뽑기() {
		return 0;
	}

	@Override
	public List<School> schoolRangking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> classRangking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> studentRangking() {
		// TODO Auto-generated method stub
		return null;
	}
}

