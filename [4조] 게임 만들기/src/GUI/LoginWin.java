package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
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

import 메소드모음.Login;
import 소리모음.BasicMusic;
import 유틸.Music;
import 유틸.SoundButton;
import 유틸.Util;

public class LoginWin extends JFrame {
	private JPanel contentPane;
	private JTextField IDField;
	private JPasswordField PWField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWin frame = new LoginWin();
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
	public LoginWin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 230); // 프레임 크기
		setUndecorated(true); // 창 프레임 없애기
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 로그인 프레임 설정
		setTitle("Login"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);
		IDField = new JTextField();
		IDField.setBounds(84, 38, 164, 30);
		IDField.setColumns(10);

		PWField = new JPasswordField();
		PWField.setBounds(84, 86, 164, 30);
		PWField.setColumns(10);

		JLabel IDlbl = new JLabel("ID"); // 아이디 라벨
		IDlbl.setFont(new Font("굴림", Font.BOLD, 12));
		IDlbl.setBounds(44, 45, 28, 15);
		IDlbl.setForeground(Color.WHITE);

		JLabel PWlbl = new JLabel("PW"); // 패스워드 라벨
		PWlbl.setFont(new Font("굴림", Font.BOLD, 12));
		PWlbl.setBounds(44, 93, 28, 15);
		PWlbl.setForeground(Color.WHITE);

		SoundButton Joinbtn = new SoundButton(Music.S1); // 가입하기 버튼
		Joinbtn.setBounds(57, 162, 80, 40);
		Joinbtn.setIcon(new ImageIcon(LoginWin.class.getResource("/이미지/가입하기버튼.png")));
		// 가입하기버튼을 누르면 JoinWin으로 이동하는 액션리스너
		Joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinWin JW = new JoinWin();
				JW.setVisible(true);
				dispose();
			}
		});
		

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 122, 276, 30);
		contentPane.add(lblNewLabel);

		SoundButton Loginbtn = new SoundButton(Music.S1); // 로그인 버튼
		Loginbtn.setBounds(156, 162, 80, 40);
		Loginbtn.setIcon(new ImageIcon(LoginWin.class.getResource("/이미지/로그인버튼.png")));
		// 가입하기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				// 존재하는 아이디인지 확인하기
				if (login.duplication(IDField.getText())) {
					if (login.login(IDField.getText(), PWField.getText()) != null) {
						lblNewLabel.setText("");
						MainWin MW = new MainWin(login.login(IDField.getText(), PWField.getText()));
						MW.setVisible(true);
						BasicMusic bm = new BasicMusic();
						bm.setVisible(false);
						dispose();
					} else {
						lblNewLabel.setText("아이디와 비밀번호가 일치하지 않습니다.");
					}
				} else {
					lblNewLabel.setText("존재하지 않는 아이디 입니다.");
				}

			}
		});

//		SoundButton Loginbtn = new SoundButton(Music.S1); // 로그인 버튼
//		Loginbtn.setBounds(158, 137, 80, 40);
//		Loginbtn.setIcon(new ImageIcon(LoginWin.class.getResource("/이미지/로그인버튼.png")));
//		// 가입하기버튼을 누르면 MainWin으로 이동하는 액션리스너
//		Loginbtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Login login = new Login();
//				if (login.login(IDField.getText(), PWField.getText()) != null) {
//					MainWin MW = new MainWin(login.login(IDField.getText(), PWField.getText()));
//					MW.setVisible(true);
//					dispose();
//				} else {
//					System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
//				}
//
//			}
//		});

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
		cutbtn.setBounds(260, 10, 30, 30);

		Container c = getContentPane();
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(Joinbtn);
		contentPane.add(Loginbtn);
		contentPane.add(IDlbl);
		contentPane.add(PWlbl);
		contentPane.add(IDField);
		contentPane.add(PWField);
		contentPane.add(cutbtn);

		setVisible(true); // 창이 보이게
		Util.removeAllButtonFocus(contentPane);
	}
}