package SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
	
	private ArrayList<BufferedImage> frames;
	
	public BufferedImage sprite;
	
	private volatile boolean running = false;
	private long previousTime, speed;
	private int frameAtPause, currentFrame;
	
	
	public Animator(ArrayList<BufferedImage> frames) {
		this.frames = frames;
	}
	
	public void loadFrames(ArrayList<BufferedImage> frames) {
		this.frames = frames;
	}
	
	public void setSpeed(long speed) {
		this.speed = speed;
	}
	
	public void update(long time) {
		if(running) {
			if(time - previousTime >= speed) {
				// Update the animation
				currentFrame++;
				try {
					sprite = frames.get(currentFrame);
				} catch(IndexOutOfBoundsException e) {
					currentFrame = 0;
					sprite = frames.get(currentFrame);
				}
				//locationArray[0] = (locationArray[0]+3) % 100;
				//locationArray[1] = (locationArray[1]+3) % 100;
				previousTime = time;
			}
		}
	}
	
	public void play() {
		running = true;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}
	
	public void stop() {
		running = false;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}
	
	public void pause() {
		frameAtPause = currentFrame;
		running = false;
	}
	
	public void resume() {
		//currentFrame = frameAtPause;
		running = true;
	}
}