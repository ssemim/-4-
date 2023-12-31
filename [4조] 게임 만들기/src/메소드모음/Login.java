package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dbutil.DBUtil;
import 객체모음.Student;

public class Login {

	public int insert(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "insert into student (id, password, school) values (?,?,?);";

		try {
			if (!validateId(student.getId())) {
				return -2;
			}

			if (!validatePassword(student.getPassword())) {
				return -3;
			}

			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, student.getId());
			stmt.setString(2, student.getPassword());
			stmt.setString(3, student.getSchool());

			// SQL 쿼리 실행
			int rowsAffected = stmt.executeUpdate();

			// 회원 가입 성공 여부 확인
			if (rowsAffected > 0) {
				EquipmentItem.insertBasicInven(student, conn);
				EquipmentItem.insertBasicEqui(student, conn);
				return 1;
			} else {
				return -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return -1;
	}

	public Student login(String id, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from student where id = ? and password = ?";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, id);
			stmt.setString(2, password);

			rs = stmt.executeQuery();
			while (rs.next()) {
				String inputId = rs.getString("id");
				String school = rs.getString("school");
				int point = rs.getInt("point");
				Student student = new Student(inputId, school, point);
				return student;
			}

			insertBasicCharacter(id);
			insertBasicWallpaper(id);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return null;
	}

	public static boolean validateId(String id) {
		// 영소문자, 대문자, 숫자로만 구성되어야 함
		Pattern pattern = Pattern.compile("[A-Za-z0-9]{2,4}");
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}

	// 비밀번호 패턴 검증 메소드
	private static boolean validatePassword(String password) {
		// 영소문자, 대문자, 숫자, 특수문자가 최소 1개 이상 포함되어야 함
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*[!@#$%^&*]+{8,20}");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public boolean duplication(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// 데이터베이스 연결
			conn = DBUtil.getConnection();

			// SQL 쿼리 작성
			String sql = "select * from student where id = ? ";

			// SQL 쿼리 실행
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			rs = stmt.executeQuery();

			// 결과 확인
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return false;
	}

	private void insertBasicCharacter(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO equipment (`studentId`, itemNo) VALUES (?, 1)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	private void insertBasicWallpaper(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO equipment (`studentId`, itemNo) VALUES (?, 2)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
}
