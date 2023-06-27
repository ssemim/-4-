package 메소드모음;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import GUI.invenWin;
import dbutil.DBUtil;
import 객체모음.Student;

public class Inventory {
	Student s;
	String type;

	public Inventory(Student s) {
		this.s = s;
	}

	// 타입과 학생 id를 받아 학생이 이 아이템을 가지고 있는지를 true false로 알려줌
	public List<Boolean> ItemAcquisition(String type) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Boolean> l = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "select * from (SELECT * FROM item where type = ?) a left join  inventory b on a.no = b.itemNo and b.studentId = ? group by a.name";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, type);
			stmt.setString(2, s.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("studentId") == null) {
					l.add(false);
				} else {
					l.add(true);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return l;
	}
	
	public void imageSet(List cList, List cList2, List wList, List wList2, 
			ImageIcon[] cIcons, ImageIcon[] cIconsBlock, ImageIcon[] wIcons, ImageIcon[] wIconsBlock) {
		for (int i = 0; i < cList2.size(); i++) {
			cIcons[i] = new ImageIcon(invenWin.class.getResource("/이미지/캐릭터" + (i + 1) + ".gif"));
		}
		for (int i = 0; i < cList2.size(); i++) {
			cIconsBlock[i] = new ImageIcon(invenWin.class.getResource("/이미지/캐릭터" + (i + 1) + "잠금.png"));
		}

		for (int i = 0; i < wList2.size(); i++) {
			wIcons[i] = new ImageIcon(invenWin.class.getResource("/이미지/배경" + (i + 1) + ".png"));
		}
		for (int i = 0; i < wList2.size(); i++) {
			wIconsBlock[i] = new ImageIcon(invenWin.class.getResource("/이미지/배경" + (i + 1) + "잠금.png"));
		}
	}

	

	public int cBtnRight(int cCount, JLabel cLbl, JButton cBtnRight, JButton cBtnLeft,
			List cList, List cList2, ImageIcon[] cIcons, ImageIcon[] cIconsBlock) {
		
		if (cCount < cList2.size())
			cCount += 1;
		if ((boolean) cList.get(cCount)) {
			cLbl.setIcon(cIcons[cCount]);
		} else {
			cLbl.setIcon(cIconsBlock[cCount]);
		}
		if (cCount == cList2.size() - 1)
			cBtnRight.setEnabled(false);
		cBtnLeft.setEnabled(true);
		return cCount;
		
	}

	public int cBtnLeft(int cCount, JLabel cLbl, JButton cBtnRight, JButton cBtnLeft,
			List cList, List cList2, ImageIcon[] cIcons, ImageIcon[] cIconsBlock) {
		if (cCount > 0)
			cCount -= 1;
		if ((boolean) cList.get(cCount)) {
			cLbl.setIcon(cIcons[cCount]);
		} else {
			cLbl.setIcon(cIconsBlock[cCount]);
		}
		if (cCount == 0)
			cBtnLeft.setEnabled(false);
		cBtnRight.setEnabled(true);
		return cCount;
	}
	
	public int wBtnLeft(int wCount, JLabel wLbl, JButton wBtnRight, JButton wBtnLeft,
			List wList, List wList2, ImageIcon[] wIcons, ImageIcon[] wIconsBlock) {
		if (wCount > 0)
			wCount -= 1;
		if ((boolean) wList.get(wCount)) {
			wLbl.setIcon(wIcons[wCount]);
		} else {
			wLbl.setIcon(wIconsBlock[wCount]);
		}
		if (wCount == 0)
			wBtnLeft.setEnabled(false);
		wBtnRight.setEnabled(true);
		System.out.println(wCount);
		return wCount;
	}

	public int wBtnRight(int wCount, JLabel wLbl, JButton wBtnRight, JButton wBtnLeft,
			List wList, List wList2, ImageIcon[] wIcons, ImageIcon[] wIconsBlock) {
		if (wCount < wList2.size())
			wCount += 1;
		if ((boolean) wList.get(wCount)) {
			wLbl.setIcon(wIcons[wCount]);
		} else {
			wLbl.setIcon(wIconsBlock[wCount]);
		}
		if (wCount == wList2.size() - 1)
			wBtnRight.setEnabled(false);
		wBtnLeft.setEnabled(true);
		System.out.println(wCount);
		return wCount;
	}
}
