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
import 메소드모음.InsertPoint;
import 메소드모음.Login;
import java.awt.Font;

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
		IDlbl.setFont(new Font("굴림", Font.BOLD, 12));
		IDlbl.setForeground(Color.WHITE);
		IDlbl.setBackground(Color.WHITE);
		IDlbl.setBounds(46, 139, 26, 15);
		JLabel PWlbl = new JLabel("PW"); // PW
		PWlbl.setFont(new Font("굴림", Font.BOLD, 12));
		PWlbl.setForeground(Color.WHITE);
		PWlbl.setBackground(Color.WHITE);
		PWlbl.setBounds(39, 206, 33, 15);
		JLabel PWPWlbl = new JLabel("PW"); // PW 중복확인
		PWPWlbl.setFont(new Font("굴림", Font.BOLD, 12));
		PWPWlbl.setForeground(Color.WHITE);
		PWPWlbl.setBackground(Color.WHITE);
		PWPWlbl.setBounds(39, 270, 33, 15);
		JLabel Schoollbl = new JLabel("Group"); // 학교
		Schoollbl.setFont(new Font("굴림", Font.BOLD, 12));
		Schoollbl.setForeground(Color.WHITE);
		Schoollbl.setBackground(Color.WHITE);
		Schoollbl.setBounds(28, 339, 54, 15);

		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(38, 364, 240, 68);

		contentPane.add(lblNewLabel);

		JButton Joinbtn = new JButton(""); // 가입버튼
		Joinbtn.setIcon(new ImageIcon(JoinWin.class.getResource("/이미지/가입하기버튼.png")));
		Joinbtn.setBounds(110, 428, 81, 39);
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
		Overlapbtn.setBounds(169, 90, 80, 30);

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

		JButton Backbtn = new JButton(""); // 뒤로가기
		Backbtn.setBackground(Color.BLACK);
		Backbtn.setBorderPainted(false); // 버튼 테두리 제거
		Backbtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginWin();
				setVisible(false);
			}
		});

		Backbtn.setLocation(248, 450);
		Backbtn.setSize(40, 40);

		IDField = new JTextField(); // ID 입력
		IDField.setBounds(84, 130, 165, 33);
		IDField.setColumns(10);

		PWField = new JPasswordField(); // PW 입력
		PWField.setBounds(84, 197, 165, 33);
		PWField.setColumns(10);

		PWPWField = new JPasswordField(); // PW 중복입력
		PWPWField.setBounds(84, 261, 165, 33);
		PWPWField.setColumns(10);
		PWField.setEchoChar('●');

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
		cutbtn.setBounds(258, 10, 30, 30);
		contentPane.add(cutbtn);
		
		SchoolField = new JTextField(""); // 학교 입력
		SchoolField.setBounds(84, 330, 165, 33);
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
		contentPane.add(Backbtn);
		
	}
}