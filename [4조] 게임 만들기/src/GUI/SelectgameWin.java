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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SelectgameWin extends JFrame {

	private JPanel contentPane;
	private JTextField CoinField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectgameWin frame = new SelectgameWin();
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
	public SelectgameWin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 게임선택 프레임 설정
		setTitle("게임선택"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		JButton Dinobtn = new JButton("냥곤런");
		// 게임하기버튼을 누르면 SelectgameWin으로 이동하는 액션리스너
		Dinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DinogameWin DW = new DinogameWin();
				DW.setVisible(true);
				dispose();
			}
		});

		JPanel Charpnl = new JPanel(); // 캐릭터 패널

		JButton Backbtn = new JButton("뒤"); // 뒤로가기버튼(이미지처리할거임)
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin();
				MW.setVisible(true);
				dispose();
			}
		});

		CoinField = new JTextField();
		CoinField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(115)
				.addComponent(Dinobtn, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addGap(117)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(CoinField, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(Backbtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(Charpnl, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(123, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(110)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Dinobtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(CoinField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(Backbtn, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE))
								.addGap(15)
								.addComponent(Charpnl, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(195, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

}
