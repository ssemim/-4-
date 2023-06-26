package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import 객체모음.Student;
import 메소드모음.PickItem;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StoreWin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreWin frame = new StoreWin(new Student("dd", "당리초", 2200000));
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
	public StoreWin(Student s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 프레임 설정
		setTitle("상점"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVisible(false);
		JButton Backbtn = new JButton(); // 뒤로가기 버튼
		Backbtn.setBounds(711, 167, 40, 40);
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin();
				MW.setVisible(true);
				dispose();
			}
		});

		JLabel Coinlbl = new JLabel("1.000");
		Coinlbl.setBounds(640, 184, 60, 15);

		JPanel Charpnl = new JPanel();
		Charpnl.setBounds(601, 217, 150, 200);

		JButton Charbtn = new JButton("캐릭터 뽑기");
		Charbtn.setBounds(38, 167, 246, 250);
		Charbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (s.getPoint() >= 1000) {
					int point = s.getPoint();
					PickItem pick = new PickItem(s, "캐릭터");
					int item = pick.pickItem();
					System.out.println(item);
					s.setPoint(point - 1000);
					lblNewLabel.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/캐릭터" + item + ".gif")));
					lblNewLabel.setVisible(true);
				}
			}
		});

		JButton BackWinbtn = new JButton("배경 뽑기");
		BackWinbtn.setBounds(316, 167, 246, 250);

		BackWinbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (s.getPoint() >= 1000) {
					int a = s.getPoint();
					PickItem pick = new PickItem(s, "배경");
					int item = pick.pickItem();
					s.setPoint(a - 1000);
					lblNewLabel.setIcon(new ImageIcon(StoreWin.class.getResource("/이미지/150150 배경더미" + item + ".png")));
					lblNewLabel.setVisible(true);
				}
			}
		});
		contentPane.setLayout(null);

		lblNewLabel.setBounds(223, 118, 361, 338);
		contentPane.add(lblNewLabel);
		contentPane.add(Charbtn);
		contentPane.add(BackWinbtn);
		contentPane.add(Coinlbl);
		contentPane.add(Backbtn);
		contentPane.add(Charpnl);
	}
}
