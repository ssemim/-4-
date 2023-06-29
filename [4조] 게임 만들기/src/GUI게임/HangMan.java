package GUI게임;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.MainWin;
import GUI.SelectgameWin;
import 객체모음.Student;
import 메소드모음.InsertPoint;

/*
 * @author Soyoung Kim
 * when 2020-04-05
 * @version 1.0
 */

public class HangMan extends JFrame {
	private JLabel answeredField;
	private JLabel missedField;
	private JLabel countLabel;
	private JPanel inputedPanel = new JPanel();
	// JLabel을 setIcon()할때마다 다른 패널이 프레임에서 사라지는 문제 때문에 JButton으로 대체한다.
	// private JButton humanDiePicture = new JButton();
	// JButton으로 바꿔도 문제가 발생한다...ㅠ

	// ===============================

	// 게임용 필드 변수
	// --------------------------------------------------------------------------------------------------------------------

	private static String[] wordArr = { "interface", "default", "extends", "serialized", "abstract", "continue",
			"package", "assert", "private", "throw", "boolean", "protected", "public", "throws", "break", "double",
			"import", "public", "transient", "return", "extends", "void", "catch", "final", "interface", "static",
			"finally", "super", "class", "float", "native", "switch", "while" };

	// 테스트용
//	private String[] wordArr = {"serialized"};

	// 게임횟수 카운트
	private static int cnt = 30;

	private JTextField userAnswer;
	private JTextField countField;
	private static int point;
	private static String computerAnswer;// 정답이 들어있는 곳
	private static char[] computerAnswerArr;
	private static ArrayList<Character> computerAnswerList;
	private static ArrayList<Character> userAnswerList;// 정답 출력하는 곳
	private static ArrayList<Character> userMissedList;// 틀린 스펠 들어가는곳
	private static ArrayList<Character> userput;
	private int pointAll;
	private JLabel lblNewLabel;

	public static void start() {

		point = (int) (Math.random() * wordArr.length);

		userput = null;
		userAnswerList = null;
		computerAnswerList = null;
		computerAnswer = wordArr[point].toLowerCase();// 정답이 들어있는 곳
		computerAnswerArr = computerAnswer.toCharArray();// 정답을 char타입으로 나누는 곳
		computerAnswerList = new ArrayList<>();

		userAnswerList = new ArrayList<>();
		userput = new ArrayList<>();
		userMissedList = new ArrayList<>();

		for (int i = 0; i < computerAnswer.length(); i++) {
			userAnswerList.add('ㅡ');
		}

		//
		for (char c : computerAnswerArr) {
			computerAnswerList.add(c);
		}
		System.out.println("정답 : " + computerAnswer);
	}
	// ------------------------------------------------------------------------------------------------------------------------------

	public void playGame(char userInput) {
		System.out.println("패널에서 playGame()메소드가 호출되었습니다. ");

		if (userput == null || !userput.contains(userInput)) {
			if (computerAnswerList.contains(userInput)) { // 사용자가 입력한 단어가 정답에 있는 경우 -----------------------------
				System.out.println("사용자가 입력한 단어가 답에 있는 경우 ");
				userput.add(userInput);
				for (int i = 0; i < computerAnswer.length(); i++) {
					if (userAnswer.getText().charAt(0) == computerAnswerArr[i]) {
						userAnswerList.set(i, computerAnswerArr[i]);
						pointAll += 100;
					}
				}
				// 단어가 맞았을 경우 추가된 userAnswerList를 answeredField에 갱신해주기
				answeredField.setText(userAnswerList.toString());
				System.out.println("사용자가 입력한 단어가 맞아서 userAnswerList가 갱신됨 : " + userAnswerList.toString());

			} else { // 사용자가 입력한 단어가 정답에 없는 경우 -----------------------------
				System.out.println("사용자가 입력한 단어가 답에 없는 경우");
				countField.setText(Integer.toString(--cnt)); // 카운트다운을 줄이면서 countField값 수정하기

				changeLabelOfHuman(cnt); // 사람 그림 바꾸기 (인간이 죽어간다..ㅠ)

				userMissedList.add(userInput);
				missedField.setText(userMissedList.toString());
				userput.add(userInput);
			}

			int response = -1;
			// 사용자가 승리한 경우
			if (userAnswerList.equals(computerAnswerList)) {
				//
			}

			// 사용자가 패배한 경우
			if (cnt < 1) { // 카운트다운 끝. 인간이 죽은 경우
				System.out.println("카운트다운 끝. 유저의 패배.");
			}
		} else {
			lblNewLabel.setText(userInput + "는 이미 입력된 값입니다.");
			lblNewLabel.setVisible(true);
		}
	}

	public HangMan(Student s) { // PlayPanel의 메인패널. 가장 큰 패널.

		pointAll = 0;

		// GUI 셋팅 ============================\ super("Save the Hang Man");
		start();
		// 프레임 셋팅
		setBounds(0, 0, 560, 460);
		setLocationRelativeTo(null); // 가운데 정렬
		setResizable(false); // 창 크기조절 못하도록
		setBackground(new Color(0, 0, 204));
		setBounds(0, 0, 560, 460);

		getContentPane().setLayout(null);
		setUndecorated(true); // 창 프레임 없애기
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
		getContentPane().setLayout(null); // 레이아웃을 내맘대로 설정가능하게 해줌.
		// ====================================

		// 게임 세팅 ============================
		// inputedPanel에 들어갈 값 초기화
		// 카운트다운동안 사용자가 입력할 userAnswerList에 대한 초기화

		// -----------------------------------------------------------------

		// userInputPanel 끝
		// ====================================================================

		// 3. inputedPanel 시작
		// ====================================================================

		// 여기가 들어가있는 panel

		JButton Backbtn = new JButton(""); // 뒤로가기
		Backbtn.setBackground(Color.BLACK);
		Backbtn.setBorderPainted(false); // 버튼 테두리 제거
		Backbtn.setIcon(new ImageIcon(SelectgameWin.class.getResource("/이미지/뒤로가기버튼.png")));
		Backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InsertPoint.test(s, returnPoint());
				InsertPoint.insertGameLog(s, 2, returnPoint());
				int pi = s.getPoint();
				s.setPoint(pi + pointAll);
				pointAll = 0;
				cnt = 30;
				MainWin MW = new MainWin(s);
				MW.setVisible(true);
				dispose();
			}
		});
		Backbtn.setLocation(510, 10);
		Backbtn.setSize(40, 40);
		inputedPanel.add(Backbtn);

		inputedPanel.setBackground(Color.BLACK);
		inputedPanel.setBounds(0, 0, 560, 460);
		inputedPanel.setLayout(null);

		// 맞는 단어 입력되는 라벨/필드
		// ----------------------------------------------------------------------------
		countLabel = new JLabel(computerAnswer.length() + "개의 철자:");
		countLabel.setBounds(166, 258, 230, 37);
		inputedPanel.add(countLabel);
		countLabel.setHorizontalAlignment(SwingConstants.CENTER);
		countLabel.setForeground(new Color(255, 204, 0));
		countLabel.setFont(new Font("Dialog", Font.BOLD, 25));

		answeredField = new JLabel(userAnswerList.toString());
		answeredField.setFont(new Font("Press Start K", Font.PLAIN, 20));
		answeredField.setHorizontalAlignment(SwingConstants.CENTER);
		// answeredField.setText("nullpointerexception");
		answeredField.setForeground(new Color(255, 204, 0));
		answeredField.setBackground(Color.BLACK);
		answeredField.setBounds(12, 305, 536, 70);
		inputedPanel.add(answeredField);
//		answeredField.setColumns(10);
		// ----------------------------------------------------------------------------

		// 틀린 단어 입력되는 라벨/필드
		// ----------------------------------------------------------------------------
		JLabel userHistory = new JLabel("없는것 :");
		userHistory.setBounds(0, 398, 140, 37);
		inputedPanel.add(userHistory);
		userHistory.setForeground(new Color(255, 204, 0));
		userHistory.setHorizontalAlignment(SwingConstants.CENTER);
		userHistory.setFont(new Font("Press Start K", Font.BOLD, 28));

		missedField = new JLabel();
		missedField.setFont(new Font("Press Start K", Font.PLAIN, 21));
		missedField.setHorizontalAlignment(SwingConstants.CENTER);
		// missedField.setText("dddd");
		missedField.setForeground(new Color(255, 204, 0));
		missedField.setBackground(Color.BLACK);
		missedField.setBounds(136, 388, 412, 60);
		inputedPanel.add(missedField);
//		missedField.setColumns(10);

		userAnswer = new JTextField();
		userAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		userAnswer.setForeground(Color.BLUE);
		userAnswer.setFont(new Font("Dialog", Font.PLAIN, 18));
		userAnswer.setFocusable(true);
		userAnswer.setColumns(10);
		userAnswer.setBackground(Color.LIGHT_GRAY);
		userAnswer.setBounds(440, 143, 108, 67);
		inputedPanel.add(userAnswer);
		// ----------------------------------------------------------------------------------------

		userAnswer.setFocusable(true);

		countField = new JTextField();
		countField.setText("30");
		countField.setHorizontalAlignment(SwingConstants.CENTER);
		countField.setForeground(new Color(255, 51, 0));
		countField.setFont(new Font("Dialog", Font.PLAIN, 26));
		countField.setColumns(10);
		countField.setBackground(Color.BLACK);
		countField.setBounds(448, 71, 82, 55);
		inputedPanel.add(countField);

		JLabel lblNewLabel_2 = new JLabel("목숨 :");
		lblNewLabel_2.setForeground(new Color(255, 204, 0));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 23));
		lblNewLabel_2.setBounds(354, 69, 82, 60);
		inputedPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("입력하는 곳 :\r\n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(new Color(255, 204, 0));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1.setBounds(296, 145, 140, 60);
		inputedPanel.add(lblNewLabel_1);

		System.out.println(computerAnswer);
		userAnswer.addKeyListener(new KeyAdapter() {
			private Object pointAll;

			@Override
			public void keyPressed(KeyEvent e) {
				lblNewLabel.setVisible(false);
				if (!(cnt < 1)) {
					System.out.println(e.getKeyCode());
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						System.out.println("엔터키 이벤트 실행 ");
						if (userAnswer.getText().length() == 1) {
							playGame(userAnswer.getText().charAt(0));
							userAnswer.setText("");
						} else if (userAnswer.getText().length() > 1) {
							lblNewLabel.setText("문자는 하나만 입력해주세요.");
							lblNewLabel.setVisible(true);
						} else {
							cnt--;
							countField.setText(Integer.toString(cnt));
							changeLabelOfHuman(cnt); // 사람 그림 바꾸기 (인간이 죽어간다..ㅠ)

							if (userAnswerList.equals(computerAnswerList)) {
								System.out.println("s");
								start();
								cnt++;
								countField.setText(Integer.toString(cnt));
								missedField.setText(userMissedList.toString());
								answeredField.setText(userAnswerList.toString());
								//
							}
							// 사용자가 패배한 경우
							if (cnt < 1) { // 카운트다운 끝. 인간이 죽은 경우
								System.out.println("카운트다운 끝. 유저의 패배.");
							}
						}
					}
				} else {
					lblNewLabel.setText("목숨을 다하셨습니다. 획득 코인: " + returnPoint());
					lblNewLabel.setVisible(true);
				}
			}
		});

		userAnswer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// check = true;
				userAnswer.setText("");
			}
		});

		getContentPane().add(inputedPanel);

		lblNewLabel = new JLabel("중복입력입니다.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(306, 220, 244, 28);
		inputedPanel.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		setVisible(true);
//		new PlayMusic("Blues.wav"); // 음악 재생 
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public void changeLabelOfHuman(int cnt) {
		// human패널의 사람그림을 바꾸는 메소드
		System.out.println(">>사람그림바꾸기!<<");
		System.out.println(cnt);

		int num = 8;

		if (cnt == 0) {

			if (userAnswerList.equals(computerAnswerList)) { // 이겼을 때

			} else { // 졌을 때

			}

		} else { // 목숨이 하나 남았을 때

			repaint();

		}
		repaint();
	}

	public int returnPoint() {
		return pointAll;
	}
}