package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import 객체모음.Student;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 프레임 설정
		setTitle("랭킹확인"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		JButton Backbtn = new JButton(); // 뒤로가기 버튼
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
			}
		});

		JLabel lblNewLabel_2 = new JLabel("1");

		JPanel Schoolpanel = new JPanel(); // 학교 랭킹
		school = RS.schoolRangking();
		JPanel Studentpanel = new JPanel(); // 학생 랭킹
		classList = RS.classRangking(s);
		JPanel AllStudentpanel = new JPanel(); // 전체 랭킹
		studentAll = RS.studentRangking(Integer.valueOf(lblNewLabel_2.getText()));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(Schoolpanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(Studentpanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(AllStudentpanel, GroupLayout.PREFERRED_SIZE, 250,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(24, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(734, Short.MAX_VALUE)
								.addComponent(Backbtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addComponent(Backbtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(111)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Schoolpanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(Studentpanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(AllStudentpanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(150, Short.MAX_VALUE)));

		JLabel lblNewLabel_4_2 = new JLabel("");
		JLabel lblNewLabel_5_2 = new JLabel("");
		JLabel lblNewLabel_3_2 = new JLabel("");

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

		GroupLayout gl_AllStudentpanel = new GroupLayout(AllStudentpanel);
		gl_AllStudentpanel.setHorizontalGroup(
				gl_AllStudentpanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_AllStudentpanel.createSequentialGroup().addContainerGap(101, Short.MAX_VALUE)
								.addGroup(gl_AllStudentpanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3_2).addComponent(lblNewLabel_4_2)
										.addComponent(lblNewLabel_5_2).addComponent(lblNewLabel_2))
								.addGap(92)));
		gl_AllStudentpanel.setVerticalGroup(gl_AllStudentpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AllStudentpanel.createSequentialGroup().addGap(31).addComponent(lblNewLabel_2).addGap(36)
						.addComponent(lblNewLabel_3_2).addGap(38).addComponent(lblNewLabel_4_2).addGap(44)
						.addComponent(lblNewLabel_5_2).addContainerGap(41, Short.MAX_VALUE)));
		AllStudentpanel.setLayout(gl_AllStudentpanel);

		JLabel lblNewLabel_1 = new JLabel(classList.get(1).getSchool() + "  교내랭킹");
		JLabel lblNewLabel_4_1 = new JLabel("");
		JLabel lblNewLabel_5_1 = new JLabel("");
		JLabel lblNewLabel_3_1 = new JLabel("");

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

		GroupLayout gl_Studentpanel = new GroupLayout(Studentpanel);
		gl_Studentpanel
				.setHorizontalGroup(gl_Studentpanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_Studentpanel.createSequentialGroup().addContainerGap(101, Short.MAX_VALUE)
								.addGroup(gl_Studentpanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3_1).addComponent(lblNewLabel_4_1)
										.addComponent(lblNewLabel_5_1).addComponent(lblNewLabel_1))
								.addGap(92)));
		gl_Studentpanel.setVerticalGroup(gl_Studentpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Studentpanel.createSequentialGroup().addGap(28).addComponent(lblNewLabel_1).addGap(37)
						.addComponent(lblNewLabel_3_1).addGap(38).addComponent(lblNewLabel_4_1).addGap(44)
						.addComponent(lblNewLabel_5_1).addContainerGap(43, Short.MAX_VALUE)));
		Studentpanel.setLayout(gl_Studentpanel);

		JLabel lblNewLabel = new JLabel("학교별 랭킹");
		JLabel lblNewLabel_3 = new JLabel("");
		JLabel lblNewLabel_4 = new JLabel("");
		JLabel lblNewLabel_5 = new JLabel("");

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

		GroupLayout gl_Schoolpanel = new GroupLayout(Schoolpanel);
		gl_Schoolpanel.setHorizontalGroup(gl_Schoolpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Schoolpanel.createSequentialGroup().addGap(96)
						.addGroup(gl_Schoolpanel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_4).addComponent(lblNewLabel_3).addComponent(lblNewLabel))
						.addContainerGap(97, Short.MAX_VALUE)));
		gl_Schoolpanel.setVerticalGroup(gl_Schoolpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Schoolpanel.createSequentialGroup().addGap(24).addComponent(lblNewLabel).addGap(43)
						.addComponent(lblNewLabel_3).addGap(38).addComponent(lblNewLabel_4)
						.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE).addComponent(lblNewLabel_5)
						.addGap(41)));
		Schoolpanel.setLayout(gl_Schoolpanel);
		contentPane.setLayout(gl_contentPane);
	}

}