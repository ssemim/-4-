package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import 객체모음.Student;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MainWin extends JFrame {

	private JPanel contentPane;
	Image image;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWin frame = new MainWin();
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
	public MainWin(Student s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600); // 프레임 크기
		setUndecorated(true); //ㅊ ㅍㄹㅇ ㅇㅇㄱ
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// 메인창 프레임 설정
		setTitle("Main"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.


		// 게임하기버튼을 누르면 SelectgameWin으로 이동하는 액션리스너
		JButton Gamebtn = new JButton(); // 인벤 버튼
		Gamebtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/게임하기버튼.png")));
		Gamebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectgameWin SlecW = new SelectgameWin(s);
				SlecW.setVisible(true);
				dispose();
			}
		});
		
		JButton invenbtn = new JButton(""); // 인벤 버튼
		invenbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/인벤토리버튼.png")));
		// 인벤토리버튼을 누르면 invenWin으로 이동하는 액션리스너
		invenbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invenWin IW = new invenWin(s);
				IW.setVisible(true);
				dispose();
			}
		});
		
		JButton rankbtn = new JButton(""); // 랭킹 버튼
		rankbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/랭킹버튼.png")));
		// 랭킹버튼을 누르면 RankWin으로 이동하는 액션리스너
		rankbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RankWin RW = new RankWin(s);
				RW.setVisible(true);
				dispose();
			}
		});
		JButton Storebtn = new JButton(""); // 상점 버튼
		Storebtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/상점버튼.png")));
		// 상점버튼을 누르면 StoreWin으로 이동하는 액션리스너
		Storebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreWin StoreW = new StoreWin(s);
				StoreW.setVisible(true);
				dispose();
			}
		});
		JPanel Charpanel = new JPanel(); // 캐릭터 이미지가 나오는 Panel

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(124)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Storebtn, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(rankbtn, GroupLayout.PREFERRED_SIZE, 240,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(invenbtn, GroupLayout.PREFERRED_SIZE, 240,
												GroupLayout.PREFERRED_SIZE))
								.addGap(133)
								.addComponent(Charpanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)) // 캐릭터
																														// 판넬
																														// 가로
						.addComponent(Gamebtn, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(137, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(77, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(Charpanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE) // 캐릭터 판넬
																												// 가로
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(Gamebtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(31)
								.addComponent(invenbtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(29)
								.addComponent(rankbtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
				.addGap(32).addComponent(Storebtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
				.addGap(72)));
		contentPane.setLayout(gl_contentPane);
	}
}