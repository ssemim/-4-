package GUI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Explanation extends JFrame {
	private int i;

	Explanation(int i) {
		this.i = i;
		setTitle("게임 설명");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(new MyPanel());
		setSize(450, 500);
		if (i == 1) {
			setSize(290, 489);
		} else if (i == 2) {
			setSize(555, 465);
		} else if (i == 3) {
			setSize(489, 520);
		} else if (i == 4) {
			setSize(799, 619);
		}
		setVisible(true);
		setResizable(false); // 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null); // 창이 가운데 나오게
	}

	class MyPanel extends JPanel {
		ImageIcon icon = new ImageIcon((Explanation.class.getResource("/이미지/Explanation" + i + ".png")));
		Image img = icon.getImage();

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
