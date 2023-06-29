
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TestFrame extends JFrame {
	int x = 20;
	int y = 20;
	int upDown = 0;
	int leftRight = 0;
	
	public void fillPoints(List<Point> p, List<JLabel> warm) {
		for (int i = 1; i < warm.size(); i++) {
			p.add(new Point(0, 0));
		}
	}

	public void fillwarms(List<JLabel> warm, JPanel pnl) {
		warm.add(new JLabel());
		warm.add(new JLabel());
		warm.add(new JLabel());
		for (int i = 1; i < warm.size(); i++) {
			ImageIcon icon = new ImageIcon(warmGame.class.getResource("/이미지/머리.png"));
			ImageIcon icon2 = new ImageIcon(warmGame.class.getResource("/이미지/몸통.png"));
			warm.get(0).setIcon(icon);
			warm.get(0).setBounds(20, 0, 20, 20);
			warm.get(i).setIcon(icon2);
			warm.get(i).setBounds(0, 0, 20, 20);
		}
		pnl.add(warm.get(0));
		pnl.add(warm.get(1));
		pnl.add(warm.get(2));
	}
	// 0 0 -20
	

	public TestFrame() {
		JPanel pnl = new JPanel();
		pnl.setLayout(null);

		

		List<JLabel> warm = new ArrayList<>();
		fillwarms(warm, pnl);

		List<Point> ps = new ArrayList<>();
		fillPoints(ps, warm);


		Timer timer = new Timer(100, new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				x += leftRight;
				y += upDown;
				ps.get(0).setLocation(new Point(x, y));
				
				for (int i = ps.size() - 1; i > 0; i--) {
					ps.get(i).setLocation(ps.get(i-1));
				}
				for (int j = 0; j < warm.size(); j++) {
					pnl.add(warm.get(j));
					warm.get(j).setLocation(ps.get(j));
				}
				

				revalidate();
				repaint();
			}
		});
		
		pnl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timer.start();
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					upDown = -20;
					leftRight = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					upDown = 20;
					leftRight = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					upDown = 0;
					leftRight = -20;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					upDown = 0;
					leftRight = 20;
				}

			}
		});

		add(pnl);
		pnl.setFocusable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
	}

	public static void main(String[] args) {
		new TestFrame();
	}
}