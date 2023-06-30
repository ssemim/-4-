package GUI게임;

import java.awt.BorderLayout;
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
import 메소드모음.InsertPoint;

public class RSP extends JFrame {

	ImageIcon[] gbb = { new ImageIcon(RSP.class.getResource("/이미지/gawi.png")),
			new ImageIcon(RSP.class.getResource("/이미지/bawi.png")),
			new ImageIcon(RSP.class.getResource("/이미지/bo.png")) };
	JButton[] btn = new JButton[gbb.length];

	JLabel me = new JLabel("  me  ");
	JLabel com = new JLabel("  com  ");
	JLabel win = new JLabel("win");
	ImageIcon backGound = new ImageIcon(RSP.class.getResource("/이미지/DG.gif"));
	ImageIcon Over = new ImageIcon(RSP.class.getResource("/이미지/Over.png"));
	JLabel backGround = new JLabel(backGound);
	private int life = 5;
	Student s;
	private int winCount = 0;

	JLabel Life = new JLabel("남은 목숨 : " + life + "");
	JLabel WinCount = new JLabel("받아갈 포인트 : " + winCount + "");
	JLabel GameOver = new JLabel(Over);
	
	
	private String[] equi;

	public RSP(Student s,String[] equipmentName) {
		this.s = s;
		equi = equipmentName;
		setUndecorated(true); // 창 프레임 없애기
		
		this.setTitle("가위 바위 보 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel southPanel = new JPanel();
		southPanel.add(backGround,JLayeredPane.DEFAULT_LAYER);
		backGround.add(GameOver,JLayeredPane.POPUP_LAYER);
		GameOver.setBounds(400, 80, 300, 200);
		GameOver.setVisible(false);

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
		@Override
		public void actionPerformed(ActionEvent e) {
			if (life > 0) {
				String w;
				JButton b = (JButton) e.getSource();

				int n = (int) (Math.random() * 3);
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
			if (life == 0) {
				GameOver.setVisible(true);
				System.out.println("게임종료");
				int totalPoint = (winCount * 100);
//				남은목숨 출력만 만들기
				InsertPoint.insertGameLog(s, 4, totalPoint);
				InsertPoint.test(s, totalPoint);
				s.setPoint(s.getPoint() + totalPoint);
				life--;
			}

		}
	}
	

}