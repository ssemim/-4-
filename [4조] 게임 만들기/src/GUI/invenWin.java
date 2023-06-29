package GUI;

import java.awt.Color;
import java.awt.Font;
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
import 유틸.Music;
import 유틸.SoundButton;
import 유틸.Util;

public class invenWin extends JFrame {

	private JPanel contentPane;
	Inventory inven;
	PickItem pickItem;
	private int cCount;
	private int wCount;
	private int[] iarr;

//   /**
//    * Launch the application.
//    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               invenWin frame = new invenWin(new Student("dd", 0));
//               frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

	public invenWin(Student s, String[] equipmentName) {
		inven = new Inventory(s);
		EquipmentItem e = new EquipmentItem();
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
		iarr = EquipmentItem.equipmentItem(equipmentName, Charpnl);
		Charpnl.setBounds(600, 231, 150, 200);

		JButton Backbtn = new JButton(); 
		Backbtn.setBorderPainted(false); // 버튼 테두리 제거
		Backbtn.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.setBackground(Color.BLACK);
		Backbtn.setBounds(748, 550, 40, 40);
		// 뒤로가기 버튼을 누르면 MainWin으로 돌아가는 버튼
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
			}
		});

		JLabel Coinlbl = new JLabel("1.000");
		Coinlbl.setFont(new Font("굴림", Font.BOLD, 18));
		Coinlbl.setForeground(Color.WHITE);
		Coinlbl.setBounds(630, 202, 120, 20);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(Coinlbl);
		contentPane.add(Backbtn);
		contentPane.add(Charpnl);
		Coinlbl.setText("" + s.getPoint());

		JLabel cLbl = new JLabel("");
		cLbl.setBounds(77, 231, 158, 200);
		cLbl.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/캐릭터1.gif")));
		contentPane.add(cLbl);

		SoundButton cBtnLeft = new SoundButton(Music.S1);
		cBtnLeft.setBackground(Color.BLACK);
		cBtnLeft.setBorderPainted(false); // 버튼 테두리 제거
		cBtnLeft.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/왼쪽버튼.png")));
		cBtnLeft.setEnabled(false);
		cBtnLeft.setBounds(35, 307, 30, 50);
		contentPane.add(cBtnLeft);

		SoundButton cBtnRight = new SoundButton(Music.S1);
		cBtnRight.setBackground(Color.BLACK);
		cBtnRight.setBorderPainted(false); // 버튼 테두리 제거
		cBtnRight.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/오른쪽버튼.png")));
		cBtnRight.setBounds(247, 307, 30, 50);
		contentPane.add(cBtnRight);

		JLabel wLbl = new JLabel("");
		wLbl.setBounds(360, 231, 158, 200);
		wLbl.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/배경1.gif")));
		contentPane.add(wLbl);

		SoundButton wBtnLeft = new SoundButton(Music.S1);
		wBtnLeft.setBackground(Color.BLACK);
		wBtnLeft.setBorderPainted(false); // 버튼 테두리 제거
		wBtnLeft.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/왼쪽버튼.png")));
		wBtnLeft.setBounds(318, 307, 30, 50);
		wBtnLeft.setEnabled(false);
		contentPane.add(wBtnLeft);

		SoundButton wBtnRight = new SoundButton(Music.S1);
		wBtnRight.setBackground(Color.BLACK);
		wBtnRight.setBorderPainted(false); // 버튼 테두리 제거
		wBtnRight.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/오른쪽버튼.png")));
		wBtnRight.setBounds(530, 307, 30, 50);
		contentPane.add(wBtnRight);

		SoundButton changeCbtn = new SoundButton(Music.S1);
		changeCbtn.setBorderPainted(false); // 버튼 테두리 제거
		changeCbtn.setContentAreaFilled(false); // 버튼 안 투명화
		changeCbtn.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/변경버튼.png")));
		changeCbtn.setBounds(112, 441, 100, 50);
		contentPane.add(changeCbtn);

		SoundButton changeWbtn = new SoundButton(Music.S1);
		changeWbtn.setBorderPainted(false); // 버튼 테두리 제거
		changeWbtn.setContentAreaFilled(false); // 버튼 안 투명화
		changeWbtn.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/변경버튼.png")));
		changeWbtn.setBounds(384, 441, 100, 50);
		contentPane.add(changeWbtn);

		JLabel CoinLbl = new JLabel("");
		CoinLbl.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/코인이미지.png")));
		CoinLbl.setBounds(600, 202, 20, 20);
		contentPane.add(CoinLbl);

		JLabel invenTitLbl = new JLabel("");
		invenTitLbl.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/인벤타이틀.png")));
		invenTitLbl.setBounds(0, 0, 350, 100);
		contentPane.add(invenTitLbl);

		JLabel invenBackLbl = new JLabel("");
		invenBackLbl.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/인벤배경.gif")));
		invenBackLbl.setBounds(0, 0, 800, 600);
		contentPane.add(invenBackLbl);

		JButton cutbtn = new JButton(""); // 종료버튼
		cutbtn.setBackground(Color.BLACK);
		cutbtn.setBorderPainted(false); // 버튼 테두리 제거
		cutbtn.setFocusable(false); // 종료버튼 포커스 제거
		cutbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/종료버튼.png")));
		cutbtn.setBounds(758, 10, 30, 30);
		contentPane.add(cutbtn);

		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
			}
		});

		cutbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		cCount = 0;
		wCount = 0;

		changeCbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iarr = e.changeC(Charpnl, cCount, iarr, s);
				revalidate();
				repaint();
			}
		});

		changeWbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iarr = e.changeW(Charpnl, wCount, iarr, s);
				revalidate();
				repaint();
			}
		});

		cBtnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCount = inven.cBtnLeft(cCount, cLbl, cBtnRight, cBtnLeft, cList, cList2, cIcons, cIconsBlock,
						changeCbtn);
				revalidate();
				repaint();
			}
		});
		cBtnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCount = inven.cBtnRight(cCount, cLbl, cBtnRight, cBtnLeft, cList, cList2, cIcons, cIconsBlock,
						changeCbtn);
				revalidate();
				repaint();

			}
		});
		wBtnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wCount = inven.wBtnLeft(wCount, wLbl, wBtnRight, wBtnLeft, wList, wList2, wIcons, wIconsBlock,
						changeWbtn);
				revalidate();
				repaint();
			}
		});

		wBtnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wCount = inven.wBtnRight(wCount, wLbl, wBtnRight, wBtnLeft, wList, wList2, wIcons, wIconsBlock,
						changeWbtn);
				revalidate();
				repaint();
			}
		});
		
		Util.removeAllButtonFocus(contentPane);

	}
}