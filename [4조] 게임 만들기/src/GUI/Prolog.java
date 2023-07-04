package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import 객체모음.Student;
import 소리모음.BasicMusic;
import 소리모음.PrologMusic;
import 유틸.Music;
import 유틸.ResourceSoundPack;
import 유틸.SoundButton;

import javax.swing.JButton;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;

public class Prolog extends JFrame {

	private JLabel label;
	private String[] sentences = { "평화로운 몬스터 마을 어쩌고저쩌고.<br>어쩌고저쩌고의 주민들은 평화롭게 생활하던 중, 한 가지 이상한 일이 벌어졌습니다.",
			"갑자기 하늘에 마을을 뒤덮는 어두운 그림자가 생기더니, 마을 주민들을 납치해갔습니다.", "이 어둠은 마을 주민들을 공포에 떨게 하며, 그들은 밤에 외출하는 것을 피하기 시작했습니다.",
			"당신은 주민들을 찾아 마을 밖을 돌아다니던 도중,<br>숲의 깊은 곳에서 마주친 거대한 그림자의 정체를 알아차립니다.",
			"그 그림자는 어둠의 마법사로<br>사악한 악마의 힘을 이용하여 세계를 지배하려는 음모를 꾸미고 있습니다. ",
			"어둠의 마법사에게서 마을 주민들을 구하기 위해서는<br>네가지의 던전을 통과하여 탈출해야합니다.", "마을 주민들을 구해주세요!"

	};
	private int sentenceIndex = 0;
	private int charIndex = 0;
	private Timer timer;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Font font = new Font("굴림", Font.PLAIN, 15);
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//					Prolog frame = new Prolog();
//					frame.setVisible(true);
//					frame.startAnimation();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

//	public static void main(String[] args) {
//	    EventQueue.invokeLater(new Runnable() {
//	        public void run() {
//	            try {
////	                Font font = new Font("굴림", Font.PLAIN, 15);
//	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	                Student student = new Student(); // 학생 객체 생성
//	                Prolog frame = new Prolog(student); // 생성자 호출
//	                frame.setVisible(true);
//	                frame.startAnimation();
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    });
//	}

	/**
	 * Create the frame.
	 */
	public Prolog(Student s) {
		Font font = new Font("굴림", Font.PLAIN, 15);

		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setUndecorated(true); // 창 프레임 없애기
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JButton cutbtn = new JButton(""); // 종료하기
		cutbtn.setBorderPainted(false); // 버튼 테두리 제거
		cutbtn.setContentAreaFilled(false); // 버튼 내용 투명화
		cutbtn.setOpaque(false); // 버튼 배경 투명화
		cutbtn.setIcon(new ImageIcon(Prolog.class.getResource("/이미지/종료버튼.png")));
		cutbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cutbtn.setBounds(770, 0, 30, 30);
		getContentPane().add(cutbtn);

		SoundButton nextBtn = new SoundButton(Music.S1); // 스킵버튼
		nextBtn.setBorderPainted(false); // 버튼 테두리 제거
		nextBtn.setContentAreaFilled(false);
		nextBtn.setOpaque(false);
		nextBtn.setIcon(new ImageIcon(Prolog.class.getResource("/이미지/next.png")));
		// 뒤로가기버튼을 누르면 MainWin으로 이동하는 액션리스너
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResourceSoundPack.prostopLoopSound();
				ResourceSoundPack.LoopSoundPlaying();
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
//				BasicMusic bm = new BasicMusic();
//				bm.setVisible(false);
				dispose();
			}

		});
		nextBtn.setBounds(710, 569, 100, 30);
		getContentPane().add(nextBtn);

		label = new JLabel();
		label.setBounds(0, 0, 800, 600);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("굴림", Font.PLAIN, 12));

		label.setHorizontalAlignment(SwingConstants.CENTER); // 글자놓는 방향
		getContentPane().add(label); // 보더레이아웃방향

		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sentenceIndex < sentences.length) {
					String sentence = sentences[sentenceIndex];
					if (charIndex < sentence.length()) {
						String text = sentence.substring(0, charIndex + 1);
						label.setText(addLineBreaks(text));
						charIndex++;
					} else {
						sentenceIndex++;
						charIndex = 0;
						if (sentenceIndex < sentences.length) {
							label.setText("");
							try {
								Thread.sleep(1000); // 1초 텀을 주기 위해 스레드를 잠시 멈춥니다.
							} catch (InterruptedException ex) {
								ex.printStackTrace();
							}
						} else {
							timer.stop();
						}
					}
				}
			}
		});
		timer.start();

	}

	public void startAnimation() {
		timer.start();
	}

	private String addLineBreaks(String text) {
		return "<html>" + text.replaceAll("\\.<br>", ".<br>") + "</html>";
	}
}
