package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import 객체모음.School;
import 객체모음.Student;
import 메소드모음.RankingSystem;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

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
		Backbtn.setIcon(new ImageIcon(RankWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.setBounds(745, 10, 40, 40);
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Backbtn.setBorderPainted(false); // 외곽선없애기
				Backbtn.setContentAreaFilled(false); // 영역채우기x
				Backbtn.setFocusPainted(false); // 버튼선택시 테두리X
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
			}
		});

		JLabel allRankLbl = new JLabel("1");
		allRankLbl.setBounds(95, 39, 76, 15);
		allRankLbl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		allRankLbl.setForeground(Color.WHITE);

		JPanel Schoolpanel = new JPanel();
		Schoolpanel.setBounds(14, 258, 250, 250);
		school = RS.schoolRangking();
		JPanel Studentpanel = new JPanel();
		Studentpanel.setBounds(276, 258, 250, 250);
		classList = RS.classRangking(s);
		JPanel AllStudentpanel = new JPanel();
		AllStudentpanel.setBounds(538, 258, 250, 250);
		studentAll = RS.studentRangking(Integer.valueOf(allRankLbl.getText()));

		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setBounds(152, 120, 0, 0);
		lblNewLabel_4_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_4_2.setForeground(Color.WHITE);
		JLabel lblNewLabel_5_2 = new JLabel("");
		lblNewLabel_5_2.setBounds(152, 164, 0, 0);
		lblNewLabel_5_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_5_2.setForeground(Color.WHITE);
		JLabel lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.setBounds(152, 82, 0, 0);
		lblNewLabel_3_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3_2.setForeground(Color.WHITE);

		if (school.size() >= 3) {
			lblNewLabel_3_2.setText("1등 : " + studentAll.get(0).getId() + " " + studentAll.get(0).getPoint());
			lblNewLabel_4_2.setText("2등 : " + studentAll.get(1).getId() + " " + studentAll.get(1).getPoint());
			lblNewLabel_5_2.setText("3등 : " + studentAll.get(2).getId() + " " + studentAll.get(2).getPoint());
		} else if (school.size() == 2) {
			lblNewLabel_3_2.setText("1등 : " + studentAll.get(0).getId() + " " + studentAll.get(0).getPoint());
			lblNewLabel_4_2.setText("2등 : " + studentAll.get(1).getId() + " " + studentAll.get(1).getPoint());
		} else {
			lblNewLabel_3_2.setText("1등 : " + studentAll.get(0).getId() + " " + studentAll.get(0).getPoint());
		}

		JLabel lblNewLabel_1 = new JLabel("<dynamic>  길드내 랭킹");
		lblNewLabel_1.setBounds(37, 38, 204, 15);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.WHITE);
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setBounds(37, 118, 0, 0);
		lblNewLabel_4_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_4_1.setForeground(Color.WHITE);
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setBounds(37, 162, 0, 0);
		lblNewLabel_5_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_5_1.setForeground(Color.WHITE);
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setBounds(37, 80, 0, 0);
		lblNewLabel_3_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3_1.setForeground(Color.WHITE);

		if (classList.size() >= 3) {
			lblNewLabel_3_1.setText("1등 : " + classList.get(0).getId() + " " + classList.get(0).getPoint());
			lblNewLabel_4_1.setText("2등 : " + classList.get(1).getId() + " " + classList.get(1).getPoint());
			lblNewLabel_5_1.setText("3등 : " + classList.get(2).getId() + " " + classList.get(2).getPoint());
		} else if (studentAll.size() == 2) {
			lblNewLabel_3_1.setText("1등 : " + classList.get(0).getId() + " " + classList.get(0).getPoint());
			lblNewLabel_4_1.setText("2등 : " + classList.get(1).getId() + " " + classList.get(1).getPoint());
		} else {
			lblNewLabel_3_1.setText("1등 : " + classList.get(0).getId() + " " + classList.get(0).getPoint());
		}

		JLabel lblNewLabel = new JLabel("길드별 랭킹");
		lblNewLabel.setBounds(78, 38, 95, 15);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.WHITE);
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(96, 82, 0, 0);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3.setForeground(Color.WHITE);
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(96, 120, 0, 0);
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_4.setForeground(Color.WHITE);
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(96, 209, 0, 0);
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_5.setForeground(Color.WHITE);

		if (studentAll.size() >= 3) {
			lblNewLabel_3.setText("1등 : " + school.get(0).getName() + " " + school.get(0).getPointAll());
			lblNewLabel_4.setText("2등 : " + school.get(1).getName() + " " + school.get(1).getPointAll());
			lblNewLabel_5.setText("3등 : " + school.get(2).getName() + " " + school.get(2).getPointAll());
		} else if (studentAll.size() == 2) {
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
		
		System.out.println(school.toString());
		System.out.println(studentAll.toString());
		System.out.println(classList.toString());
	}
}