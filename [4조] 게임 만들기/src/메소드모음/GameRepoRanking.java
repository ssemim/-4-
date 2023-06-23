package 메소드모음;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;
import 객체모음.School;
import 객체모음.Student;

public class GameRepoRanking implements GameInterface {
	@Override
	public int insert(Student student) {
		return 0;
	}

	@Override
	public int login(Student student) {
		return 0;
	}

	@Override
	public int 뽑기() {
		return 0;
	}

	/**
	 * 학교 랭킹 반환 메소드
	 * 
	 * @return 학교 리스트
	 */
	@Override
	public List<School> schoolRangking() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<School> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "select school, sum(point) " + "from (select a.id, a.school, max(b.point) as point "
					+ "from student a " + "left join gamelog b on a.id = b.studentid \r\n" + "group by a.id) as a\r\n"
					+ "group by school limit 3;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String schoolName = rs.getString("school");
				int pointAll = rs.getInt("sum(point)");

				list.add(new School(schoolName, pointAll));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return list;
	}

	/**
	 * 교내 랭킹 반환 메소드
	 * 
	 * @param 접속중인 학생 객체
	 * @return 학생리스트
	 */
	@Override
	public List<Student> classRangking(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "select a.id, a.school, max(b.point) as point from student a left join gamelog b on a.id = b.studentid \r\n"
					+ "group by a.id having a.school = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, student.getSchool());
			rs = stmt.executeQuery();

			while (rs.next()) {
				String parseId = rs.getString("id");
				String school = rs.getString("school");
				int point = rs.getInt("point");

				list.add(new Student(parseId, school, point));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return list;
	}

	/**
	 * 게임별 랭킹 반환 메소드
	 * 
	 * @param 게임 no
	 * @return 학생리스트
	 */
	@Override
	public List<Student> studentRangking(int gameNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "select a.id, b.gameNo, b.point from student a \r\n"
					+ "left join gamelog b on a.id = b.studentid\r\n" + "where gameNo = ? \r\n"
					+ "order by b.point desc limit 3;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, gameNo);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String parseId = rs.getString("id");
				int point = rs.getInt("point");

				list.add(new Student(parseId, point));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return list;
	}
}
