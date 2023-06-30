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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.javafx.collections.MappingChange.Map;

import GUI.MainWin;
import GUI.SelectgameWin;
import 객체모음.Student;
import 메소드모음.DuduLog;
import 메소드모음.InsertPoint;

public class Dudu extends JFrame implements ActionListener, Runnable {

	private JButton jbt[] = new JButton[12];

	private JButton start = new JButton("START");

	private JButton end = new JButton("END");

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
	
	public static List<Integer> list = new ArrayList<Integer>();

	private int countAll = 0;
	
	private DuduLog DL = new DuduLog();

	private String[] equipmentName;

	public Dudu(Student s, String[] equi) {
		this.s = s;
		this.equi = equi;
		this.init();

		this.start();

		super.setSize(600, 686);
		setUndecorated(true); // 창 프레임 없애기
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
		time_jlb.setForeground(Color.WHITE);
		time_jlb.setHorizontalAlignment(SwingConstants.LEFT);
		time_jlb.setFont(new Font("굴림", Font.BOLD, 12));

		con.add("North", time_jlb);

		con.add("Center", jp1);

		jp1.setLayout(gl1);

		for (int i = 0; i < 12; ++i) {
			jbt[i] = new JButton();
			jbt[i].setIcon(new ImageIcon(Dudu.class.getResource("/이미지/d.png")));
			jbt[i].setBorderPainted(false); // 버튼 테두리 제거
			jbt[i].setContentAreaFilled(false); // 버튼 테두리 제거
			jp1.add(jbt[i]);
		}

		off_button();
		jp2.setBackground(Color.BLACK);

		con.add("South", jp2);

		jp2.setLayout(gl2);
		jlb.setForeground(Color.WHITE);
		jlb.setBackground(Color.BLACK);
		jlb.setHorizontalAlignment(SwingConstants.CENTER);
		jlb.setFont(new Font("굴림", Font.BOLD, 20));

		jp2.add(jlb);
		jp21.setBackground(Color.BLACK);

		jp2.add(jp21);

		jp21.setLayout(fl21);
		start.setForeground(Color.WHITE);
		start.setFont(new Font("굴림", Font.BOLD, 17));

		jp21.add(start);
		end.setForeground(Color.WHITE);
		end.setFont(new Font("굴림", Font.BOLD, 17));
		end.setBackground(Color.BLACK);
		end.setBorderPainted(false); // 버튼 테두리 제거

		jp21.add(end);

		JButton Backbtn = new JButton(); // 뒤로가기 버튼
		Backbtn.setBackground(Color.BLACK);
		Backbtn.setBorderPainted(false); // 버튼 테두리 제거
		Backbtn.setIcon(new ImageIcon(Dudu.class.getResource("/이미지/뒤로가기버튼.png")));
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectgameWin SW = new SelectgameWin(s, equi);
				DL.insertDudu(s, countAll, count, list);
				SW.setVisible(true);
				dispose();
			}
		});
		jp21.add(Backbtn);

	}

	public void start() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.setBackground(Color.BLACK);
		start.setBorderPainted(false); // 버튼 테두리 제거

		start.addActionListener(this);

		end.addActionListener(this);

		for (int i = 0; i < 12; ++i) {

			jbt[i].addActionListener(this);
		}
	} // end

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

			Thread th = new Thread(this);

			th.start();

			on_button();

			random(0);

		} else if (e.getSource() == end) {
			if (count != -1) {
				InsertPoint.test(s, count * 30);
				InsertPoint.insertGameLog(s, 3, count * 30);
				DL.insertDudu(s, countAll, count, list);
				int point = s.getPoint();
				s.setPoint(point + count * 30);
			}
			SelectgameWin s = new SelectgameWin(this.s, equi);
			s.setVisible(true);
			this.setVisible(false);
		}

		for (int i = 0; i < 12; ++i) {
			if (e.getSource() == jbt[i]) {
				jbt[i].setIcon(new ImageIcon(Dudu.class.getResource("/이미지/d.png")));
				int j = random(i);
				countAll++;
				list.add(j);
			}
		}
	} // end

	public void off_button() {// 종료버튼
		for (int i = 0; i < 12; ++i) {
			jbt[i].setEnabled(false);
		}

	} // end

	public void on_button() {// 시작버튼
		for (int i = 0; i < 12; ++i) {
			jbt[i].setEnabled(true);
		}
	} // end

	public void run() {

		int time = 10;

		while (true) {

			try {

				Thread.sleep(1000);

			} catch (InterruptedException e) {
			}

			time--;

			if (time == 0) {
				System.out.println(list.toString());
				System.out.println(countAll);
				time_jlb.setText("게임이 끝났습니다.");

//            off_button();
				start.setEnabled(true);
				for (int i = 0; i < 12; ++i) {
					jbt[i].setIcon(new ImageIcon(Dudu.class.getResource("/이미지/d.png")));
					jbt[i].setEnabled(false);
				}
				randomsu = 0;
				break;
			}
			time_jlb.setText("시간 => 0:0" + time);
		}

	} // end

	public int random(int i) {

		if (i != randomsu)
			return i;

		count++;

		randomsu = (int) (Math.random() * 12);

		jbt[randomsu].setIcon(new ImageIcon(Dudu.class.getResource("/이미지/" + equi[0] + ".gif")));

		jlb.setText("점수 : " + count * 30);
		
		return randomsu;
	}
} // end