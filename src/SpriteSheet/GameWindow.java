package SpriteSheet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameWindow extends JFrame {
	
	int x, y;
	//int[] locationArray = new int[2];
	
	BufferedImage sprite;
	ArrayList<BufferedImage> sprites;
	SpriteSheet ss;
	
	Animator dude;
	BufferedImage background = null;
	
	public class AL extends KeyAdapter {
		@SuppressWarnings("static-access")
		
		KeyEvent preE;
		
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if(keyCode == e.VK_LEFT) {
				if(e != preE) {
					sprites.clear();
					sprites.add(ss.grabSprite(0, 32, 32, 32));
					sprites.add(ss.grabSprite(32, 32, 32, 32));
					sprites.add(ss.grabSprite(64, 32, 32, 32));
					dude.loadFrames(sprites);
					dude.resume();
				}
				
				if(x <= 5)
					x = 5;
				
				preE = e;
				x -=2 ;
			}
			if(keyCode == e.VK_RIGHT) {
				if(e != preE) {
					sprites.clear();
					sprites.add(ss.grabSprite(0, 64, 32, 32));
					sprites.add(ss.grabSprite(32, 64, 32, 32));
					sprites.add(ss.grabSprite(64, 64, 32, 32));
					dude.loadFrames(sprites);
					dude.resume();
				}
				
				if(x >= 725)
					x = 725;
				
				preE = e;
				x += 2;
			}
			if(keyCode == e.VK_UP) {
				if(e != preE) {
					sprites.clear();
					sprites.add(ss.grabSprite(0, 96, 32, 32));
					sprites.add(ss.grabSprite(32, 96, 32, 32));
					sprites.add(ss.grabSprite(64, 96, 32, 32));
					dude.loadFrames(sprites);
					dude.resume();
				}
				
				if(y <= 320)
					y = 320;
				
				preE = e;
				y -= 2;
			}
			if(keyCode == e.VK_DOWN) {
				if(e != preE) {
					sprites.clear();
					sprites.add(ss.grabSprite(0, 0, 32, 32));
					sprites.add(ss.grabSprite(32, 0, 32, 32));
					sprites.add(ss.grabSprite(64, 0, 32, 32));
					dude.loadFrames(sprites);
					dude.resume();
				}
				
				if(y >= 500)
					y = 500;
				
				preE = e;
				y += 2;
			}
			
		}
		
		public void keyReleased(KeyEvent e) {
			dude.stop();
			preE = null;
		}
	}
	
	public GameWindow() {
		// load background image		
		BufferedImageLoader bgLoader = new BufferedImageLoader(); 
		try {
			background = bgLoader.loadImage("woods.png");
		} catch (IOException ex) {
			Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		// game properties
		addKeyListener(new AL());
		setTitle("Battle Demo");
		setSize(800, 600);
		setResizable(false);
		setVisible(true);
		setBackground(Color.GREEN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		x = 650;
		y = 400;
				
		init();
	}
	
	private void init() {		
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage spriteSheet = null;
		try {
			spriteSheet = loader.loadImage("AnimationSpriteSheet.png");
		} catch (IOException ex) {
			Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		ss = new SpriteSheet(spriteSheet);
		
		sprites = new ArrayList<BufferedImage>();
		
		sprites.add(ss.grabSprite(0, 32, 32, 32));
		sprites.add(ss.grabSprite(32, 32, 32, 32));
		sprites.add(ss.grabSprite(64, 32, 32, 32));
		
		dude = new Animator(sprites);
		dude.setSpeed(200);
		dude.play();
		try {
		    Thread.sleep(300);                 
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		dude.stop();
	}
	
	Image dbImage;
	Graphics dbg;
	
	@Override
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, null);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 800, 600, null);
		if(dude != null) {
			dude.update(System.currentTimeMillis());
			g.drawImage(dude.sprite, x, y, 75, 75, null);
		}
		repaint();
	}

}
