import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Management extends JFrame {
	static JPanel all = new JPanel();
	static CardLayout card = new CardLayout();
	static AllRecords allRecords = new AllRecords();
	LottoDrawPage lottoDrawPage = new LottoDrawPage(this);
	TestA testA = new TestA(lottoDrawPage, allRecords);
	StartPage startPage = new StartPage(allRecords);
	Clip BackgrundSoundClip;
	int i;

	public void close() {
		this.setVisible(false);
		this.dispose();
	}

	public Management() {
		setTitle("로또");

		all.setLayout(card);

		all.add(startPage, "시작");
		all.add(new Tutorial(), "튜토리얼");
		all.add(new Tutorial2(), "튜토리얼2");
		all.add(new Tutorial3(), "튜토리얼3");
		all.add(new Tutorial4(), "튜토리얼4");
		all.add(new Tutorial5(), "튜토리얼5");
		all.add(new Tutorial6(), "튜토리얼6");
		all.add(new Tutorial7(), "튜토리얼7");
		all.add(new Tutorial7_1(), "튜토리얼7-1");
		all.add(new Tutorial7_2(), "튜토리얼7-2");
		all.add(new TutorialTicket(), "튜토리얼8"); // A슬롯 번호(자동)
		all.add(new Tutorial9(), "튜토리얼9"); // 번호 오토 선택 시 뜨는 안내창
		all.add(new Tutorial10(), "튜토리얼10"); // 번호 수동 선택 시 뜨는 안내창
		all.add(new TutorialTicket2(), "튜토리얼11"); // A슬롯 번호(수동) 123457 선택하면 시작페이지로.
		all.add(new Tutorial12(), "튜토리얼12"); // 번호 수동 선택 시 뜨는 안내창
		all.add(new Tutorial13(), "튜토리얼13"); // 번호 수동 선택 시 뜨는 안내창
		all.add(new HelpMessage(), "도움말");
		all.add(testA, "번호 선택");
		all.add(new LottoTicket1(testA), "티켓1");
		all.add(new LottoTicket2(testA), "티켓2");
		all.add(new LottoTicket3(testA), "티켓3");
		all.add(new LottoTicket4(testA), "티켓4");
		all.add(new LottoTicket5(testA), "티켓5");
		all.add(new LotteryBall(), "공튀기기");
		all.add(lottoDrawPage, "당첨 번호");

		card.show(all, "시작");
		add(all);

		all.setPreferredSize(new Dimension(340, 550));
		setResizable(false);
		pack();
		
		ResourceSoundPack.backgrundsound();
		

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	

	public static void main(String[] args) {
		new Management();
		
	}
}