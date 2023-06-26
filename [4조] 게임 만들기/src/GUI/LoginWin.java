package GUI;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
<<<<<<< HEAD
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Color;
=======
import javax.swing.border.EmptyBorder;

import 메소드모음.Login;
>>>>>>> branch 'master' of https://github.com/ssemim/-4-.git

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
		setBounds(100, 100, 300, 200); // 프레임 크기
		setUndecorated(true); // 프레임 타이틀바 삭제
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 로그인 프레임 설정
		setTitle("Login"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);

		IDField = new JTextField();
		IDField.setBounds(90, 42, 164, 30);
		IDField.setColumns(10);

<<<<<<< HEAD
		PWField = new JTextField();
		PWField.setBounds(90, 90, 164, 30);
=======
		PWField = new JPasswordField();
		PWField.setBounds(83, 68, 164, 30);
>>>>>>> branch 'master' of https://github.com/ssemim/-4-.git
		PWField.setColumns(10);

		JLabel IDlbl = new JLabel("ID"); // 아이디 라벨
		IDlbl.setBounds(54, 49, 11, 15);
		IDlbl.setForeground(Color.WHITE);
		JLabel PWlbl = new JLabel("PW"); // 패스워드 라벨
		PWlbl.setBounds(49, 97, 20, 20);
		PWlbl.setForeground(Color.WHITE);

		JButton Joinbtn = new JButton(); // 가입하기 버튼
		Joinbtn.setBounds(54, 139, 80, 40);
		Joinbtn.setIcon(new ImageIcon(LoginWin.class.getResource("/이미지/가입하기버튼.png")));
		Joinbtn.setContentAreaFilled(false); // 내용 채우기 X
		// 가입하기버튼을 누르면 JoinWin으로 이동하는 액션리스너
		Joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinWin JW = new JoinWin();
				JW.setVisible(true);
				dispose();
			}
		});

		JButton Loginbtn = new JButton(); // 로그인 버튼
		Loginbtn.setBounds(165, 139, 80, 40);
		Loginbtn.setIcon(new ImageIcon(LoginWin.class.getResource("/이미지/로그인버튼.png")));
		Loginbtn.setContentAreaFilled(false); // 내용 채우기 X
		// 가입하기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				if (login.login(IDField.getText(), PWField.getText()) != null) {
					MainWin MW = new MainWin();
					MW.setVisible(true);
					dispose();
				} else {
					System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
				}

			}
		});

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

		JPanel panel = new JPanel();
		panel.setBounds(12, 243, 272, 193);
		contentPane.add(panel);
		panel.setLayout(null);
<<<<<<< HEAD
=======

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(12, 70, 97, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(58, 70, 97, 23);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LoginWin.class.getResource("/이미지/로그인더미.png")));
		lblNewLabel.setBounds(0, 0, 296, 179);
		contentPane.add(lblNewLabel);
>>>>>>> branch 'master' of https://github.com/ssemim/-4-.git
		setVisible(true); // 창이 보이게
	}
}