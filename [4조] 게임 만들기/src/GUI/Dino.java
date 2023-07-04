package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import GUI.MainWin;
import GUI.SelectgameWin;
import 객체모음.Student;
import 메소드모음.InsertPoint;

public class Dino extends JFrame implements ActionListener, KeyListener {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
	private static final int GROUND_HEIGHT = 100;
	private static final int DINO_WIDTH = 50;
	private static final int DINO_HEIGHT = 50;
	private static final int CACTUS_WIDTH = 40;
	private static final int CACTUS_HEIGHT = 40;
	private static final int CACTUS_START = WIDTH;
	private static final int CACTUS_END = -CACTUS_WIDTH;
	private static final int JUMP_HEIGHT = 100;
	private static int GRAVITY = 5;
	private static final int SCORE_INCREMENT = 100;
	private static Timer timer = null;
	private JLabel dinoLabel;
	private JLabel cactusLabel;
	private JPanel gamePanel;
	private int dinoX;
	private int dinoY;
	private int cactusX;
	private int cactusY;
	private int score;
	private boolean isJumping;
	private int jumpHeight;
	private Student s;

	public Dino(Student s) {
		this.s = s;
		setTitle("Dinosaur Game");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		addKeyListener(this);

		dinoX = 50;
		dinoY = HEIGHT - GROUND_HEIGHT - DINO_HEIGHT;
		cactusX = WIDTH;
		cactusY = HEIGHT - GROUND_HEIGHT - CACTUS_HEIGHT;

		score = 0;
		isJumping = false;
		jumpHeight = 0;

		gamePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, getWidth(), getHeight());

				g.setColor(Color.BLACK);
				g.drawLine(0, getHeight() - GROUND_HEIGHT, getWidth(), getHeight() - GROUND_HEIGHT);

				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString("Score: " + score, 20, 30);
			}
		};
		gamePanel.setLayout(null);
		gamePanel.setBackground(Color.WHITE);
		getContentPane().add(gamePanel);

		dinoLabel = new JLabel(new ImageIcon(SelectgameWin.class.getResource("/이미지/dino.png")));
		dinoLabel.setBounds(dinoX, dinoY, DINO_WIDTH, DINO_HEIGHT);
		gamePanel.add(dinoLabel);

		cactusLabel = new JLabel(new ImageIcon(SelectgameWin.class.getResource("/이미지/tree.png")));
		cactusLabel.setBounds(cactusX, cactusY, 30, 30);
		gamePanel.add(cactusLabel);

		setVisible(true);
		timer = new Timer(10, this);
		timer.start();
	}

	private void update() {
		if (isJumping) {
			if (dinoY > HEIGHT - GROUND_HEIGHT - DINO_HEIGHT - jumpHeight) {
				dinoY -= GRAVITY;
			} else {
				isJumping = false;
				jumpHeight = 0;
			}
		} else {
			if (dinoY < HEIGHT - GROUND_HEIGHT - DINO_HEIGHT) {
				dinoY += GRAVITY;
			}
		}

		if (cactusX > CACTUS_END) {
			if (score >= 100 && score < 200) {
				cactusX -= 6;
			} else if (score >= 200 && score < 300) {
				cactusX -= 8;
			} else if (score >= 300 && score < 400) {
				cactusX -= 10;
			} else if (score >= 400 && score < 500) {
				cactusX -= 12;
			} else if (score >= 500 && score < 600) {
				cactusX -= 14;
			} else if (score >= 600 && score < 700) {
				cactusX -= 16;
			} else if (score >= 700 && score < 800) {
				cactusX -= 18;
			} else if (score >= 800 && score < 900) {
				cactusX -= 20;
			} else if (score >= 900 && score < 1000) {
				cactusX -= 22;
			} else {
				cactusX -= 4;
			}

		} else {
			cactusX = CACTUS_START;
			score += SCORE_INCREMENT;
		}

		if (dinoX + DINO_WIDTH > cactusX && dinoX < cactusX + CACTUS_WIDTH && dinoY + DINO_HEIGHT > cactusY) {
			gameOver();
		}

		dinoLabel.setBounds(dinoX, dinoY, DINO_WIDTH, DINO_HEIGHT);
		cactusLabel.setBounds(cactusX, cactusY, CACTUS_WIDTH, CACTUS_HEIGHT);
	}

	private void gameOver() {
//		JOptionPane.showMessageDialog(this, "Game Over\nScore: " + score, "Game Over", JOptionPane.PLAIN_MESSAGE);
		timer.stop();
		InsertPoint.insertGameLog(s, 1, score);
		InsertPoint.test(s, score);
		int point = s.getPoint();
		s.setPoint(point + score);
		score = 0;
		MainWin MW = new MainWin(s);
		MW.setVisible(true);
//		dispose(); // 현재의 Dino 프레임을 닫음
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		gamePanel.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && !isJumping && dinoY == 250) {
			isJumping = true;
			jumpHeight = JUMP_HEIGHT;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	

public static class Dinotest {
	public static void main(String[] args) {
		 Student student = new Student(); // 학생 객체 생성 (필요한 경우 인자 전달)
	        Dino dino = new Dino(student); 
	}

}

}
