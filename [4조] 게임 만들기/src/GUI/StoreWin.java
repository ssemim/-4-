package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbutil.DBUtil;
import 객체모음.Student;
import 메소드모음.EquipmentItem;
import 메소드모음.PickItem;

public class StoreWin extends JFrame {

	private JPanel contentPane;
	private int countBack = 0;
	private int character = 0;
	private int item;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StoreWin frame = new StoreWin(new Student("dd", "당리초", 2200000));
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public StoreWin(Student s, String[] equipmentName) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 프레임 설정
		setTitle("상점"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);
		JButton Backbtn = new JButton();
		Backbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.setBounds(711, 167, 40, 40);
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
				character = 0;
			}
		});

		JLabel Coinlbl = new JLabel("1.000");
		Coinlbl.setBounds(640, 184, 60, 15);

		JPanel Charpnl = new JPanel();
		EquipmentItem.equipmentItem(equipmentName, Charpnl);
		
		Charpnl.setBounds(601, 217, 150, 200);

		JButton Charbtn = new JButton("캐릭터 뽑기");
		Charbtn.setBounds(38, 167, 246, 250);
//		Charbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/뽑기 기본이미지.gif")));
		Charbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (s.getPoint() >= 1000) {
					if (character == 0) {
						int point = s.getPoint();
						PickItem pick = new PickItem(s, "캐릭터");
						item = ItemLook("캐릭터", pick.pickItem());
						s.setPoint(point - 1000);
						pointDown(s);
						Charbtn.setText("");
						Charbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/상자오픈.gif")));
						character++;
					} else if (character == 1) {
						Charbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/캐릭터" + item + ".gif")));
						character++;
					} else if (character == 2) {
//						 Charbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/뽑기 기본이미지.gif")));
						character = 0;
					}
				}
			}
		});

		JButton BackWinbtn = new JButton("배경 뽑기");
		BackWinbtn.setBounds(316, 167, 246, 250);
//		BackWinbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/뽑기 기본이미지.gif")));
		BackWinbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (s.getPoint() >= 1000) {
					if (countBack == 0) {
						int a = s.getPoint();
						PickItem pick = new PickItem(s, "배경");
						item = ItemLook("배경", pick.pickItem());
						s.setPoint(a - 1000);
						pointDown(s);
						BackWinbtn.setText("");
						BackWinbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/상자오픈.gif")));
						countBack++;
					} else if (countBack == 1) {
						BackWinbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/배경" + item + ".png")));
						countBack++;
					} else if (countBack == 2) {
//						BackWinbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/뽑기 기본이미지.gif")));
						countBack = 0;
					}
				}
			}
		});
		
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(Charbtn);
		contentPane.add(BackWinbtn);
		contentPane.add(Coinlbl);
		contentPane.add(Backbtn);
		contentPane.add(Charpnl);
		
		JButton cutbtn = new JButton(); // 종료버튼
		cutbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/종료버튼.png")));
		cutbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cutbtn.setBorderPainted(false); // 외곽선없애기
				cutbtn.setContentAreaFilled(false); // 영역채우기x
				cutbtn.setFocusPainted(false); // 버튼선택시 테두리X
				System.exit(0);
			}
		});
		cutbtn.setBounds(752, 10, 30, 30);
		contentPane.add(cutbtn);
	}

	// 아이템 이미지를 가져오기 위한 것
	public int ItemLook(String type, int item) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Integer> l = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM item where type = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, type);
			rs = stmt.executeQuery();
			while (rs.next()) {
				l.add(rs.getInt("no"));
			}
			for (int i = 0; i < l.size(); i++) {
				if (l.get(i).equals(item)) {
					return i + 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return -1;
	}

	// 학생 점수를 깍는 것
	public int pointDown(Student s) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "update student set point = point - 1000 where id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getId());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return -1;
	}
}
