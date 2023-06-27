package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import 객체모음.Student;
import 메소드모음.EquipmentItem;
import 메소드모음.Inventory;
import 메소드모음.PickItem;
import java.awt.Color;

public class invenWin extends JFrame {

	private JPanel contentPane;
	Inventory inven;
	PickItem pickItem;
	private int cCount;
	private int wCount;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					invenWin frame = new invenWin(new Student("dd", 0));
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
	public invenWin(Student s, String[] equipmentName) {
		inven = new Inventory(s);
		List<Boolean> cList = inven.ItemAcquisition("캐릭터");
		List<Boolean> wList = inven.ItemAcquisition("배경");

		List<Integer> cList2 = new PickItem(s, "캐릭터").random();
		List<Integer> wList2 = new PickItem(s, "배경").random();

		ImageIcon[] cIcons = new ImageIcon[cList2.size()];
		ImageIcon[] cIconsBlock = new ImageIcon[cList2.size()];
		ImageIcon[] wIcons = new ImageIcon[wList2.size()];
		ImageIcon[] wIconsBlock = new ImageIcon[cList2.size()];

		inven.imageSet(cList, cList2, wList, wList2, cIcons, cIconsBlock, wIcons, wIconsBlock);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setUndecorated(true); // 창 프레임 없애기
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 공룡게임 프레임 설정
		setTitle("인벤토리"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);

		JPanel Charpnl = new JPanel();
		EquipmentItem.equipmentItem(equipmentName, Charpnl);
		Charpnl.setBounds(600, 231, 150, 200);

		JButton Restartbtn = new JButton("다시하기");
		Restartbtn.setBackground(Color.DARK_GRAY);
		Restartbtn.setBounds(600, 127, 150, 50);
		Restartbtn.setEnabled(false);

		JButton Backbtn = new JButton();
		Backbtn.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.setBackground(Color.BLACK);
		Backbtn.setBounds(701, 185, 40, 40);
		// 뒤로가기 버튼을 누르면 MainWin으로 돌아가는 버튼
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
			}
		});

		JLabel Coinlbl = new JLabel("1.000");
		Coinlbl.setBounds(630, 202, 60, 15);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(Restartbtn);
		contentPane.add(Coinlbl);
		contentPane.add(Backbtn);
		contentPane.add(Charpnl);

		JLabel cLbl = new JLabel("");
		cLbl.setBounds(77, 231, 158, 200);
		cLbl.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/캐릭터1.gif")));
		cCount = 0;
		contentPane.add(cLbl);

		JButton cBtnRight = new JButton("");
		cBtnRight.setBackground(Color.BLACK);
		cBtnRight.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/오른쪽버튼.png")));
		cBtnRight.setBounds(247, 307, 30, 50);
		cBtnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCount = inven.cBtnRight(cCount, cLbl, cBtnRight, cBtnLeft, cList, cList2, cIcons, cIconsBlock);
				revalidate();
				repaint();

			}
		});
		contentPane.add(cBtnRight);

		JLabel wLbl = new JLabel("");
		wLbl.setBounds(360, 231, 158, 200);
		wLbl.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/배경1.png")));
		contentPane.add(wLbl);

		wCount = 0;

		JButton wBtnLeft = new JButton("");
		wBtnLeft.setBackground(Color.BLACK);
		wBtnLeft.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/왼쪽버튼.png")));
		wBtnLeft.setBounds(318, 307, 30, 50);
		JButton wBtnRight = new JButton("");
		wBtnRight.setBackground(Color.BLACK);
		wBtnRight.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/오른쪽버튼.png")));
		wBtnRight.setBounds(530, 307, 30, 50);
		wBtnLeft.setEnabled(false);
		wBtnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wCount = inven.wBtnLeft(wCount, wLbl, wBtnRight, wBtnLeft, wList, wList2, wIcons, wIconsBlock);
				revalidate();
				repaint();
			}
		});
		contentPane.add(wBtnLeft);

		wBtnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wCount = inven.wBtnRight(wCount, wLbl, wBtnRight, wBtnLeft, wList, wList2, wIcons, wIconsBlock);
				revalidate();
				repaint();
			}
		});
		contentPane.add(wBtnRight);
		
		JButton cutbtn = new JButton(""); // 종료버튼
		cutbtn.setBackground(Color.BLACK);
		cutbtn.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/종료버튼.png")));
		cutbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cutbtn.setBorderPainted(false); // 외곽선없애기
				cutbtn.setContentAreaFilled(false); // 영역채우기x
				cutbtn.setFocusPainted(false); // 버튼선택시 테두리X
				System.exit(0);
			}
		});
		cutbtn.setBounds(758, 10, 30, 30);
		contentPane.add(cutbtn);
		
		JButton cBtnLeft = new JButton("");
		cBtnLeft.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/왼쪽버튼.png")));
		cBtnLeft.setEnabled(false);
		cBtnLeft.setBackground(Color.BLACK);
		cBtnLeft.setBounds(35, 307, 30, 50);
		contentPane.add(cBtnLeft);

	}
}