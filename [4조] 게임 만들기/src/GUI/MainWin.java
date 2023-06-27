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
import 메소드모음.equipmentItem;
import javax.swing.LayoutStyle.ComponentPlacement;

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
//					Student s = new Student();
//					MainWin frame = new MainWin(s);
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// 메인창 프레임 설정
		setTitle("Main"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.


		// 게임하기버튼을 누르면 SelectgameWin으로 이동하는 액션리스너
		JButton Gamebtn = new JButton(); // 인벤 버튼
		Gamebtn.setBounds(129, 82, 240, 80);
		Gamebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectgameWin SlecW = new SelectgameWin(s);
				SlecW.setVisible(true);
				dispose();
			}
		});
		
		JButton invenbtn = new JButton("인벤토리"); // 인벤 버튼
		invenbtn.setBounds(129, 193, 240, 80);
		// 인벤토리버튼을 누르면 invenWin으로 이동하는 액션리스너
		invenbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invenWin IW = new invenWin(s);
				IW.setVisible(true);
				dispose();
			}
		});
		
		JButton rankbtn = new JButton("랭킹"); // 랭킹 버튼
		rankbtn.setBounds(129, 302, 240, 80);
		// 랭킹버튼을 누르면 RankWin으로 이동하는 액션리스너
		rankbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RankWin RW = new RankWin(s);
				RW.setVisible(true);
				dispose();
			}
		});
		JButton Storebtn = new JButton("상점"); // 상점 버튼
		Storebtn.setBounds(129, 414, 240, 80);
		// 상점버튼을 누르면 StoreWin으로 이동하는 액션리스너
		Storebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreWin StoreW = new StoreWin(s);
				StoreW.setVisible(true);
				dispose();
			}
		});
		
		equipmentItem e = new equipmentItem();
		JPanel Charpanel = new JPanel();
		e.equipmentItem(e.selectItemIamgeName(e.itemNos(s)), Charpanel);
		Charpanel.setBounds(502, 182, 150, 200);
		contentPane.setLayout(null);
		contentPane.add(Storebtn);
		contentPane.add(rankbtn);
		contentPane.add(invenbtn);
		contentPane.add(Charpanel);
		contentPane.add(Gamebtn);
	}
}