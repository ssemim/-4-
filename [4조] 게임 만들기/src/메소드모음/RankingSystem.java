package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (game.equals("RUN")) {
			gameNo = 1;
		} else if (game.equals("HANGMAN")) {
			gameNo = 2;
		} else if (game.equals("NUMBER")) {
			gameNo = 3;
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

	public String gameSelect(String gamename, int plma) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Map<Integer, String> game = new HashMap<Integer, String>();
		game.put(1, "RUN");
		game.put(2, "HANGMAN");
		game.put(3, "NUMBER");

		int index = 0;

		if (gamename.equals("RUN")) {
			index = 1;
		} else if (gamename.equals("HANGMAN")) {
			index = 2;
		} else if (gamename.equals("NUMBER")) {
			index = 3;
		}

		String sql = "select * from (SELECT * FROM gamelog A where gameNo = ? order by no) B\r\n"
				+ "order by point desc limit 3;";
		List<Integer> gamelist = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, index);
			rs = stmt.executeQuery();

			while (rs.next()) {
				gamelist.add(rs.getInt("gameNo"));
			}

			if ((index + plma) > 0 && (index + plma) <= gamelist.size()) {
				return game.get(index + plma);
			} else if ((index + plma) > 0) {
				return game.get(index);
			} else if ((index + plma) <= gamelist.size()) {
				return game.get(index);
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

//	public static void main(String[] args) {
//		RankingSystem game = new RankingSystem();
//		System.out.println(game.gameSelect("HANGMAN", 1));
//	}
}
