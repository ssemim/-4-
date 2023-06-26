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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import 객체모음.Student;
import 메소드모음.Login;

public class JoinWin extends JFrame {
	Login login = new Login();
	int count = 0;
	private JPanel contentPane;
	private JTextField IDField;
	private JPasswordField PWField;
	private JPasswordField PWPWField;
	private JTextField SchoolField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinWin frame = new JoinWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JoinWin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		setUndecorated(true); // 프레임 타이틀바 삭제
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 가입창 프레임 설정
		setTitle("Join"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		JLabel IDlbl = new JLabel("ID"); // ID
		IDlbl.setBounds(45, 113, 11, 15);
		IDlbl.setForeground(Color.WHITE);
		JLabel PWlbl = new JLabel("PW"); // PW
		PWlbl.setBounds(38, 180, 18, 15);
		PWlbl.setForeground(Color.WHITE);
		JLabel PWPWlbl = new JLabel("PW"); // PW 중복확인
		PWPWlbl.setBounds(38, 244, 18, 15);
		PWPWlbl.setForeground(Color.WHITE);
		JLabel Schoollbl = new JLabel("School"); // 학교
<<<<<<< HEAD
		Schoollbl.setBounds(17, 313, 39, 15);
		Schoollbl.setForeground(Color.WHITE);
		
		JButton Joinbtn = new JButton(""); // 가입버튼
		Joinbtn.setBounds(106, 398, 81, 39);
		Joinbtn.setIcon(new ImageIcon(JoinWin.class.getResource("/이미지/가입하기버튼.png")));
=======

		JButton Joinbtn = new JButton("가입하기"); // 가입버튼
>>>>>>> branch 'master' of https://github.com/ssemim/-4-.git
		// 가입하기버튼을 누르면 LoginWin으로 이동하는 액션리스너
		Joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (count == 1) {
					if (PWField.getText().equals(PWPWField.getText())) {
						String id = IDField.getText();
						String password = PWlbl.getText();
						String school = SchoolField.getText();

						login.insert(new Student(id, password, school, 0));

						LoginWin LW = new LoginWin();
						LW.setVisible(true);
						dispose();
					} else {
						System.out.println("비밀번호가 일치하지 않습니다.");
					}
<<<<<<< HEAD
				});
		JButton Overlapbtn = new JButton(""); // ID 중복확인 버튼
		Overlapbtn.setIcon(new ImageIcon(JoinWin.class.getResource("/이미지/중복확인버튼.png")));
		Overlapbtn.setBounds(202, 63, 81, 30);
		
=======
				} else {
					System.out.println("중복확인을 해주세요");
				}
			}
		});
		JButton Overlapbtn = new JButton("중복확인"); // ID 중복확인 버튼

		Overlapbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (login.duplication(IDField.getText())) {
					count = 0;
				} else {
					count = 1;
				}
			}
		});

>>>>>>> branch 'master' of https://github.com/ssemim/-4-.git
		IDField = new JTextField(); // ID 입력
		IDField.setBounds(83, 104, 165, 33);
		IDField.setColumns(10);
<<<<<<< HEAD
		
		PWField = new JTextField(); // PW 입력
		PWField.setBounds(83, 171, 165, 33);
=======

		PWField = new JPasswordField(); // PW 입력
>>>>>>> branch 'master' of https://github.com/ssemim/-4-.git
		PWField.setColumns(10);
<<<<<<< HEAD
		
		PWPWField = new JTextField(); // PW 중복입력
		PWPWField.setBounds(83, 235, 165, 33);
=======

		PWPWField = new JPasswordField(); // PW 중복입력
>>>>>>> branch 'master' of https://github.com/ssemim/-4-.git
		PWPWField.setColumns(10);
		PWField.setEchoChar('●');

		SchoolField = new JTextField(); // 학교 입력
		SchoolField.setBounds(83, 304, 165, 33);
		SchoolField.setColumns(10);
<<<<<<< HEAD
		contentPane.setLayout(null);
		contentPane.add(Overlapbtn);
		contentPane.add(Schoollbl);
		contentPane.add(PWPWlbl);
		contentPane.add(PWlbl);
		contentPane.add(IDlbl);
		contentPane.add(PWPWField);
		contentPane.add(SchoolField);
		contentPane.add(PWField);
		contentPane.add(IDField);
		contentPane.add(Joinbtn);
=======
		PWPWField.setEchoChar('●');

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup().addGap(101).addComponent(Joinbtn).addContainerGap(102,
								Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(191, Short.MAX_VALUE)
						.addComponent(Overlapbtn).addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(Schoollbl)
								.addComponent(PWPWlbl).addComponent(PWlbl).addComponent(IDlbl))
						.addGap(27)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(PWPWField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 165,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(SchoolField, GroupLayout.PREFERRED_SIZE, 165,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(PWField, GroupLayout.PREFERRED_SIZE, 165,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(IDField, GroupLayout.PREFERRED_SIZE, 165,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(41, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_contentPane.createSequentialGroup().addGap(58).addComponent(Overlapbtn)
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(IDField, GroupLayout.PREFERRED_SIZE, 33,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(IDlbl))
												.addGap(34)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(PWField, GroupLayout.PREFERRED_SIZE, 33,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(PWlbl))
												.addGap(31)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(PWPWField, GroupLayout.PREFERRED_SIZE, 33,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(PWPWlbl))
												.addGap(36)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(SchoolField, GroupLayout.PREFERRED_SIZE, 33,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(Schoollbl))
												.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
												.addComponent(Joinbtn, GroupLayout.PREFERRED_SIZE, 39,
														GroupLayout.PREFERRED_SIZE)
												.addGap(44)));
		contentPane.setLayout(gl_contentPane);
>>>>>>> branch 'master' of https://github.com/ssemim/-4-.git
	}

}
