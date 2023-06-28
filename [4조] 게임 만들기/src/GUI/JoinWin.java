package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JoinWin frame = new JoinWin();
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
	public JoinWin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		setUndecorated(true); // 창 프레임 없애기
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// 가입창 프레임 설정
		setTitle("Join"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.
		JLabel IDlbl = new JLabel("ID"); // ID
		IDlbl.setForeground(Color.WHITE);
		IDlbl.setBackground(Color.WHITE);
		IDlbl.setBounds(45, 113, 11, 15);
		JLabel PWlbl = new JLabel("PW"); // PW
		PWlbl.setForeground(Color.WHITE);
		PWlbl.setBackground(Color.WHITE);
		PWlbl.setBounds(38, 180, 18, 15);
		JLabel PWPWlbl = new JLabel("PW"); // PW 중복확인
		PWPWlbl.setForeground(Color.WHITE);
		PWPWlbl.setBackground(Color.WHITE);
		PWPWlbl.setBounds(38, 244, 18, 15);
		JLabel Schoollbl = new JLabel("School"); // 학교
		Schoollbl.setForeground(Color.WHITE);
		Schoollbl.setBackground(Color.WHITE);
		Schoollbl.setBounds(17, 313, 39, 15);

		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(17, 358, 259, 68);

		contentPane.add(lblNewLabel);

		JButton Joinbtn = new JButton(""); // 가입버튼
		Joinbtn.setIcon(new ImageIcon(JoinWin.class.getResource("/이미지/가입하기버튼.png")));
		Joinbtn.setBounds(106, 436, 81, 39);
		// 가입하기버튼을 누르면 LoginWin으로 이동하는 액션리스너
		Joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (count == 1) {
					IDlbl.setForeground(Color.WHITE);
					if (PWField.getText().equals(PWPWField.getText())) {
						PWlbl.setForeground(Color.WHITE);
						PWPWlbl.setForeground(Color.WHITE);

						lblNewLabel.setText(" ");

						String id = IDField.getText();
						String password = PWField.getText();
						String school = SchoolField.getText();

						if (!(school.equals(""))) {
							Schoollbl.setForeground(Color.WHITE);

							int result = login.insert(new Student(id, password, school, 0));
							if (result == 1) {
								lblNewLabel.setText("회원가입이 완료되었습니다.");

								LoginWin LW = new LoginWin();
								LW.setVisible(true);
								dispose();
							}
							if (result == -1) {
								lblNewLabel.setText("회원가입이 실패했습니다.");
							}
							if (result == -2) {
								lblNewLabel.setText("<html>아이디는 영소문자, 대문자, 숫자를 포함해야 합니다.(8 ~ 20자)</html>");
								IDlbl.setForeground(Color.RED);
							} else {
								IDlbl.setForeground(Color.WHITE);
							}
							if (result == -3) {
								lblNewLabel.setText("<html>비밀번호는 영소문자, 대문자, 숫자, 특수문자를 포함해야 합니다.(8 ~ 20자)</html>");
								PWlbl.setForeground(Color.RED);
								PWPWlbl.setForeground(Color.RED);
							} else {
								PWlbl.setForeground(Color.WHITE);
								PWPWlbl.setForeground(Color.WHITE);
							}

						} else {
							lblNewLabel.setText("소속된 학교를 입력해주세요");
							Schoollbl.setForeground(Color.RED);
						}

					} else {
						lblNewLabel.setText("비밀번호가 일치하지 않습니다.");
						PWlbl.setForeground(Color.RED);
						PWPWlbl.setForeground(Color.RED);
					}
				} else {
					lblNewLabel.setText("중복확인을 해주세요");
					IDlbl.setForeground(Color.RED);
				}
			}
		});
		JButton Overlapbtn = new JButton(""); // ID 중복확인 버튼
		Overlapbtn.setIcon(new ImageIcon(JoinWin.class.getResource("/이미지/중복확인버튼.png")));
		Overlapbtn.setBounds(196, 62, 80, 30);

		Overlapbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (login.duplication(IDField.getText())) {
					count = 0;
					lblNewLabel.setText("중복된 아이디가 있습니다.");
					IDlbl.setForeground(Color.RED);
				} else {
					if (!(IDField.getText().equals(""))) {
						count = 1;
						lblNewLabel.setText("사용 가능한 아이디입니다.");
						IDlbl.setForeground(Color.WHITE);
					} else {
						lblNewLabel.setText("사용 불가능한 아이디입니다.");
					}
				}
			}
		});

		IDField = new JTextField(); // ID 입력
		IDField.setBounds(83, 104, 165, 33);
		IDField.setColumns(10);

		PWField = new JPasswordField(); // PW 입력
		PWField.setBounds(83, 171, 165, 33);
		PWField.setColumns(10);

		PWPWField = new JPasswordField(); // PW 중복입력
		PWPWField.setBounds(83, 235, 165, 33);
		PWPWField.setColumns(10);
		PWField.setEchoChar('●');

		SchoolField = new JTextField(""); // 학교 입력
		SchoolField.setBounds(83, 304, 165, 33);
		SchoolField.setColumns(10);
		PWPWField.setEchoChar('●');
		contentPane.setLayout(null);
		contentPane.add(Joinbtn);
		contentPane.add(Overlapbtn);
		contentPane.add(Schoollbl);
		contentPane.add(PWPWlbl);
		contentPane.add(PWlbl);
		contentPane.add(IDlbl);
		contentPane.add(PWPWField);
		contentPane.add(SchoolField);
		contentPane.add(PWField);
		contentPane.add(IDField);

	}
}