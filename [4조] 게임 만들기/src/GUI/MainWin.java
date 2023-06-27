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
import javax.swing.border.LineBorder;

import 객체모음.Student;
import 메소드모음.EquipmentItem;
import javax.swing.ImageIcon;
import java.awt.Color;
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
		EquipmentItem e = new EquipmentItem();
		String[] equipmentName = e.selectItemIamgeName(e.itemNos(s));
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
		Gamebtn.setBounds(129, 111, 240, 80);
		Gamebtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/게임하기버튼.png")));
		Gamebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectgameWin SlecW = new SelectgameWin(s, equipmentName);
				SlecW.setVisible(true);
				dispose();
			}
		});
		
		JButton invenbtn = new JButton(); // 인벤 버튼
		invenbtn.setBounds(129, 222, 240, 80);
		invenbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/인벤토리버튼.png")));
		// 인벤토리버튼을 누르면 invenWin으로 이동하는 액션리스너
		invenbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invenWin IW = new invenWin(s, equipmentName);
				IW.setVisible(true);
				dispose();
			}
		});
		
		JButton rankbtn = new JButton(); // 랭킹 버튼
		rankbtn.setBounds(129, 331, 240, 80);
		rankbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/랭킹버튼.png")));
		// 랭킹버튼을 누르면 RankWin으로 이동하는 액션리스너
		rankbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RankWin RW = new RankWin(s);
				RW.setVisible(true);
				dispose();
			}
		});
		JButton Storebtn = new JButton(); // 상점 버튼
		Storebtn.setBounds(129, 443, 240, 80);
		Storebtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/상점버튼.png")));
		// 상점버튼을 누르면 StoreWin으로 이동하는 액션리스너
		Storebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreWin StoreW = new StoreWin(s, equipmentName);
				StoreW.setVisible(true);
				dispose();
			}
		});
		
		JPanel Charpanel = new JPanel(); // 캐릭터 이미지가 나오는 Panel
		EquipmentItem.equipmentItem(equipmentName, Charpanel);
		Charpanel.setBounds(502, 211, 150, 200);
		contentPane.setLayout(null);
		
		
		JButton cutbtn = new JButton(); // 종료버튼
		cutbtn.setBackground(Color.BLACK);
		cutbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/종료버튼.png")));
		cutbtn.setBorderPainted(false);
		cutbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				cutbtn.setBorderPainted(false); // 외곽선없애기
//				cutbtn.setContentAreaFilled(false); // 영역채우기x
//				cutbtn.setFocusPainted(false); // 버튼선택시 테두리X
				System.exit(0);
			}
		});
		cutbtn.setLocation(758, 10);
		cutbtn.setSize(30, 30);
		
		contentPane.setLayout(null);
		contentPane.add(cutbtn);
		contentPane.add(Storebtn);
		contentPane.add(rankbtn);
		contentPane.add(invenbtn);
		contentPane.add(Charpanel);
		contentPane.add(Gamebtn);
	}
}