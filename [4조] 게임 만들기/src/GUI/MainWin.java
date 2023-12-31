package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import GUI게임.RSP;
import 객체모음.Student;
import 메소드모음.EquipmentItem;
import 유틸.Music;
import 유틸.ResourceSoundPack;
import 유틸.SoundButton;
import 유틸.Util;

public class MainWin extends JFrame {

	private JPanel contentPane;
	Image image;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Student s = new Student();
//					MainWin frame = new MainWin(s);
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
	public MainWin(Student s) {

		EquipmentItem e = new EquipmentItem();
		String[] equipmentName = e.selectItemIamgeName(e.itemNos(s));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600); // 프레임 크기
		setUndecorated(true); // ㅊ ㅍㄹㅇ ㅇㅇㄱ
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 메인창 프레임 설정
		setTitle("Main"); // 타이틀 이름
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.

		// 게임하기버튼을 누르면 SelectgameWin으로 이동하는 액션리스너
		SoundButton Gamebtn = new SoundButton(Music.S1); // 게임 버튼
		Gamebtn.setBounds(129, 111, 240, 80);
		Gamebtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/게임하기버튼.png")));

		Gamebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResourceSoundPack.btnSound();
				SelectgameWin SlecW = new SelectgameWin(s, equipmentName);
				SlecW.setVisible(true);
				setVisible(false);
			}
		});

		SoundButton invenbtn = new SoundButton(Music.S1); // 인벤 버튼
		invenbtn.setBounds(129, 222, 240, 80);
		invenbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/인벤토리버튼.png")));
		// 인벤토리버튼을 누르면 invenWin으로 이동하는 액션리스너
		invenbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResourceSoundPack.btnSound();
				invenWin IW = new invenWin(s, equipmentName);
				IW.setVisible(true);
				setVisible(false);
				dispose();
			}
		});

		SoundButton rankbtn = new SoundButton(Music.S1); // 랭킹 버튼
		rankbtn.setBounds(129, 331, 240, 80);
		rankbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/랭킹버튼.png")));
		// 랭킹버튼을 누르면 RankWin으로 이동하는 액션리스너
		rankbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResourceSoundPack.btnSound();
				RankWin RW = new RankWin(s);
				RW.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		SoundButton Storebtn = new SoundButton(Music.S1); // 상점 버튼
		Storebtn.setBounds(129, 443, 240, 80);
		Storebtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/상점버튼.png")));
		// 상점버튼을 누르면 StoreWin으로 이동하는 액션리스너
		Storebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResourceSoundPack.btnSound();
				StoreWin StoreW = new StoreWin(s, equipmentName);
				StoreW.setVisible(true);
				setVisible(false);
				dispose();
			}
		});

		JPanel Charpanel = new JPanel(); // 캐릭터 이미지가 나오는 Panel
		EquipmentItem.equipmentItem(equipmentName, Charpanel);
		Charpanel.setBounds(502, 211, 150, 200);
		contentPane.setLayout(null);

		JButton cutbtn = new JButton(); // 종료버튼
		cutbtn.setBackground(Color.BLACK);
		cutbtn.setBorderPainted(false); // 버튼 테두리 제거
		cutbtn.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/종료버튼.png")));
		cutbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResourceSoundPack.btnSound();
				System.exit(0);
			}
		});

		JLabel ReCoin = new JLabel("");
		ReCoin.setFont(new Font("굴림", Font.BOLD, 18));
		ReCoin.setForeground(Color.WHITE);
		ReCoin.setHorizontalAlignment(SwingConstants.LEFT);
		ReCoin.setBounds(534, 181, 120, 20);
		ReCoin.setText("" + s.getPoint());

		cutbtn.setLocation(758, 10);
		cutbtn.setSize(30, 30);

		contentPane.setLayout(null);
		contentPane.add(cutbtn);
		contentPane.add(Storebtn);
		contentPane.add(rankbtn);
		contentPane.add(invenbtn);
		contentPane.add(Charpanel);
		contentPane.add(Gamebtn);
		contentPane.add(ReCoin);

		JLabel CoinImgLbl = new JLabel("");
		CoinImgLbl.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/코인이미지.png")));
		CoinImgLbl.setBounds(502, 181, 20, 20);
		contentPane.add(CoinImgLbl);
//		ResourceSoundPack.backgrundsound();
		Util.removeAllButtonFocus(contentPane);

		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		ImageIcon checkedIcon = new ImageIcon("/이미지/음소거됐을때.png"); // 체크된 상태의 이미지 경로를 지정해주세요
        ImageIcon uncheckedIcon = new ImageIcon("/이미지/음소거.png"); // 체크되지 않은 상태의 이미지 경로를 지정해주세요
        chckbxNewCheckBox.setIcon(new ImageIcon(MainWin.class.getResource("/이미지/음소거.png")));
        chckbxNewCheckBox.setSelectedIcon(new ImageIcon(MainWin.class.getResource("/이미지/음소거됐을때.png")));

		chckbxNewCheckBox.setBackground(Color.BLACK);
		chckbxNewCheckBox.setOpaque(false);
		chckbxNewCheckBox.setBounds(758, 564, 30, 30);
		contentPane.add(chckbxNewCheckBox);

		chckbxNewCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox c = (JCheckBox) e.getSource();
				if (c.isSelected()) {
					ResourceSoundPack.stopLoopSound();
					System.out.println("체크됨");
				} else if (!c.isSelected()) {
					ResourceSoundPack.LoopSoundPlaying();
					System.out.println("체크취소됨");
				}
			}
		});

		if (ResourceSoundPack.isLoopSoundPlaying()) {
			chckbxNewCheckBox.setSelected(false);
		} else {
			chckbxNewCheckBox.setSelected(true);
		}
	}

}