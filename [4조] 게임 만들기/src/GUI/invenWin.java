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
import 메소드모음.Inventory;
import 메소드모음.PickItem;

public class invenWin extends JFrame {

	private JPanel contentPane;
	Inventory inven;
	PickItem pickItem;
	private int cCount;
	private int wCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					invenWin frame = new invenWin(new Student("dd", 0));
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
	public invenWin(Student s) {
		inven = new Inventory(s);
		List<Boolean> cList = inven.ItemAcquisition("캐릭터");
		List<Boolean> wList = inven.ItemAcquisition("배경");

		List<Integer> cList2 = new PickItem(s, "캐릭터").random();
		List<Integer> wList2 = new PickItem(s, "배경").random();

		ImageIcon[] cIcons = new ImageIcon[cList2.size()];
		ImageIcon[] cIconsBlock = new ImageIcon[cList2.size()];
		ImageIcon[] wIcons = new ImageIcon[wList2.size()];
		ImageIcon[] wIconsBlock = new ImageIcon[cList2.size()];

		for (int i = 0; i < cList2.size(); i++) {
			cIcons[i] = new ImageIcon(invenWin.class.getResource("/이미지/캐릭터" + (i + 1) + ".gif"));
		}
		for (int i = 0; i < cList2.size(); i++) {
			cIconsBlock[i] = new ImageIcon(invenWin.class.getResource("/이미지/캐릭터" + (i + 1) + "잠금.png"));
		}

		for (int i = 0; i < wList2.size(); i++) {
			wIcons[i] = new ImageIcon(invenWin.class.getResource("/이미지/배경" + (i + 1) + ".png"));
		}
		for (int i = 0; i < wList2.size(); i++) {
			wIconsBlock[i] = new ImageIcon(invenWin.class.getResource("/이미지/배경" + (i + 1) + "잠금.png"));
		}

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
		Charpnl.setBounds(600, 231, 150, 200);

		JButton Restartbtn = new JButton("다시하기");
		Restartbtn.setBounds(600, 127, 150, 50);
		Restartbtn.setEnabled(false);

		JButton Backbtn = new JButton(); // 뒤로가기 버튼
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

		JButton cBtnLeft = new JButton("");
		cBtnLeft.setBounds(40, 307, 30, 50);
		contentPane.setLayout(null);
		contentPane.add(cBtnLeft);
		contentPane.add(Restartbtn);
		contentPane.add(Coinlbl);
		contentPane.add(Backbtn);
		contentPane.add(Charpnl);

		JLabel cLbl = new JLabel("");
		cLbl.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/캐릭터1.gif")));
		cCount = 0;
		cLbl.setBounds(77, 231, 158, 200);
		contentPane.add(cLbl);

		JButton cBtnRight = new JButton("");
		cBtnLeft.setEnabled(false);
		cBtnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cCount < cList2.size())
					cCount += 1;
				if (cList.get(cCount)) {
					cLbl.setIcon(cIcons[cCount]);
				} else {
					cLbl.setIcon(cIconsBlock[cCount]);
				}
				if (cCount == cList2.size() - 1)
					cBtnRight.setEnabled(false);
				cBtnLeft.setEnabled(true);
				revalidate();
				repaint();
			}
		});
		cBtnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cCount > 0)
					cCount -= 1;
				if (cList.get(cCount)) {
					cLbl.setIcon(cIcons[cCount]);
				} else {
					cLbl.setIcon(cIconsBlock[cCount]);
				}
				if (cCount == 0)
					cBtnLeft.setEnabled(false);
				cBtnRight.setEnabled(true);
				revalidate();
				repaint();
			}
		});
		cBtnRight.setBounds(246, 307, 30, 50);
		contentPane.add(cBtnRight);

		JLabel wLbl = new JLabel("");
		wLbl.setIcon(new ImageIcon(invenWin.class.getResource("/이미지/배경1.png")));
		wLbl.setBounds(360, 232, 158, 200);
		contentPane.add(wLbl);

		wCount = 0;

		JButton wBtnLeft = new JButton("");
		JButton wBtnRight = new JButton("");
		wBtnLeft.setEnabled(false);
		wBtnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (wCount > 0)
					wCount -= 1;
				if (wList.get(cCount)) {
					wLbl.setIcon(wIcons[cCount]);
				} else {
					wLbl.setIcon(wIconsBlock[cCount]);
				}
				if (wCount == 0)
					wBtnLeft.setEnabled(false);
				wBtnRight.setEnabled(true);
				revalidate();
				repaint();
			}
		});
		wBtnLeft.setBounds(312, 307, 30, 50);
		contentPane.add(wBtnLeft);

		wBtnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (wCount < wList2.size())
					wCount += 1;
				if (wList.get(cCount)) {
					wLbl.setIcon(wIcons[cCount]);
				} else {
					wLbl.setIcon(wIconsBlock[cCount]);
				}
				if (wCount == wList2.size() - 1)
					wBtnRight.setEnabled(false);
				wBtnLeft.setEnabled(true);
				revalidate();
				repaint();
			}
		});
		wBtnRight.setBounds(530, 307, 30, 50);
		contentPane.add(wBtnRight);

	}
}
