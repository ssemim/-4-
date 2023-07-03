package GUI게임;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import GUI.SelectgameWin;
import dbutil.DBUtil;
import 객체모음.RspData;
import 객체모음.Student;
import 메소드모음.EquipmentItem;
import 메소드모음.InsertPoint;
import 메소드모음.RspGG;

public class RSP extends JFrame {
	private int playLog;
	private int playTime = 0;
	private String myChoice;
	private String comChoice;
	private List<RspData> list;

	ImageIcon[] gbb = { new ImageIcon(RSP.class.getResource("/이미지/gawi.png")),
			new ImageIcon(RSP.class.getResource("/이미지/bawi.png")),
			new ImageIcon(RSP.class.getResource("/이미지/bo.png")) };
	JButton[] btn = new JButton[gbb.length];

	ImageIcon WIN = new ImageIcon(RSP.class.getResource("/이미지/WIN.png")); // 이겼을떄
	ImageIcon LOSE = new ImageIcon(RSP.class.getResource("/이미지/lose.png")); // 졌을때
	ImageIcon TIE = new ImageIcon(RSP.class.getResource("/이미지/Tie.png")); // 비겼을때
	ImageIcon ME = new ImageIcon(RSP.class.getResource("/이미지/나.png")); // 나
	ImageIcon COM = new ImageIcon(RSP.class.getResource("/이미지/콤.png")); // 컴퓨터

	ImageIcon HEART = new ImageIcon(RSP.class.getResource("/이미지/heart.png")); // 컴퓨터
	ImageIcon POINT = new ImageIcon(RSP.class.getResource("/이미지/RSPPoint.png")); // 포인트

	JLabel[] heart = new JLabel[5];

	JLabel me = new JLabel("");
	JLabel meIcon = new JLabel(ME);
	JLabel com = new JLabel("");
	JLabel comIcon = new JLabel(COM);
	JLabel win = new JLabel("");

	ImageIcon backGound = new ImageIcon(RSP.class.getResource("/이미지/DG.gif")); // 뒷배경
	ImageIcon Over = new ImageIcon(RSP.class.getResource("/이미지/Over.png")); // 게임 오버 이미지

	JLabel backGround = new JLabel(backGound);
	JLabel HowMuch = new JLabel(POINT);

	private int life = 5;
	private int winCount = 0;
//	private int totalPoint = 0;

	Student s;

	JLabel WinCount = new JLabel("" + winCount + "");

	JLabel Life = new JLabel("남은 목숨 : " + life + "");

	JLabel GameOver = new JLabel(Over);

	private String[] equi;
	private RspGG rg;

	public RSP(Student s, String[] equipmentName) {
		list = new ArrayList<>();
		rg = new RspGG();
		this.s = s;
		equi = equipmentName;
		setUndecorated(true); // 창 프레임 없애기

		JPanel ButtonPanel = new JPanel();
		JLabel Charlbl = new JLabel();

		this.setTitle("가위 바위 보 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLACK);

		JPanel DrawingPanel = new JPanel(); // 버튼 클릭시 가위바위보가 그려지는 판넬

		backGround.add(GameOver, JLayeredPane.POPUP_LAYER);
		GameOver.setBounds(400, 80, 300, 200);
		GameOver.setVisible(false);

		JButton back = new JButton(); // 뒤로 가기 버튼
		back.setBorderPainted(false); // 버튼 테두리 없애기
		back.setContentAreaFilled(false); // 버튼 내부를 투명하게
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

		for (int i = 0; i < 5; i++) { // 하트
			heart[i] = new JLabel();
			heart[i].setIcon(HEART);
			backGround.add(heart[i]);
			heart[i].setBounds(10, 40 + (30 * i), 30, 50);

		}

		DrawingPanel.setBounds(150, 20, 800, 150);
		ButtonPanel.setBounds(100, 450, 800, 150);
		backGround.setBounds(0, 0, 800, 600);

		ButtonPanel.setOpaque(false);
		DrawingPanel.setOpaque(false);

		EquipmentItem.equipmentCharacter(equipmentName, Charlbl);

		backGround.add(GameOver, JLayeredPane.POPUP_LAYER);
		GameOver.setBounds(350, 90, 300, 200);
		GameOver.setVisible(false); // 배경과 그 위에 게임오버 이미지

		backGround.add(back);
		back.setBounds(30, 520, 40, 40);

		backGround.add(Charlbl);
		Charlbl.setBounds(30, 30, 150, 200);

		backGround.add(HowMuch);
		HowMuch.setBounds(350, 180, 300, 200);
		HowMuch.setVisible(false);

		backGround.add(WinCount);
		WinCount.setBounds(200, 180, 300, 200);
		WinCount.setFont(new Font("굴림", Font.BOLD, 36));
		WinCount.setVisible(false);

		this.setLayout(null);
		this.add(ButtonPanel, JLayeredPane.POPUP_LAYER);
		this.add(DrawingPanel, JLayeredPane.POPUP_LAYER);
		this.add(backGround, JLayeredPane.DEFAULT_LAYER);
		this.setSize(800, 600);
		this.setVisible(true);
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게

	}

	public void draw(Icon m, Icon c, Icon w) {
		me.setIcon(m);
		com.setIcon(c);
		win.setIcon(w);

		revalidate();
		repaint();
	}

	class MyActionListener implements ActionListener {
		private int n;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (life > 0) {
				ImageIcon w;
				JButton b = (JButton) e.getSource();

				n = (int) (Math.random() * 3);
				System.out.println(n);
				if (n == 0) {
					comChoice = "가위";
				} else if (n == 1) {
					comChoice = "바위";
				} else if (n == 2) {
					comChoice = "보";
				}
				if (btn[0] == b) {
					myChoice = "가위";
					if (n == 0) {
						w = TIE;
						draw(gbb[0], gbb[n], w);

					} else if (n == 1) {
						w = LOSE;
						life--;
						System.out.println("남은 목숨 :" + life);
						draw(gbb[0], gbb[n], w);
					} else if (n == 2) {
						w = WIN;

						winCount++;

						draw(gbb[0], gbb[n], w);
					}
				} else if (btn[1] == b) {
					myChoice = "바위";
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
					myChoice = "보";
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
			if (life == 4) {
				heart[4].setVisible(false);
			} else if (life == 3) {
				heart[3].setVisible(false);
			} else if (life == 2) {
				heart[2].setVisible(false);
			} else if (life == 1) {
				heart[1].setVisible(false);
			}
			playTime++;
			list.add(new RspData(playLog, playTime, s, myChoice, comChoice));
			System.out.printf("playLog %d, playTime %d, s %s, myChoice %s, comChoice %s\n", playLog, playTime,
					s.getId(), myChoice, comChoice);
			if (life == 0) {
				heart[0].setVisible(false);
				HowMuch.setVisible(true);
				WinCount.setVisible(true);
				GameOver.setVisible(true);
				System.out.println("게임종료");

				int totalPoint = (winCount * 100);
//				남은목숨 출력만 만들기

				InsertPoint.insertGameLog(s, 4, totalPoint);
				InsertPoint.test(s, totalPoint);
				s.setPoint(s.getPoint() + totalPoint);
				life--;
				btn[0].setEnabled(false);
				btn[1].setEnabled(false);
				btn[2].setEnabled(false);

				Connection conn = null;
				try {
					conn = DBUtil.getConnection();
					playLog = rg.selectPlayLog(conn);
					for (RspData rd : list) {
						rd.setPlayLog(playLog);
						rg.insert(rd, conn);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					DBUtil.close(conn);
				}

			}
		}
	}

}