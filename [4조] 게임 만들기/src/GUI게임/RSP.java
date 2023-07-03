package GUI게임;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;

import GUI.SelectgameWin;
import dbutil.DBUtil;
import 객체모음.RspData;
import 객체모음.Student;
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

	JLabel me = new JLabel("  me  ");
	JLabel com = new JLabel("  com  ");
	JLabel win = new JLabel("win");
	ImageIcon backGound = new ImageIcon(RSP.class.getResource("/이미지/DG.gif"));
	JLabel backGround = new JLabel(backGound);
	private int life = 5;
	Student s;
	private int winCount = 0;

	JLabel Life = new JLabel("남은 목숨 : " + life + "");
	JLabel WinCount = new JLabel("받아갈 포인트 : " + winCount + "");
	private String[] equi;
	private RspGG rg;

	public RSP(Student s,String[] equipmentName) {
		list = new ArrayList<>();
		rg = new RspGG();
		this.s = s;
		equi = equipmentName;
		setUndecorated(true); // 창 프레임 없애기
		
		this.setTitle("가위 바위 보 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel southPanel = new JPanel();
		southPanel.add(backGround);

		JPanel centerPanel = new JPanel();

		WinCount.setBounds(800, 40, 85, 15);
		WinCount.setFont(new Font("굴림", Font.BOLD, 12));
		southPanel.add(WinCount);

		Life.setBounds(0, 90, 85, 15);
		Life.setFont(new Font("굴림", Font.BOLD, 12));
		centerPanel.add(Life);

		JButton back = new JButton("그만하기");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectgameWin sw = new SelectgameWin(s, equi);
				sw.setVisible(true);
				dispose();
			}
		});

		win.setForeground(Color.BLUE);
		centerPanel.add(me);
		centerPanel.add(com);
		centerPanel.add(win);

		for (int i = 0; i < gbb.length; i++) {
			btn[i] = new JButton(gbb[i]);
			btn[i].addActionListener(new MyActionListener());
			btn[i].setBorderPainted(false);
			btn[i].setContentAreaFilled(false);
			southPanel.add(btn[i]);

		}

		this.add(southPanel, BorderLayout.SOUTH);
		this.add(centerPanel, null);
		centerPanel.setBounds(0, 0, 800, 200);
		centerPanel.add(backGround);
		southPanel.add(back);
		this.setSize(800, 600);
		this.setVisible(true);
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게

	}

	public void draw(Icon m, Icon c, String w) {
		me.setIcon(m);
		com.setIcon(c);
		win.setText(w);
		Life.setText("남은 목숨 : " + life + "");
		WinCount.setText("받아갈 포인트 : " + winCount * 100 + "");

	}

	class MyActionListener implements ActionListener {
		private int n;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (life > 0) {
				String w;
				JButton b = (JButton) e.getSource();

				n = (int) (Math.random() * 3);
				if (btn[0] == b) {
					if (n == 0) {
						w = "  비김";
						draw(gbb[0], gbb[n], w);

					} else if (n == 1) {
						w = "  컴이 이김";
						life--;
						System.out.println("남은 목숨 :" + life);
						draw(gbb[0], gbb[n], w);
					} else if (n == 2) {
						w = "  내가 이김";

						winCount++;
						System.out.println("여기 작동됌?");
						draw(gbb[0], gbb[n], w);
					}
				} else if (btn[1] == b) {
					if (n == 0) {
						w = "  내가 이김";

						winCount++;

						draw(gbb[1], gbb[n], w);
					} else if (n == 1) {
						w = "  비김";
						draw(gbb[1], gbb[n], w);
					} else if (n == 2) {
						w = "  컴이 이김";
						life--;
						System.out.println("남은 목숨 :" + life);
						draw(gbb[1], gbb[n], w);
					}
				} else if (btn[2] == b) {
					if (n == 0) {
						w = "  컴이 이김";
						life--;
						System.out.println("남은 목숨 :" + life);
						draw(gbb[2], gbb[n], w);
					} else if (n == 1) {
						w = "  내가 이김";

						winCount++;
						draw(gbb[2], gbb[n], w);
					} else if (n == 2) {
						w = "  비김";
						draw(gbb[2], gbb[n], w);
					} else
						return;
				}
			}
			if (e.getSource() == btn[0]) {
				myChoice = "가위";
			}
			if (e.getSource() == btn[1]) {
				myChoice = "바위";
			}
			if (e.getSource() == btn[2]) {
				myChoice = "보";
			}
			if (n == 0) {
				comChoice = "가위";
			}
			if (n == 1) {
				comChoice = "바위";
			}
			if (n == 2) {
				comChoice = "보";
			}
			playTime ++;
			System.out.println(comChoice);
			list.add(new RspData(playLog, playTime, s, myChoice, comChoice));
			if (life == 0) {
				System.out.println("게임종료");
				int totalPoint = (winCount * 100);
//				남은목숨 출력만 만들기
				InsertPoint.insertGameLog(s, 4, totalPoint);
				InsertPoint.test(s, totalPoint);
				s.setPoint(s.getPoint() + totalPoint);
				life--;
				for (JButton jButton : btn) {
					jButton.setEnabled(false);
				}
				
				
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