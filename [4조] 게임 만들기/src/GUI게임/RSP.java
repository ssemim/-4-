package GUI게임;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import GUI.JoinWin;

class RSPGO extends JFrame {
	ImageIcon[] gbb = { new ImageIcon("/이미지/gawi.png"), new ImageIcon("/이미지/bawi.png"), new ImageIcon("/이미지/bo.png") };
	JButton[] btn = new JButton[gbb.length];

	JLabel me = new JLabel("  me  ");
	JLabel com = new JLabel("  com  ");
	JLabel win = new JLabel("win");
	ImageIcon backGound = new ImageIcon("/이미지/DG.gif");
	JLabel backGround = new JLabel(backGound);

	JLabel GameOver = new JLabel("게임 오버");

	private int winCount = 0;
	boolean keepPlaying = true;

	JLabel WinCount = new JLabel("받아갈 포인트 : " + winCount + "");

	RSPGO() {

		this.setTitle("가위 바위 보 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel southPanel = new JPanel();
		southPanel.add(backGround);

		JPanel centerPanel = new JPanel();

		WinCount.setBounds(800, 40, 85, 15);
		WinCount.setFont(new Font("굴림", Font.BOLD, 12));
		southPanel.add(WinCount);

		JButton back = new JButton("그만하기");

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

		JLabel GameOver = new JLabel("게임 오버");
		GameOver.setBounds(206, 186, 378, 165);
		centerPanel.add(GameOver);
		GameOver.setVisible(false);

		JButton GoMain = new JButton("메인으로 돌아가기");
		GoMain.setBackground(Color.BLACK);
		GoMain.setBounds(352, 305, 97, 23);
		GoMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		GameOver.setBounds(206, 186, 378, 165);
		centerPanel.add(GameOver);
		GameOver.setVisible(false);

		GameOver.add(GoMain);

	}

	public void draw(Icon m, Icon c, String w) {
		me.setIcon(m);
		com.setIcon(c);
		win.setText(w);

		WinCount.setText("받아갈 포인트 : " + winCount * 10 + "");
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String w;
			JButton b = (JButton) e.getSource();

			int n = (int) (Math.random() * 3);
			if (btn[0] == b) {
				if (n == 0) {
					w = "  비김";
					draw(gbb[0], gbb[n], w);

				} else if (n == 1) {
					w = "  컴이 이김";

					System.out.println("남은 목숨 :");
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

					System.out.println("남은 목숨 :");
					draw(gbb[1], gbb[n], w);
				}
			} else if (btn[2] == b) {
				if (n == 0) {
					w = "  컴이 이김";

					System.out.println("남은 목숨 :");
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
	}
}

public class RSP {
	public static void main(String[] args) {

		RSPGO rspgo = new RSPGO();
		rspgo.getContentPane().setLayout(null);

	}
}