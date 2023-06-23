import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

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

				list.add(new School(userId, pointAll))
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

	@Override
	public List<Student> classRangking() {

		return null;
	}

	@Override
	public List<Student> studentRangking() {

		return null;
	}

}
