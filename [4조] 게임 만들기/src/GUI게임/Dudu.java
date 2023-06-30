package GUI게임;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.SelectgameWin;
import 객체모음.Student;
import 메소드모음.InsertPoint;

public class Dudu extends JFrame implements ActionListener {

	private JButton jbt[] = new JButton[12];
	private JButton start = new JButton("시작");
	private JButton end = new JButton("종료");
	private JLabel jlb = new JLabel("점수 : 0");
	private JLabel time_jlb = new JLabel("Time - 0:10");
	private BorderLayout bl = new BorderLayout(10, 10);
	private JPanel jp1 = new JPanel();
	private GridLayout gl1 = new GridLayout(3, 4);
	private JPanel jp2 = new JPanel();
	private GridLayout gl2 = new GridLayout(1, 2);
	private JPanel jp21 = new JPanel();
	private FlowLayout fl21 = new FlowLayout(FlowLayout.RIGHT);
	private int randomsu = 0;
	private int count = -1;
	private Student s;
	private String[] equi;
	private int time = 0;
	private int countAll = 0;

	public Dudu(Student s, String[] equi) {
		this.s = s;
		this.equi = equi;
		this.init();
		this.start();
		super.setSize(500, 500);
		setUndecorated(true);
		getContentPane().setBackground(Color.BLACK);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true);
	}

	public void init() {
		Container con = this.getContentPane();
		con.setLayout(bl);
		time_jlb.setHorizontalAlignment(SwingConstants.LEFT);
		time_jlb.setFont(new Font("굴림", Font.BOLD, 12));
		con.add("North", time_jlb);
		con.add("Center", jp1);
		jp1.setLayout(gl1);
		for (int i = 0; i < 12; ++i) {
			jbt[i] = new JButton();
			jbt[i].setIcon(new ImageIcon(Dudu.class.getResource("/이미지/d.png")));
			jbt[i].setBorderPainted(false);
			jbt[i].setContentAreaFilled(false);
			jp1.add(jbt[i]);
		}
		off_button();
		jp2.setBackground(Color.BLACK);
		con.add("South", jp2);
		jp2.setLayout(gl2);
		jlb.setForeground(Color.WHITE);
		jlb.setBackground(Color.BLACK);
		jlb.setHorizontalAlignment(SwingConstants.CENTER);
		jlb.setFont(new Font("굴림", Font.BOLD, 12));
		jp2.add(jlb);
		jp21.setBackground(Color.BLACK);
		jp2.add(jp21);
		jp21.setLayout(fl21);
		jp21.add(start);
		jp21.add(end);
		JButton Backbtn = new JButton();
		Backbtn.setBackground(Color.BLACK);
		Backbtn.setBorderPainted(false);
		Backbtn.setIcon(new ImageIcon(Dudu.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(countAll);
				SelectgameWin SW = new SelectgameWin(s, equi);
				SW.setVisible(true);
				dispose();
			}
		});
		jp21.add(Backbtn);
	}

	public void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.addActionListener(this);
		end.addActionListener(this);
		for (int i = 0; i < 12; ++i) {
			jbt[i].addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		start.setEnabled(false);
		if (e.getSource() == start) {
			time_jlb.setText("시간 => 0:10");
			if (!(count == -1)) {
				jlb.setText("점수 : 0");
				InsertPoint.test(s, count * 30);
				int point = s.getPoint();
				s.setPoint(point + count * 30);
			}
			count = -1;
			on_button();
			random(0);
			new Timer().schedule(new java.util.TimerTask() {
				int time = 10;

				@Override
				public void run() {
					time--;
					if (time == 0) {
						System.out.println(count);
						time_jlb.setText("게임이 끝났습니다.");
						for (int i = 0; i < 12; ++i) {
							jbt[i].setIcon(new ImageIcon(Dudu.class.getResource("/이미지/d.png")));
							jbt[i].setEnabled(false);
						}
						randomsu = 0;
						InsertPoint.test(s, count * 30);
						InsertPoint.insertGameLog(s, 3, count * 30);
						int point = s.getPoint();
						s.setPoint(point + count * 30);
					}
					time_jlb.setText("시간 => 0:0" + time);
				}
			}, 1000, 1000);
		} else if (e.getSource() == end) {
			InsertPoint.test(s, count * 30);
			InsertPoint.insertGameLog(s, 3, count * 30);
			int point = s.getPoint();
			s.setPoint(point + count * 30);
			SelectgameWin s = new SelectgameWin(this.s, equi);
			s.setVisible(true);
			this.setVisible(false);
		}
		for (int i = 0; i < 12; ++i) {
			if (e.getSource() == jbt[i]) {
				jbt[i].setIcon(new ImageIcon(Dudu.class.getResource("/이미지/d.png")));
				random(i);
				countAll++;
			}
		}
	}

	public void off_button() {
		for (int i = 0; i < 12; ++i) {
			jbt[i].setEnabled(false);
		}
	}

	public void on_button() {
		for (int i = 0; i < 12; ++i) {
			jbt[i].setEnabled(true);
		}
	}

	public void random(int i) {
		if (i != randomsu)
			return;
		count++;
		randomsu = (int) (Math.random() * 12);
		jbt[randomsu].setIcon(new ImageIcon(Dudu.class.getResource("/이미지/c.png")));
		jlb.setText("점수 : " + count * 30);
	}
}
