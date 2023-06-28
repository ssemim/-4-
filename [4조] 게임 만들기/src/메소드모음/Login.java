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
//			if (!validateId(student.getId())) {
//				System.out.println("아이디는 영소문자, 대문자, 숫자로만 구성되어야 합니다.");
//				return -1;
//			}
//			if (student.getId().length() < 8 || student.getId().length() > 15) {
//				System.out.println("아이디는 8 ~ 15 자리수로 설정해야 합니다.");
//			}
//
//			if (!validatePassword(student.getPassword())) {
//				System.out.println("비밀번호는 영소문자, 대문자, 숫자, 특수문자를 포함해야 합니다.");
//				return -1;
//			}
//			if (student.getPassword().length() < 8 || student.getPassword().length() > 20) {
//				System.out.println("비밀번호는 8 ~ 20 자리수로 설정해야 합니다.");
//				return -1;
//			}

			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, student.getId());
			stmt.setString(2, student.getPassword());
			stmt.setString(3, student.getSchool());

			// SQL 쿼리 실행
			int rowsAffected = stmt.executeUpdate();

			// 회원 가입 성공 여부 확인
			if (rowsAffected > 0) {
				System.out.println("회원 가입이 완료되었습니다.");
				EquipmentItem.insertBasicInven(student, conn);
				EquipmentItem.insertBasicEqui(student, conn);
				return 1;
			} else {
				System.out.println("회원 가입에 실패했습니다.");
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
		Pattern pattern = Pattern.compile(id);
		Matcher matcher = pattern.matcher("^[a-z]$");
		System.out.println(matcher.matches());
		return matcher.matches();
	}

	// 비밀번호 패턴 검증 메소드
	private static boolean validatePassword(String password) {
		// 영소문자, 대문자, 숫자, 특수문자가 최소 1개 이상 포함되어야 함
		Pattern pattern = Pattern.compile(password);
		Matcher matcher = pattern.matcher("^[a-zA-Z][a-zA-Z0-9]{7,14}$");
		System.out.println(matcher.matches());
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
				System.out.println("중복된 아이디가 있습니다.");
				return true;
			} else {
				System.out.println("사용 가능한 아이디입니다.");
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
