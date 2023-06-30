package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI게임.Dudu;
import GUI게임.HangMan;
import GUI게임.Moving;
import GUI게임.RSP;
import 객체모음.Student;
import 메소드모음.EquipmentItem;
import 유틸.Music;
import 유틸.SoundButton;

public class SelectgameWin extends JFrame {

	private JPanel contentPane;

	private String[] equi;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectgameWin frame = new SelectgameWin();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	static JLabel coinlbl;

	public static void setCoinlbl(String b) {
		coinlbl.setText(b);
		System.out.println(b);
		coinlbl.revalidate();
		coinlbl.repaint();
	}

	/**
	 * Create the frame.
	 */
	public SelectgameWin(Student s, String[] equipmentName) {
		this.equi = equipmentName;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 800, 600);
		setUndecorated(true); // 창 프레임 없애기
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 게임선택 프레임 설정
		setTitle("게임선택"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		SoundButton poopBtn = new SoundButton(Music.S1);
		poopBtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/셀렉버튼똥피하기.png")));
		poopBtn.setBounds(159, 123, 270, 80);
		// 게임하기버튼을 누르면 SelectgameWin으로 이동하는 액션리스너
		poopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Moving(s, equi));
				t.start();
			}
		});

		JButton cutbtn = new JButton(""); // 종료하기
		cutbtn.setBackground(Color.BLACK);
		cutbtn.setBorderPainted(false); // 버튼 테두리 제거
		cutbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/종료버튼.png")));
		cutbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cutbtn.setLocation(758, 10);
		cutbtn.setSize(30, 30);

		JPanel Charpnl = new JPanel(); // 캐릭터 패널
		EquipmentItem.equipmentItem(equipmentName, Charpnl);
		Charpnl.setBounds(544, 211, 150, 200);

		JButton Backbtn = new JButton(""); // 뒤로가기버튼(이미지처리할거임)
		Backbtn.setBackground(Color.BLACK);
		Backbtn.setBorderPainted(false); // 버튼 테두리 제거
		Backbtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.setBounds(748, 550, 40, 40);
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
			}
		});

		SoundButton hmBtn = new SoundButton(Music.S1);
		hmBtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/셀렉버튼행맨.png")));
		hmBtn.setBounds(159, 217, 270, 80);
		hmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HangMan(s, equi);
				dispose();
			}
		});

		SoundButton whackBtn = new SoundButton(Music.S1);
		whackBtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/셀렉버튼두더지.png")));
		whackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dudu d = new Dudu(s, equi);
				dispose();
			}
		});
		whackBtn.setBounds(159, 312, 270, 80);

		SoundButton rspBtn = new SoundButton(Music.S1);
		rspBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RSP rsp = new RSP(s, equi);
				dispose();
			}
		});
		rspBtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/셀렉버튼가위바위보.png")));
		rspBtn.setBounds(159, 406, 270, 80);

		contentPane.setLayout(null);
		contentPane.add(hmBtn);
		contentPane.add(rspBtn);
		contentPane.add(poopBtn);
		contentPane.add(Backbtn);
		contentPane.add(Charpnl);
		contentPane.add(cutbtn);
		contentPane.add(whackBtn);

		coinlbl = new JLabel("1.000");
		coinlbl.setFont(new Font("굴림", Font.BOLD, 18));
		coinlbl.setForeground(Color.WHITE);
		coinlbl.setBounds(576, 181, 120, 20);
		contentPane.add(coinlbl);
		coinlbl.setText("" + s.getPoint());

		JLabel CoinImgLbl = new JLabel("");
		CoinImgLbl.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/코인이미지.png")));
		CoinImgLbl.setBounds(544, 181, 20, 20);
		contentPane.add(CoinImgLbl);

		JButton Explanation1 = new JButton("");
		Explanation1.setBackground(Color.BLACK);
		Explanation1.setBounds(107, 143, 40, 40);
		Explanation1.setBorderPainted(false); // 버튼 테두리 제거
		Explanation1.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/물음표.png")));
		contentPane.add(Explanation1);
		Explanation1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Explanation(1);
			}
		});

		JButton Explanation2 = new JButton("");
		Explanation2.setBackground(Color.BLACK);
		Explanation2.setBounds(107, 238, 40, 40);
		Explanation2.setBorderPainted(false); // 버튼 테두리 제거
		Explanation2.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/물음표.png")));
		contentPane.add(Explanation2);
		Explanation2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Explanation(2);
			}
		});

		JButton Explanation3 = new JButton("");
		Explanation3.setBackground(Color.BLACK);
		Explanation3.setBounds(107, 334, 40, 40);
		Explanation3.setBorderPainted(false); // 버튼 테두리 제거
		Explanation3.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/물음표.png")));
		contentPane.add(Explanation3);
		Explanation3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Explanation(3);
			}
		});

		JButton Explanation4 = new JButton("");
		Explanation4.setBackground(Color.BLACK);
		Explanation4.setBounds(107, 428, 40, 40);
		Explanation4.setBorderPainted(false); // 버튼 테두리 제거
		Explanation4.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/물음표.png")));
		contentPane.add(Explanation4);
		Explanation4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Explanation(4);
			}
		});

	}
}