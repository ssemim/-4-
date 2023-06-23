package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;

public class invenWin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					invenWin frame = new invenWin();
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
	public invenWin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 공룡게임 프레임 설정
		setTitle("인벤토리"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.
		
		JPanel Charpnl = new JPanel();
		
		JButton Restartbtn = new JButton("다시하기");
		Restartbtn.setEnabled(false);
		
		JButton Backbtn = new JButton();
		
		JLabel Coinlbl = new JLabel("1.000");
		
		JPanel ItemCharpanel = new JPanel();
		
		JPanel ItemCharpanel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addComponent(ItemCharpanel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(ItemCharpanel_1, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Restartbtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(Coinlbl, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(Backbtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(Charpnl, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addGap(39))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(122)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(ItemCharpanel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
						.addComponent(ItemCharpanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Restartbtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Backbtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(17)
									.addComponent(Coinlbl)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Charpnl, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		GroupLayout gl_ItemCharpanel_1 = new GroupLayout(ItemCharpanel_1);
		gl_ItemCharpanel_1.setHorizontalGroup(
			gl_ItemCharpanel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_ItemCharpanel_1.createSequentialGroup()
					.addContainerGap(203, Short.MAX_VALUE)
					.addComponent(scrollBar_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_ItemCharpanel_1.setVerticalGroup(
			gl_ItemCharpanel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ItemCharpanel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollBar_1, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		ItemCharpanel_1.setLayout(gl_ItemCharpanel_1);
		
		JScrollBar scrollBar = new JScrollBar();
		GroupLayout gl_ItemCharpanel = new GroupLayout(ItemCharpanel);
		gl_ItemCharpanel.setHorizontalGroup(
			gl_ItemCharpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ItemCharpanel.createSequentialGroup()
					.addContainerGap(203, Short.MAX_VALUE)
					.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_ItemCharpanel.setVerticalGroup(
			gl_ItemCharpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ItemCharpanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
					.addContainerGap())
		);
		ItemCharpanel.setLayout(gl_ItemCharpanel);
		contentPane.setLayout(gl_contentPane);
	}
}
