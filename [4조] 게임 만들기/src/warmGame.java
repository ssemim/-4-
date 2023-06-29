


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class fillPoint {
	public static Point[] fillPoints(Point[] p) {
		for (int i = 1; i < p.length; i++) {
			p[i] = new Point(0, 0);
		}
		return p;
	}
}

class fillwarm {
	public static JLabel[] fillwarms(JLabel[] warm) {
		for (int i = 1; i < warm.length; i++) {
			ImageIcon icon = new ImageIcon(warmGame.class.getResource("/이미지/머리.png"));
			ImageIcon icon2 = new ImageIcon(warmGame.class.getResource("/이미지/몸통.png"));
			warm[0] = new JLabel(icon);
			warm[0].setBounds(20, 0, 20, 20);
			warm[i] = new JLabel(icon2);
			warm[i].setBounds(0, 0, 20, 20);
		}
		return warm;
	}
}

class Game extends JFrame {
	private int upDown;
	private int y;
	private int leftRigth;
	private int x;
	Point greedPoint;
	int greedCount = 4;
	private JLabel lbl;
	private JLabel[] warm = new JLabel[300];
	Point[] p = new Point[300] ;
	private Timer earthworm;
	private int blockCount = 4;
	Point[] blockP = new Point[70];
	public Game() {
		
		fillPoint.fillPoints(blockP);
		blockP[0] = new Point(0, 0);
		fillPoint.fillPoints(p);
		JPanel pnl = new JPanel();
		pnl.setLayout(null);
		fillwarm.fillwarms(warm);
		pnl.add(warm[0]);
		pnl.add(warm[1]);
		pnl.add(warm[2]);
		
		
		
		ImageIcon icon4 = new ImageIcon(warmGame.class.getResource("/이미지/장애물.png"));
		int randomX = (int) (Math.random() * 24) * 20;
		int randomY = (int) (Math.random() * 24) * 20;
		Point randomP = new Point(randomX, randomY);
		ImageIcon icon3 = new ImageIcon(warmGame.class.getResource("/이미지/먹이.png"));
		lbl = new JLabel(icon3);
		lbl.setBounds(randomX, randomY, 20, 20);
		pnl.add(lbl);
		x = 20;
		earthworm = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime time = LocalDateTime.now();
				for (int i = 2; i < greedCount; i++) {
					if (warm[0].getLocation().equals(warm[i].getLocation())) {
						System.out.println("1번위치문제");
						earthworm.stop();
					}
				}
				
				
				x += leftRigth;
				y += upDown;
				p[0] = new Point(x, y);
				
				for (int i = p.length - 1; i > 0; i--) {
					p[i] = p[i - 1];
				}
				for (int j = 0; j < greedCount; j++) {
					pnl.add(warm[j]);
					warm[j].setLocation(p[j]);
				}
				
				
				if (lbl.getBounds().intersects(warm[0].getBounds())){
					pnl.remove(lbl);
					int randomX = (int) (Math.random() * 23) * 20;
					int randomY = (int) (Math.random() * 23) * 20;
					Point randomP = new Point(randomX, randomY);
					ImageIcon icon3 = new ImageIcon(warmGame.class.getResource("/이미지/먹이.png"));
					lbl = new JLabel(icon3);
					lbl.setBounds(0, 0, 20, 20);
					pnl.add(lbl);
					lbl.setLocation(randomP);
					greedPoint = new Point(randomP);
					pnl.add(lbl);
					greedCount++;
				}
				if (greedCount == 300 + 2) {
					System.out.println("2번위치문제");
					earthworm.stop();
					}
				if (warm[0].getLocation().getX() == 480 
						|| warm[0].getLocation().getY() == 460
						|| warm[0].getLocation().getX() == -20
						|| warm[0].getLocation().getY() == -20) {
					System.out.println("3번위치문제");
					earthworm.stop();
				}
				Point rP = new Point(0, 0);
				if (greedCount > blockCount) {
					boolean flag = true;
					while (flag) {
						int randomX = (int) (Math.random() * 23) * 20;
						int randomY = (int) (Math.random() * 23) * 20;
						rP = new Point(randomX, randomY);
						if (!(rP.equals(greedPoint))){
							flag = false;
						}
					}
					try {
//		먹이랑 벽이랑 안겹치게 설정
						if(!(greedPoint.equals(rP))) {
							JLabel lblb = new JLabel(icon4);
							lblb.setBounds(randomX, randomY, 20, 20);
							pnl.add(lblb);
							blockCount += 5;
							blockP[greedCount] = new Point(randomX, randomY);
						}
					}catch (NullPointerException e1) {
						// TODO: handle exception
						System.out.println("캐치");
					}
				}
				for (int i = 0; i < blockP.length; i++) {
					if(blockP[i].getLocation().equals(warm[0].getLocation())) {
						earthworm.stop();
						System.out.println("4번문제");
					}
				}
				
				revalidate();
				repaint();
			}
		});
		
		
		
		
		pnl.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				earthworm.start();
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					upDown = -20;
					leftRigth = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					upDown = 20;
					leftRigth = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					upDown = 0;
					leftRigth = -20;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					upDown = 0;
					leftRigth = 20;
				}
			}
		});
		
		
		
		
		pnl.setFocusable(true);
		add(pnl);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
public class warmGame {
	public static void main(String[] args) {
		new Game();
	}
}
