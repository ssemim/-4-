package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GUI.RankWin;
import dbutil.DBUtil;
import 객체모음.School;
import 객체모음.Student;

public class RankingSystem {
	/**
	 * 학교 랭킹 반환 메소드
	 * 
	 * @return 학교 리스트
	 */
	public List<School> schoolRangking() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<School> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "select `last`.id, `last`.school, sum(`last`.point) as point from "
					+ "(SELECT A.id, A.school, SUM(C.max_score_sum) AS point\r\n" + "	FROM student A\r\n"
					+ "	inner JOIN (\r\n" + "		SELECT studentId, SUM(max_score) AS max_score_sum\r\n"
					+ "		FROM (\r\n" + "			SELECT studentId, gameNo, MAX(point) AS max_score\r\n"
					+ "			FROM gamelog\r\n" + "			WHERE gameNo IN (1, 2, 3)\r\n"
					+ "			GROUP BY studentId, gameNo\r\n" + "		) AS subquery\r\n"
					+ "		GROUP BY studentId\r\n" + "	) AS C ON A.id = C.studentId\r\n"
					+ "	GROUP BY A.id, A.school\r\n"
					+ "	ORDER BY `point` DESC) as `last` group by school order by point desc;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String schoolName = rs.getString("school");
				int pointAll = rs.getInt("point");

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
	public List<Student> classRangking(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT A.id, A.school, SUM(C.max_score_sum) AS point\r\n" + "	FROM student A\r\n"
					+ "	LEFT JOIN (\r\n" + "		SELECT studentId, SUM(max_score) AS max_score_sum\r\n"
					+ "		FROM (\r\n" + "			SELECT studentId, gameNo, MAX(point) AS max_score\r\n"
					+ "			FROM gamelog\r\n" + "			WHERE gameNo IN (1, 2, 3)\r\n"
					+ "			GROUP BY studentId, gameNo\r\n" + "		) AS subquery\r\n"
					+ "		GROUP BY studentId\r\n" + "	) AS C ON A.id = C.studentId\r\n" + "	WHERE A.school = ?\r\n"
					+ "	GROUP BY A.id, A.school\r\n" + "	ORDER BY `point` DESC\r\n" + "	LIMIT 3;";
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
	public List<Student> studentRangking(String game) {

		int gameNo = 0;
		if (game.equals("똥 피하기")) {
			gameNo = 1;
		} else if (game.equals("행맨")) {
			gameNo = 2;
		} else if (game.equals("두더지 잡기")) {
			gameNo = 3;
		} else if (game.equals("가위바위보")) {
			gameNo = 4;
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "select * from (SELECT * FROM gamelog A where gameNo = ? order by no) B\r\n"
					+ "order by point desc limit 3;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, gameNo);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String parseId = rs.getString("studentId");
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

	public List<Student> gameSelect(int gameName) {
		RankWin.gameList.put("똥 피하기", 1);
		RankWin.gameList.put("행맨", 2);
		RankWin.gameList.put("두더지 잡기", 3);
		RankWin.gameList.put("가위바위보", 4);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Student> list = new ArrayList<Student>();

		String sql = "select * from (SELECT * FROM gamelog A where gameNo = ? order by no) B\r\n"
				+ "order by point desc limit 3;";

		if (gameName > 0 && RankWin.gameList.size() >= gameName) {
			try {
				conn = DBUtil.getConnection();
				stmt = conn.prepareStatement(sql);

				stmt.setInt(1, gameName);
				rs = stmt.executeQuery();

				while (rs.next()) {
					String id = rs.getString("studentId");
					int point = rs.getInt("point");

					list.add(new Student(id, point));
				}

				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.close(rs);
				DBUtil.close(stmt);
				DBUtil.close(conn);
			}
		}

		return null;
	}

//	public static void main(String[] args) {
//		RankingSystem game = new RankingSystem();
//		System.out.println(game.gameSelect(5));
//	}
}
