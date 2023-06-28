package 유틸;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

public class SoundButton extends JButton {
	private static String path;
    public SoundButton(int i) {
        super();
        
        path = null;
        if(i == Music.S1) {
        	path = "/소리모음/test.wav";
        }
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playSound();
            }
        });
    }

    private void playSound() {
        try {
        	URL url = SoundButton.class.getResource(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
//            무한반복 하는법
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void test() {
        try {
        	URL url = SoundButton.class.getResource(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
//            무한반복 하는법
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            ex.printStackTrace();
        }
    }
}

