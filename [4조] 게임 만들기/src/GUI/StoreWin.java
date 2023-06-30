package GUI;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import dbutil.DBUtil;
import 객체모음.Student;
import 메소드모음.EquipmentItem;
import 메소드모음.PickItem;
import 유틸.Music;
import 유틸.SoundButton;
import 유틸.Util;

public class StoreWin extends JFrame {

	private JPanel contentPane;
	private int countBack = 0;
	private int character = 0;
	private int item;
	private final JLabel StoreBackLbl = new JLabel("");
	private Timer timerC;
	private Timer timerW;

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
		setBackground(Color.BLACK);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 프레임 설정
		setTitle("상점"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);
		setUndecorated(true); // 창 프레임 없애기

		JButton Backbtn = new JButton(); // 뒤로가기 버튼
		Backbtn.setBackground(Color.BLACK);
		Backbtn.setBorderPainted(false); // 버튼 테두리 제거
		Backbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.setBounds(742, 550, 40, 40);
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
				countBack = 0;
				character = 0;
			}
		});

		JLabel Coinlbl = new JLabel("1.000");
		Coinlbl.setFont(new Font("굴림", Font.BOLD, 18));
		Coinlbl.setForeground(Color.WHITE);
		Coinlbl.setBounds(631, 187, 120, 20);
		Coinlbl.setText("" + s.getPoint());

		JPanel Charpnl = new JPanel();
		EquipmentItem.equipmentItem(equipmentName, Charpnl);

		Charpnl.setBounds(601, 217, 150, 200);

		// 캐릭터 뽑기 버튼
		SoundButton Charbtn = new SoundButton(Music.S1);
		Charbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/캐릭터뽑기더미.png")));
		Charbtn.setBounds(38, 167, 250, 250);
		Charbtn.setBorderPainted(false); // 버튼 테두리 제거
		Charbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (s.getPoint() >= 1000 || character > 0) {
					if (character == 0) {
						int point = s.getPoint();
						PickItem pick = new PickItem(s, "캐릭터");
						item = ItemLook("캐릭터", pick.pickItem());
						s.setPoint(point - 1000);
						Coinlbl.setText("" + s.getPoint());
						pointDown(s);
						Charbtn.setText("");
						Charbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/상자오픈.gif")));
						character++;
						timerC.start();
					} else if (character == 1) {
						timerC.stop();
						Charbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/캐릭터" + item + ".gif")));
						character++;
						timerC.start();
					} else if (character == 2) {
						timerC.stop();
						Charbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/캐릭터뽑기더미.png")));
						character = 0;
					}
				}
			}
		});
		
		timerC = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Charbtn.doClick();
			}
		});

		SoundButton BackWinbtn = new SoundButton(Music.S1); // 배경뽑기
		BackWinbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/배경뽑기더미.png")));
		BackWinbtn.setBounds(316, 167, 250, 250);
		BackWinbtn.setBorderPainted(false); // 버튼 테두리 제거
		BackWinbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (s.getPoint() >= 1000 || countBack > 0) {
					if (countBack == 0) {
						int a = s.getPoint();
						PickItem pick = new PickItem(s, "배경");
						item = ItemLook("배경", pick.pickItem());
						s.setPoint(a - 1000);
						Coinlbl.setText("" + s.getPoint());
						pointDown(s);
						BackWinbtn.setText("");
						BackWinbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/상자오픈2.gif")));
						countBack++;
						timerW.start();
					} else if (countBack == 1) {
						timerW.stop();
						BackWinbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/배경" + item + ".gif")));
						countBack++;
						timerW.start();
					} else if (countBack == 2) {
						timerW.stop();
						BackWinbtn.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/배경뽑기더미.png")));
						countBack = 0;
					}
				}
			}
		});
		
		timerW = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BackWinbtn.doClick();
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
		cutbtn.setBackground(Color.BLACK);
		cutbtn.setBorderPainted(false); // 버튼 테두리 제거
		cutbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/종료버튼.png")));
		cutbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cutbtn.setBounds(752, 10, 30, 30);
		contentPane.add(cutbtn);

		JLabel CoinImgLbl = new JLabel("");
		CoinImgLbl.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/코인이미지.png")));
		CoinImgLbl.setBounds(601, 187, 20, 20);
		contentPane.add(CoinImgLbl);
		Util.removeAllButtonFocus(contentPane);
		StoreBackLbl.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/상점배경움짤.gif")));
		StoreBackLbl.setBounds(0, 0, 800, 600);
		contentPane.add(StoreBackLbl);
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