package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import 객체모음.School;
import 객체모음.Student;
import 메소드모음.RankingSystem;

public class RankWin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RankWin frame = new RankWin();
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
	public RankWin(Student s) {
		RankingSystem RS = new RankingSystem();
		List<Student> classList = null;
		List<School> school = null;
		List<Student> studentAll = null;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setUndecorated(true); // 창 프레임 없애기
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 프레임 설정
		setTitle("랭킹확인"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		JButton Backbtn = new JButton(); // 뒤로가기 버튼
		Backbtn.setBackground(Color.BLACK);
		Backbtn.setBorderPainted(false); // 버튼 테두리 제거
		Backbtn.setIcon(new ImageIcon(RankWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.setBounds(748, 550, 40, 40);
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
			}
		});

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
		cutbtn.setBounds(755, 13, 30, 30);
		contentPane.add(cutbtn);

		JLabel allRankLbl = new JLabel("RUN");
		allRankLbl.setHorizontalAlignment(SwingConstants.CENTER);
		allRankLbl.setBounds(60, 30, 111, 35);
		allRankLbl.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		allRankLbl.setForeground(Color.WHITE);

		JPanel Schoolpanel = new JPanel();
		Schoolpanel.setBounds(14, 258, 250, 250);
		school = RS.schoolRangking();
		JPanel Studentpanel = new JPanel();
		Studentpanel.setBounds(276, 258, 250, 250);
		classList = RS.classRangking(s);
		JPanel AllStudentpanel = new JPanel();
		AllStudentpanel.setBounds(538, 258, 250, 250);
		studentAll = RS.studentRangking(allRankLbl.getText());

		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setBounds(30, 120, 180, 30);
		lblNewLabel_4_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_4_2.setForeground(Color.WHITE);
		JLabel lblNewLabel_5_2 = new JLabel("");
		lblNewLabel_5_2.setBounds(30, 160, 180, 30);
		lblNewLabel_5_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_5_2.setForeground(Color.WHITE);
		JLabel lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.setBounds(30, 80, 180, 30);
		lblNewLabel_3_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3_2.setForeground(Color.WHITE);

		if (studentAll.size() >= 3) {
			lblNewLabel_3_2.setText("1등 : " + studentAll.get(0).getId() + " " + studentAll.get(0).getPoint());
			if (studentAll.get(0).getPoint() == studentAll.get(1).getPoint()) {
				lblNewLabel_4_2.setText("1등 : " + studentAll.get(1).getId() + " " + studentAll.get(1).getPoint());
			} else {
				lblNewLabel_4_2.setText("2등 : " + studentAll.get(1).getId() + " " + studentAll.get(1).getPoint());
				if (studentAll.get(1).getPoint() == studentAll.get(2).getPoint()) {
					lblNewLabel_4_2.setText("2등 : " + studentAll.get(2).getId() + " " + studentAll.get(2).getPoint());
					lblNewLabel_5_2.setText("2등 : " + studentAll.get(2).getId() + " " + studentAll.get(2).getPoint());
				} else {
					lblNewLabel_5_2.setText("3등 : " + studentAll.get(2).getId() + " " + studentAll.get(2).getPoint());
				}
			}
		} else if (studentAll.size() == 2) {
			lblNewLabel_3_2.setText("1등 : " + studentAll.get(0).getId() + " " + studentAll.get(0).getPoint());
			lblNewLabel_4_2.setText("2등 : " + studentAll.get(1).getId() + " " + studentAll.get(1).getPoint());
		} else {
			lblNewLabel_3_2.setText("1등 : " + studentAll.get(0).getId() + " " + studentAll.get(0).getPoint());
		}

		JLabel lblNewLabel_1 = new JLabel("길드내 랭킹");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(49, 30, 147, 40);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel_1.setForeground(Color.WHITE);
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setBounds(30, 120, 180, 30);
		lblNewLabel_4_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_4_1.setForeground(Color.WHITE);
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setBounds(30, 160, 180, 30);
		lblNewLabel_5_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_5_1.setForeground(Color.WHITE);
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setBounds(30, 80, 180, 30);
		lblNewLabel_3_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3_1.setForeground(Color.WHITE);

		if (classList.size() >= 3) {
			lblNewLabel_3_1.setText("1등 : " + classList.get(0).getId() + " " + classList.get(0).getPoint());
			if (classList.get(0).getPoint() == classList.get(1).getPoint()) {
				lblNewLabel_4_1.setText("1등 : " + classList.get(1).getId() + " " + classList.get(1).getPoint());
			} else {
				lblNewLabel_4_1.setText("2등 : " + classList.get(1).getId() + " " + classList.get(1).getPoint());
				if (classList.get(1).getPoint() == classList.get(2).getPoint()) {
					lblNewLabel_4_1.setText("2등 : " + classList.get(2).getId() + " " + classList.get(2).getPoint());
					lblNewLabel_5_1.setText("2등 : " + classList.get(2).getId() + " " + classList.get(2).getPoint());
				} else {
					lblNewLabel_5_1.setText("3등 : " + classList.get(2).getId() + " " + classList.get(2).getPoint());
				}
			}
		} else if (classList.size() == 2) {
			lblNewLabel_3_1.setText("1등 : " + classList.get(0).getId() + " " + classList.get(0).getPoint());
			lblNewLabel_4_1.setText("2등 : " + classList.get(1).getId() + " " + classList.get(1).getPoint());
		} else {
			lblNewLabel_3_1.setText("1등 : " + classList.get(0).getId() + " " + classList.get(0).getPoint());
		}

		JLabel lblNewLabel = new JLabel("길드별 랭킹");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(55, 30, 140, 35);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.WHITE);
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(30, 80, 180, 30);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3.setForeground(Color.WHITE);
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(30, 120, 180, 30);
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_4.setForeground(Color.WHITE);
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(30, 160, 180, 30);
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_5.setForeground(Color.WHITE);

		if (school.size() >= 3) {
			lblNewLabel_3.setText("1등 : " + school.get(0).getName() + " " + school.get(0).getName());
			if (school.get(0).getName() == school.get(1).getName()) {
				lblNewLabel_4.setText("1등 : " + school.get(1).getName() + " " + school.get(1).getName());
				if (school.get(1).getName() == school.get(2).getName()) {
					lblNewLabel_5.setText("1등 : " + school.get(2).getName() + " " + school.get(1).getName());
				}
			} else {
				lblNewLabel_4.setText("2등 : " + school.get(1).getName() + " " + school.get(1).getName());
				if (school.get(1).getName() == school.get(2).getName()) {
					lblNewLabel_5.setText("2등 : " + school.get(2).getName() + " " + school.get(2).getPointAll());
				} else {
					lblNewLabel_5.setText("3등 : " + school.get(2).getName() + " " + school.get(2).getPointAll());
				}
			}
		} else if (school.size() == 2) {
			lblNewLabel_3.setText("1등 : " + school.get(0).getName() + " " + school.get(0).getPointAll());
			lblNewLabel_4.setText("2등 : " + school.get(1).getName() + " " + school.get(1).getPointAll());
		} else {
			lblNewLabel_3.setText("1등 : " + school.get(0).getName() + " " + school.get(0).getPointAll());
		}

		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(Schoolpanel);
		Schoolpanel.setLayout(null);
		Schoolpanel.add(lblNewLabel_5);
		Schoolpanel.add(lblNewLabel_4);
		Schoolpanel.add(lblNewLabel_3);
		Schoolpanel.add(lblNewLabel);

		JLabel SchoImgPnl = new JLabel("");
		SchoImgPnl.setBounds(0, 0, 250, 250);
		SchoImgPnl.setForeground(Color.WHITE);
		SchoImgPnl.setIcon(new ImageIcon(RankWin.class.getResource("/이미지/랭킹배경.png")));
		Schoolpanel.add(SchoImgPnl);
		contentPane.add(Studentpanel);
		Studentpanel.setLayout(null);
		Studentpanel.add(lblNewLabel_3_1);
		Studentpanel.add(lblNewLabel_4_1);
		Studentpanel.add(lblNewLabel_5_1);
		Studentpanel.add(lblNewLabel_1);

		JLabel SchInmgPnl = new JLabel("");
		SchInmgPnl.setBounds(0, 0, 250, 250);
		SchInmgPnl.setIcon(new ImageIcon(RankWin.class.getResource("/이미지/랭킹배경.png")));
		SchInmgPnl.setForeground(Color.WHITE);
		Studentpanel.add(SchInmgPnl);
		contentPane.add(AllStudentpanel);
		AllStudentpanel.setLayout(null);
		AllStudentpanel.add(lblNewLabel_3_2);
		AllStudentpanel.add(lblNewLabel_4_2);
		AllStudentpanel.add(lblNewLabel_5_2);
		AllStudentpanel.add(allRankLbl);

		JButton btnNewButton_1 = new JButton(new ImageIcon(invenWin.class.getResource("/이미지/오른쪽버튼.png")));
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setBorderPainted(false); // 버튼 테두리 제거
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(198, 26, 40, 35);
		AllStudentpanel.add(btnNewButton_1);

		JButton btnNewButton = new JButton(new ImageIcon(invenWin.class.getResource("/이미지/왼쪽버튼.png")));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBorderPainted(false); // 버튼 테두리 제거
		btnNewButton.setBounds(12, 26, 40, 35);
		AllStudentpanel.add(btnNewButton);
		AllStudentpanel.add(lblNewLabel_3_2);
		AllStudentpanel.add(lblNewLabel_4_2);
		AllStudentpanel.add(lblNewLabel_5_2);
		AllStudentpanel.add(allRankLbl);

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newGame = RS.gameSelect(allRankLbl.getText(), 1);
				if (newGame == null) {
					System.out.println("마우스 비활성화");
				} else {
					if (newGame == "NUMBER") {
						btnNewButton_1.setBorderPainted(false);
						btnNewButton_1.setContentAreaFilled(false);
						btnNewButton_1.setFocusPainted(false);
					}

					allRankLbl.setText(newGame);

					List<Student> newList = RS.studentRangking(newGame);
					if (newList.size() >= 3) {
						lblNewLabel_3_2.setText("1등 : " + newList.get(0).getId() + " " + newList.get(0).getPoint());
						if (newList.get(0).getPoint() == newList.get(1).getPoint()) {
							lblNewLabel_4_2.setText("1등 : " + newList.get(1).getId() + " " + newList.get(1).getPoint());
							if (newList.get(1).getPoint() == newList.get(2).getPoint()) {
								lblNewLabel_5_2
										.setText("1등 : " + newList.get(2).getId() + " " + newList.get(2).getPoint());
							}
						} else {
							lblNewLabel_4_2.setText("2등 : " + newList.get(1).getId() + " " + newList.get(1).getPoint());
							if (newList.get(1).getPoint() == newList.get(2).getPoint()) {
								lblNewLabel_5_2
										.setText("2등 : " + newList.get(2).getId() + " " + newList.get(2).getPoint());
							} else {
								lblNewLabel_5_2
										.setText("3등 : " + newList.get(2).getId() + " " + newList.get(2).getPoint());
							}
						}
					} else if (newList.size() == 2) {
						lblNewLabel_3_2.setText("1등 : " + newList.get(0).getId() + " " + newList.get(0).getPoint());
						lblNewLabel_4_2.setText("2등 : " + newList.get(1).getId() + " " + newList.get(1).getPoint());
						lblNewLabel_5_2.setText("");
					} else {
						lblNewLabel_3_2.setText("1등 : " + newList.get(0).getId() + " " + newList.get(0).getPoint());
						lblNewLabel_4_2.setText("");
						lblNewLabel_5_2.setText("");
					}
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newGame = RS.gameSelect(allRankLbl.getText(), -1);
				if (newGame == null) {
					System.out.println("마우스 비활성화");
				} else {
					if (newGame == "RUN") {
						btnNewButton.setBorderPainted(false);
						btnNewButton.setContentAreaFilled(false);
						btnNewButton.setFocusPainted(false);
					}
					allRankLbl.setText(newGame);

					List<Student> newList = RS.studentRangking(newGame);

					if (newList.size() >= 3) {
						lblNewLabel_3_2.setText("1등 : " + newList.get(0).getId() + " " + newList.get(0).getPoint());
						if (newList.get(0).getPoint() == newList.get(1).getPoint()) {
							lblNewLabel_4_2.setText("1등 : " + newList.get(1).getId() + " " + newList.get(1).getPoint());
							if (newList.get(1).getPoint() == newList.get(2).getPoint()) {
								lblNewLabel_5_2
										.setText("1등 : " + newList.get(2).getId() + " " + newList.get(2).getPoint());
							}
						} else {
							lblNewLabel_4_2.setText("2등 : " + newList.get(1).getId() + " " + newList.get(1).getPoint());
							if (newList.get(1).getPoint() == newList.get(2).getPoint()) {
								lblNewLabel_5_2
										.setText("2등 : " + newList.get(2).getId() + " " + newList.get(2).getPoint());
							} else {
								lblNewLabel_5_2
										.setText("3등 : " + newList.get(2).getId() + " " + newList.get(2).getPoint());
							}
						}
					} else if (newList.size() == 2) {
						lblNewLabel_3_2.setText("1등 : " + newList.get(0).getId() + " " + newList.get(0).getPoint());
						lblNewLabel_4_2.setText("2등 : " + newList.get(1).getId() + " " + newList.get(1).getPoint());
						lblNewLabel_5_2.setText("");
					} else {
						lblNewLabel_3_2.setText("1등 : " + newList.get(0).getId() + " " + newList.get(0).getPoint());
						lblNewLabel_4_2.setText("");
						lblNewLabel_5_2.setText("");
					}
				}
			}
		});

		JLabel AllStudentImgPnl = new JLabel("");
		AllStudentImgPnl.setBounds(0, 0, 250, 250);
		AllStudentImgPnl.setIcon(new ImageIcon(RankWin.class.getResource("/이미지/랭킹배경.png")));
		AllStudentImgPnl.setForeground(Color.WHITE);
		AllStudentpanel.add(AllStudentImgPnl);
		contentPane.add(Backbtn);

		JLabel RankMainLabel = new JLabel("");
		RankMainLabel.setIcon(new ImageIcon(RankWin.class.getResource("/이미지/게임결과타이틀.png")));
		RankMainLabel.setBounds(148, 72, 500, 150);
		contentPane.add(RankMainLabel);
	}
}