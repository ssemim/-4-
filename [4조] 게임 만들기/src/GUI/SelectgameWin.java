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

import GUI게임.Dino;
import GUI게임.HangMan;
import 객체모음.Student;
import 메소드모음.EquipmentItem;
import 유틸.Music;
import 유틸.SoundButton;

public class SelectgameWin extends JFrame {

	private JPanel contentPane;

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

	/**
	 * Create the frame.
	 */
	public SelectgameWin(Student s, String[] equipmentName) {
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

		SoundButton runbtn = new SoundButton(Music.S1);
		runbtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/셀렉버튼달리기.png")));
		runbtn.setBounds(120, 122, 270, 80);
		// 게임하기버튼을 누르면 SelectgameWin으로 이동하는 액션리스너
		runbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dino d = new Dino(s);
				dispose();
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
		Charpnl.setBounds(514, 191, 150, 200);

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
				dispose();
			}
		});

		SoundButton btnRun = new SoundButton(Music.S1);
		btnRun.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/셀렉버튼행맨.png")));
		btnRun.setBounds(120, 216, 270, 80);
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HangMan(s);
				setVisible(false);
			}
		});

		SoundButton numbtn = new SoundButton(Music.S1);
		numbtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/셀렉버튼숫자.png")));
		numbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		numbtn.setBounds(120, 311, 270, 80);

		SoundButton btnRun_1 = new SoundButton(Music.S1);
		btnRun_1.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/셀렉버튼똥피하기.png")));
		btnRun_1.setBounds(120, 405, 270, 80);
		contentPane.setLayout(null);
		contentPane.add(btnRun);
		contentPane.add(btnRun_1);
		contentPane.add(runbtn);
		contentPane.add(Backbtn);
		contentPane.add(Charpnl);
		contentPane.add(cutbtn);
		contentPane.add(numbtn);

		JLabel Coinlbl = new JLabel("1.000");
		Coinlbl.setFont(new Font("굴림", Font.BOLD, 18));
		Coinlbl.setForeground(Color.WHITE);
		Coinlbl.setBounds(534, 161, 130, 20);
		contentPane.add(Coinlbl);
		Coinlbl.setText("" + s.getPoint());

		JLabel CoinImgLbl = new JLabel("");
		CoinImgLbl.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/코인이미지.png")));
		CoinImgLbl.setBounds(514, 161, 20, 20);
		contentPane.add(CoinImgLbl);

	}

}