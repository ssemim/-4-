package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 가입창 프레임 설정
		setTitle("Join"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.
		
		JLabel IDlbl = new JLabel("ID"); // ID
		JLabel PWlbl = new JLabel("PW"); // PW
		JLabel PWPWlbl = new JLabel("PW"); // PW 중복확인
		JLabel Schoollbl = new JLabel("School"); // 학교
		
		JButton Joinbtn = new JButton("가입하기"); // 가입버튼
		JButton Overlapbtn = new JButton("중복확인"); // ID 중복확인 버튼
		
		IDField = new JTextField(); // ID 입력
		IDField.setColumns(10);
		
		PWField = new JTextField(); // PW 입력
		PWField.setColumns(10);
		
		PWPWField = new JTextField(); // PW 중복입력
		PWPWField.setColumns(10);
		
		SchoolField = new JTextField(); // 학교 입력
		SchoolField.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(101)
					.addComponent(Joinbtn)
					.addContainerGap(102, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(191, Short.MAX_VALUE)
					.addComponent(Overlapbtn)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(Schoollbl)
						.addComponent(PWPWlbl)
						.addComponent(PWlbl)
						.addComponent(IDlbl))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(PWPWField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(SchoolField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addComponent(PWField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addComponent(IDField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addComponent(Overlapbtn)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(IDlbl))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(PWField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(PWlbl))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(PWPWField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(PWPWlbl))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(SchoolField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(Schoollbl))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(Joinbtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
