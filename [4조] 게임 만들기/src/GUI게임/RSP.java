package GUI게임;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import GUI.SelectgameWin;
import 객체모음.Student;
import 메소드모음.EquipmentItem;
import 메소드모음.InsertPoint;

public class RSP extends JFrame {

	ImageIcon[] gbb = { new ImageIcon(RSP.class.getResource("/이미지/gawi.png")),
			new ImageIcon(RSP.class.getResource("/이미지/bawi.png")),
			new ImageIcon(RSP.class.getResource("/이미지/bo.png")) };
	JButton[] btn = new JButton[gbb.length]; // 가위바위보 버튼 배열

	ImageIcon WIN = new ImageIcon(RSP.class.getResource("/이미지/WIN.png")); // 이겼을떄
	ImageIcon LOSE = new ImageIcon(RSP.class.getResource("/이미지/lose.png")); // 졌을때
	ImageIcon TIE = new ImageIcon(RSP.class.getResource("/이미지/Tie.png")); // 비겼을때
	ImageIcon ME = new ImageIcon(RSP.class.getResource("/이미지/나.png")); // 나
	ImageIcon COM = new ImageIcon(RSP.class.getResource("/이미지/콤.png")); // 컴퓨터

	JLabel me = new JLabel("");
	JLabel meIcon = new JLabel(ME);
	JLabel com = new JLabel("");
	JLabel comIcon = new JLabel(COM);
	JLabel win = new JLabel("");

	ImageIcon backGound = new ImageIcon(RSP.class.getResource("/이미지/DG.gif")); // 뒷배경
	ImageIcon Over = new ImageIcon(RSP.class.getResource("/이미지/Over.png")); // 게임 오버 이미지

	JLabel backGround = new JLabel(backGound);

	private int life = 5;
	private int winCount = 0;

	Student s;

	JLabel Life = new JLabel("남은 목숨 : " + life + "");
	JLabel WinCount = new JLabel("받아갈 포인트 : " + winCount + "");
	JLabel GameOver = new JLabel(Over);

	private String[] equi;

	public RSP(Student s, String[] equipmentName) {
		this.s = s;
		equi = equipmentName;
		setUndecorated(true); // 창 프레임 없애기

		JPanel ButtonPanel = new JPanel();
		JLabel Charlbl = new JLabel();

		this.setTitle("가위 바위 보 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLACK);

		JPanel DrawingPanel = new JPanel(); // 버튼 클릭시 가위바위보가 그려지는 판넬

		JPanel southPanel = new JPanel();
		southPanel.add(backGround);
		setBackground(Color.BLACK);
		southPanel.add(backGround, JLayeredPane.DEFAULT_LAYER);
		backGround.add(GameOver, JLayeredPane.POPUP_LAYER);
		GameOver.setBounds(400, 80, 300, 200);
		GameOver.setVisible(false);

		WinCount.setBounds(800, 40, 85, 15); // 이긴 횟수(받아갈 포인트)가 카운트 되는 라벨

		JPanel centerPanel = new JPanel();
		setBackground(Color.BLACK);

		WinCount.setBounds(800, 40, 85, 15);

		WinCount.setFont(new Font("굴림", Font.BOLD, 12));
		// southPanel.add(WinCount);

		Life.setBounds(0, 90, 85, 15); // 남은 목숨이 카운트 되는 라벨
		Life.setFont(new Font("굴림", Font.BOLD, 12));
		// centerPanel.add(Life);

		JButton back = new JButton(); // 뒤로 가기 버튼

		back.setBorderPainted(false);
		back.setBackground(Color.WHITE);
		back.setIcon(new ImageIcon(RSP.class.getResource("/이미지/뒤로가기버튼.png")));
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectgameWin sw = new SelectgameWin(s, equi);
				sw.setVisible(true);
				dispose();
			}
		});

		win.setForeground(Color.BLUE);

		DrawingPanel.add(meIcon);
		DrawingPanel.add(me);
		DrawingPanel.add(comIcon);
		DrawingPanel.add(com);
		DrawingPanel.add(win);

		for (int i = 0; i < gbb.length; i++) {
			btn[i] = new JButton(gbb[i]);
			btn[i].addActionListener(new MyActionListener());
			btn[i].setBorderPainted(false);
			btn[i].setContentAreaFilled(false);
			ButtonPanel.add(btn[i]);

		}

		DrawingPanel.setBounds(150, 20, 800, 150);
		ButtonPanel.setBounds(100, 450, 800, 150);
		backGround.setBounds(0, 0, 800, 600);

		ButtonPanel.setOpaque(false);
		DrawingPanel.setOpaque(false);

		EquipmentItem.equipmentCharacter(equipmentName, Charlbl);

		backGround.add(GameOver, JLayeredPane.POPUP_LAYER);
		GameOver.setBounds(350, 80, 300, 200);
		GameOver.setVisible(false); // 배경과 그 위에 게임오버 이미지

		backGround.add(back);
		back.setBounds(30, 520, 40, 40);

		backGround.add(Charlbl);
		Charlbl.setBounds(30, 30, 150, 200);

		this.setLayout(null);
		this.add(ButtonPanel, JLayeredPane.POPUP_LAYER);
		this.add(DrawingPanel, JLayeredPane.POPUP_LAYER);
		this.add(backGround, JLayeredPane.DEFAULT_LAYER);
		this.setSize(800, 600);
		setBackground(Color.BLACK);
		this.setVisible(true);
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게

	}

	public void draw(Icon m, Icon c, Icon w) {
		me.setIcon(m);
		com.setIcon(c);
		win.setIcon(w);
		Life.setText("남은 목숨 : " + life + "");
		WinCount.setText("받아갈 포인트 : " + winCount * 100 + "");

		revalidate();
		repaint();

	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (life > 0) {
				ImageIcon w;
				JButton b = (JButton) e.getSource();

				int n = (int) (Math.random() * 3);
				if (btn[0] == b) {
					if (n == 0) {
						w = WIN;
						draw(gbb[0], gbb[n], w);

					} else if (n == 1) {
						w = LOSE;
						life--;
						System.out.println("남은 목숨 :" + life);
						draw(gbb[0], gbb[n], w);
					} else if (n == 2) {
						w = WIN;

						winCount++;
						System.out.println("여기 작동됌?");
						draw(gbb[0], gbb[n], w);
					}
				} else if (btn[1] == b) {
					if (n == 0) {
						w = WIN;

						winCount++;

						draw(gbb[1], gbb[n], w);
					} else if (n == 1) {
						w = TIE;
						draw(gbb[1], gbb[n], w);
					} else if (n == 2) {
						w = LOSE;
						life--;
						System.out.println("남은 목숨 :" + life);
						draw(gbb[1], gbb[n], w);
					}
				} else if (btn[2] == b) {
					if (n == 0) {
						w = LOSE;
						life--;
						System.out.println("남은 목숨 :" + life);
						draw(gbb[2], gbb[n], w);
					} else if (n == 1) {
						w = WIN;

						winCount++;
						draw(gbb[2], gbb[n], w);
					} else if (n == 2) {
						w = TIE;
						draw(gbb[2], gbb[n], w);
					} else
						return;
				}
			}
			if (life == 0) {
				GameOver.setVisible(true);
				System.out.println("게임종료");
				int totalPoint = (winCount * 100);
//            남은목숨 출력만 만들기
				InsertPoint.insertGameLog(s, 4, totalPoint);
				InsertPoint.test(s, totalPoint);
				s.setPoint(s.getPoint() + totalPoint);
				life--;
			}

		}
	}

}