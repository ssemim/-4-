package GUI게임;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class asd extends JFrame {

	private static final int GRID_SIZE = 4;
	private static final int MOLE_COUNT = 10;

	private JLabel scoreLabel;
	private MoleButton[][] moleButtons;
	private int score;

	public asd() {
		super("Whack-A-Mole Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		scoreLabel = new JLabel("Score: 0");

		moleButtons = new MoleButton[GRID_SIZE][GRID_SIZE];
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				MoleButton button = new MoleButton(row, col);
				moleButtons[row][col] = button;
			}
		}

		// 컴포넌트 배치
		setLayout(new GridLayout(GRID_SIZE + 1, GRID_SIZE));
		add(scoreLabel);
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				add(moleButtons[row][col]);
			}
		}

		// 게임 시작
		startGame();

		// 창 크기 및 위치 설정
		setSize(400, 400);
		setLocationRelativeTo(null); // 화면 중앙에 위치
	}

	private void startGame() {
		score = 0;
		scoreLabel.setText("Score: 0");

		// 모든 버튼 초기화
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				moleButtons[row][col].reset();
			}
		}

		// 지정된 개수만큼의 버튼에 두더지가 나타나도록 설정
		Random random = new Random();
		int moleCount = 0;
		while (moleCount < MOLE_COUNT) {
			int row = random.nextInt(GRID_SIZE);
			int col = random.nextInt(GRID_SIZE);
			if (!moleButtons[row][col].hasMole()) {
				moleButtons[row][col].setMole();
				moleCount++;
			}
		}

		// 게임 진행을 위한 타이머 설정
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 두더지가 나타날 버튼 선택
				int row = random.nextInt(GRID_SIZE);
				int col = random.nextInt(GRID_SIZE);

				// 기존에 두더지가 있는 버튼일 경우 다른 버튼 선택
				while (moleButtons[row][col].hasMole()) {
					row = random.nextInt(GRID_SIZE);
					col = random.nextInt(GRID_SIZE);
				}

				// 새로운 위치에 두더지 나타냄
				moleButtons[row][col].setMole();
			}
		});
		timer.start();
	}

	private class MoleButton extends JButton {
		private int row;
		private int col;
		private boolean hasMole;

		public MoleButton(int row, int col) {
			this.row = row;
			this.col = col;
			this.hasMole = false;

			setPreferredSize(new Dimension(80, 80));

			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (hasMole) {
						score++;
						scoreLabel.setText("Score: " + score);
						hasMole = false;
						setBackground(Color.WHITE);
					}
				}
			});
		}

		public void setMole() {
			hasMole = true;
			setBackground(Color.BLACK);
		}

		public void reset() {
			hasMole = false;
			setBackground(Color.WHITE);
		}

		public boolean hasMole() {
			return hasMole;
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new asd().setVisible(true);
			}
		});
	}
}
