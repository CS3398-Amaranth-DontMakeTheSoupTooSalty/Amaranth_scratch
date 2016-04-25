package sound;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class Sound extends JFrame {
	Clip clip;
	//Default constructor loops LordOfTheLand.wav until song is changed
	 public Sound() {
		try {
			// Open an audio input stream.
			File soundFile = new File("bin/sound/LordOfTheLand.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource
			clip = AudioSystem.getClip();
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
	public Sound(String track) {
		try {
		File soundFile = new File("bin/sound/" + track + ".wav");
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
		}
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	//stopClip stops current song from playing
	public void stopClip() {
		clip.stop();
	}
	
	//newTrack to be used for changing current background song playing
	public void newTrack(String track) {
		stopClip();
		try {
			File soundFile = new File("bin/sound/" + track + ".wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		}
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}	
	}
	
	//playSound to be used for hits and sound effects
	public void playSound(String track) {
		try {
			File soundFile = new File("src/sound/" + track + ".wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}	
	}
}
