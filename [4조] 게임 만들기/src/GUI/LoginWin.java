package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Color;

public class LoginWin extends JFrame {
	private JPanel contentPane;
	private JTextField IDField;
	private JTextField PWField;
	
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

		PWField = new JTextField();
		PWField.setBounds(90, 90, 164, 30);
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
				MainWin MW = new MainWin();
				MW.setVisible(true);
				dispose();
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
		setVisible(true); // 창이 보이게
	}
}
