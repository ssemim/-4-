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

public class DinogameWin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DinogameWin frame = new DinogameWin();
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
	public DinogameWin(Student s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 공룡게임 프레임 설정
		setTitle("냥곤런"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		// 게임실행 판넬
		JPanel Gamepanel = new JPanel();
		Gamepanel.setBounds(42, 218, 560, 240);

		JPanel Charpnl = new JPanel(); // 게임 캐릭터 판넬

		JButton Restartbtn = new JButton("다시하기"); // 다시하기 버튼
		Restartbtn.setEnabled(false);

		JLabel Coinlbl = new JLabel("1.000"); // 포인트 확인 라벨
		Coinlbl.setLocation(681, 235);

		JButton Backbtn = new JButton(); // 뒤로가기 버튼
		// 뒤로가기버튼을 누르면 SelectgameWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectgameWin SW = new SelectgameWin(s);
				SW.setVisible(true);
				dispose();
			}
		});

		Backbtn.setBounds(733, 210, 40, 40);
		contentPane.add(Backbtn);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(42)
				.addComponent(Gamepanel, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(55).addComponent(Coinlbl,
								GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(Restartbtn, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(Charpnl, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(13)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(146)
				.addComponent(Restartbtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(16)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(Gamepanel, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(10).addComponent(Coinlbl)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(Charpnl, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))));
		contentPane.setLayout(gl_contentPane);
	}
}
