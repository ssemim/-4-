package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class JoinWin extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JTextField PWField;
	private JTextField PWPWField;
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
		Schoollbl.setBounds(17, 313, 39, 15);
		Schoollbl.setForeground(Color.WHITE);
		
		JButton Joinbtn = new JButton(""); // 가입버튼
		Joinbtn.setBounds(106, 398, 81, 39);
		Joinbtn.setIcon(new ImageIcon(JoinWin.class.getResource("/이미지/가입하기버튼.png")));
		// 가입하기버튼을 누르면 LoginWin으로 이동하는 액션리스너
		Joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWin LW = new LoginWin();
				LW.setVisible(true);
				dispose();
					}
				});
		JButton Overlapbtn = new JButton(""); // ID 중복확인 버튼
		Overlapbtn.setIcon(new ImageIcon(JoinWin.class.getResource("/이미지/중복확인버튼.png")));
		Overlapbtn.setBounds(202, 63, 81, 30);
		
		IDField = new JTextField(); // ID 입력
		IDField.setBounds(83, 104, 165, 33);
		IDField.setColumns(10);
		
		PWField = new JTextField(); // PW 입력
		PWField.setBounds(83, 171, 165, 33);
		PWField.setColumns(10);
		
		PWPWField = new JTextField(); // PW 중복입력
		PWPWField.setBounds(83, 235, 165, 33);
		PWPWField.setColumns(10);
		
		SchoolField = new JTextField(); // 학교 입력
		SchoolField.setBounds(83, 304, 165, 33);
		SchoolField.setColumns(10);
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
	}

}
