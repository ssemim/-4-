package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import 객체모음.Student;
import 메소드모음.EquipmentItem;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class SelectgameWin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectgameWin frame = new SelectgameWin();
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
	public SelectgameWin(Student s, String[] equipmentName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 게임선택 프레임 설정
		setTitle("게임선택"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		JButton Dinobtn = new JButton("RUN");
		Dinobtn.setBounds(120, 122, 270, 80);
		// 게임하기버튼을 누르면 SelectgameWin으로 이동하는 액션리스너
		Dinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DinogameWin DW = new DinogameWin(s, equipmentName);
				DW.setVisible(true);
				dispose();
			}
		});

		JPanel Charpnl = new JPanel(); // 캐릭터 패널
		EquipmentItem.equipmentItem(equipmentName, Charpnl);
		Charpnl.setBounds(511, 170, 150, 200);

		JButton Backbtn = new JButton(""); // 뒤로가기버튼(이미지처리할거임)
		Backbtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.setBounds(624, 115, 40, 40);
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
			}
		});
		
		JButton btnRun = new JButton("HANGMAN");
		btnRun.setBounds(120, 216, 270, 80);
		
		JButton btnRun_1 = new JButton("FALL");
		btnRun_1.setBounds(120, 405, 270, 80);
		contentPane.setLayout(null);
		contentPane.add(btnRun);
		contentPane.add(btnRun_1);
		contentPane.add(Dinobtn);
		contentPane.add(Backbtn);
		contentPane.add(Charpnl);
		
		JButton Dinobtn_2_1 = new JButton("NUMBER");
		Dinobtn_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Dinobtn_2_1.setBounds(120, 311, 270, 80);
		contentPane.add(Dinobtn_2_1);
		
		JLabel Coinlbl = new JLabel("1.000");
		Coinlbl.setForeground(Color.WHITE);
		Coinlbl.setBounds(533, 140, 60, 15);
		contentPane.add(Coinlbl);
	}

}