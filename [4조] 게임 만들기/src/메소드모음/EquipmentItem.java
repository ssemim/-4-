package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import GUI.invenWin;
import dbutil.DBUtil;
import 객체모음.Student;

public class EquipmentItem {
	public int[] itemNos(Student s) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int[] itemNos = new int[2];

		try {
			conn = DBUtil.getConnection();
			String sql = "select a.studentId, a.type, a.itemNo \r\n"
					+ "from (select a.studentId, a.itemNo, b.no, b.type \r\n" + "from equipment a \r\n"
					+ "left join item b on a.itemNo = b.no) a \r\n" + "left join student b on a.studentId = b.id \r\n"
					+ "where b.id = ? order by type;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				String type = rs.getString("type");
				int itemNo = rs.getInt("itemNo");
				if (type.equals("캐릭터")) {
					itemNos[0] = itemNo;
				} else {
					itemNos[1] = itemNo;
				}
			}
		} catch (SQLException e) {
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return itemNos;
	}

	/**
	 * 아이템 명 반환 메소드 0번인덱스 캐릭터 1번, 배경. 이미지 이름에 넣어서 쓰면됨
	 * 
	 * @param itemNos 메소드 사용
	 * @return 문자열배열
	 */
	public String[] selectItemIamgeName(int[] arr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String[] itemNames = new String[2];

		try {
			conn = DBUtil.getConnection();
			String sql = "select * from item where no = ? or no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.valueOf(arr[0]));
			stmt.setInt(2, Integer.valueOf(arr[1]));
			rs = stmt.executeQuery();
			while (rs.next()) {
				String itemName = rs.getString("name");
				String type = rs.getString("type");
				if (type.equals("캐릭터")) {
					itemNames[0] = itemName;
				} else {
					itemNames[1] = itemName;
				}
			}
		} catch (SQLException e) {
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return itemNames;
	}

	/**
	 * 장착한 캐릭터 이미지를 가지는 판넬
	 * 
	 * @param selectItemIamageName 메소드
	 * @return panel
	 */

	public static void equipmentItem(String[] arr, JPanel pnl) {
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		pnl.setLayout(null);

		JLabel clbl = new JLabel();
		ImageIcon cicon = new ImageIcon(invenWin.class.getResource("/이미지/" + arr[0] + ".gif"));
		clbl.setIcon(cicon);
		clbl.setBounds(0, 0, 150, 200);
		pnl.add(clbl, JLayeredPane.PALETTE_LAYER);

		JLabel wlbl = new JLabel();
		ImageIcon wicon = new ImageIcon(invenWin.class.getResource("/이미지/" + arr[1] + ".png"));
		wlbl.setIcon(wicon);
		wlbl.setBounds(0, 0, 150, 200);
		pnl.add(wlbl, JLayeredPane.DEFAULT_LAYER);

	}

//   public void changeEquipmentItem(Student s, JPanel Charpanel) {
//	   Connection conn = null;
//	      PreparedStatement stmt = null;
//	      ResultSet rs = null;
//	      String[] itemNames = new String[2];
//	      
//	      try {
//	         conn = DBUtil.getConnection();
//	         String sql = "select * from item where no = ? or no = ?";
//	         stmt = conn.prepareStatement(sql);
//	         stmt.setInt(1, Integer.valueOf(arr[0]));
//	         stmt.setInt(2, Integer.valueOf(arr[1]));
//	         rs = stmt.executeQuery();
//	         while (rs.next()) {
//	            String itemName = rs.getString("name");
//	            String type = rs.getString("type");
//	            if (type.equals("캐릭터")) {
//	               itemNames[0] = itemName;
//	            }
//	            else {
//	               itemNames[1] = itemName;
//	            }
//	         }
//	      } catch (SQLException e) {
//	      } finally {
//	         DBUtil.close(rs);
//	         DBUtil.close(stmt);
//	         DBUtil.close(conn);
//	      }
//	   
//	   EquipmentItem e = new EquipmentItem();
//	   String[] equipmentName = e.selectItemIamgeName(e.itemNos(s));
//	   EquipmentItem.equipmentItem(equipmentName, Charpanel);
//   }

	public static void insertBasicInven(Student s, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO `team4`.`inventory` (`studentId`, `itemNo`) " 
						+ "VALUES (?, '1'), (?, '2');";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getId());
			stmt.setString(2, s.getId());
			stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
		}
	}
	
	public static void insertBasicEqui(Student s, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO `team4`.`equipment` (`studentId`, `itemNo`) "
						+ "VALUES (?, '1'), (?, '2');";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getId());
			stmt.setString(2, s.getId());
			stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
		}
	}
}