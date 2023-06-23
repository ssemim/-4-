package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class MainWin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin frame = new MainWin();
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
	public MainWin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600); // 프레임 크기
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// 메인창 프레임 설정
		setTitle("Main"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.
		
		JButton Gamebtn = new JButton("게임하기"); // 게임하기 버튼
		JButton invenbtn = new JButton("인벤토리"); // 인벤 버튼
		JButton rankbtn = new JButton("랭킹"); // 랭킹 버튼
		JButton Storebtn = new JButton("상점"); // 상점 버튼
		
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
								.addComponent(rankbtn, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(invenbtn, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
							.addGap(133)
							.addComponent(Charpanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)) // 캐릭터 판넬 가로
						.addComponent(Gamebtn, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(137, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(77, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(Charpanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE) // 캐릭터 판넬 가로
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Gamebtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(invenbtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(rankbtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addComponent(Storebtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(72))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
