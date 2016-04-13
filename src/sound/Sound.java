package sound;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class Sound extends JFrame {
	
	//Constructor
	public Sound() {
		try {
			// Open an audio input stream.
			File soundFile = new File("bin/sound/LordOfTheLand.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			//clip.start(); //play once
			clip.loop(Clip.LOOP_CONTINUOUSLY); // repeat forever
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}