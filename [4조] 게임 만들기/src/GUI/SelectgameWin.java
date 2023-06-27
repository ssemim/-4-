package GUI;

import java.awt.EventQueue;
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
import java.awt.Color;

public class SelectgameWin extends JFrame {

	private JPanel contentPane;
	private JTextField CoinField;

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
	public SelectgameWin(Student s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setUndecorated(true); // 창 프레임 없애기
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 게임선택 프레임 설정
		setTitle("게임선택"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		JButton Dinobtn = new JButton();
		Dinobtn.setBounds(120, 115, 274, 84);
		// 게임하기버튼을 누르면 SelectgameWin으로 이동하는 액션리스너
		Dinobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DinogameWin DW = new DinogameWin(s);
				DW.setVisible(true);
				dispose();
			}
		});

		JPanel Charpnl = new JPanel(); // 캐릭터 패널
		Charpnl.setBounds(511, 170, 150, 200);

		JButton Backbtn = new JButton(); // 뒤로가기버튼(이미지처리할거임)
		Backbtn.setBounds(624, 115, 40, 40);
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
			}
		});

		CoinField = new JTextField();
		CoinField.setBounds(511, 134, 95, 21);
		CoinField.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(Dinobtn);
		contentPane.add(CoinField);
		contentPane.add(Backbtn);
		contentPane.add(Charpnl);
	}

}
