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
import javax.swing.border.EmptyBorder;

import 메소드모음.Login;

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
		setBounds(100, 100, 302, 465); // 프레임 크기
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 로그인 프레임 설정
		setTitle("Login"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		IDField = new JTextField();
		IDField.setBounds(83, 20, 164, 30);
		IDField.setColumns(10);

		PWField = new JPasswordField();
		PWField.setBounds(83, 68, 164, 30);
		PWField.setColumns(10);

		JLabel IDlbl = new JLabel("ID"); // 아이디 라벨
		IDlbl.setBounds(47, 27, 11, 15);
		JLabel PWlbl = new JLabel("PW"); // 패스워드 라벨
		PWlbl.setBounds(47, 75, 18, 15);

		JButton Joinbtn = new JButton("가입하기"); // 가입하기 버튼
		Joinbtn.setBounds(59, 116, 81, 40);
		// 가입하기버튼을 누르면 JoinWin으로 이동하는 액션리스너
		Joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinWin JW = new JoinWin();
				JW.setVisible(true);
				dispose();
			}
		});

		JButton Loginbtn = new JButton("로그인"); // 로그인 버튼
		Loginbtn.setBounds(158, 116, 69, 40);
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
		setVisible(true); // 창이 보이게
	}
}