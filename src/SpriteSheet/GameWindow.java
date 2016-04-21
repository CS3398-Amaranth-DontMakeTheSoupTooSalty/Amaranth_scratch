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
	ArrayList<BufferedImage> sprites; // hero
	ArrayList<BufferedImage> spritesOrcHit; // orc hit
	ArrayList<BufferedImage> spritesOrcAttack; // orc attack
	ArrayList<BufferedImage> spritesOrcHeal; // orc hit
	SpriteSheet ss; // hero
	SpriteSheet ssOrc; // orc
	
	Animator dude;
	Animator enemy1;
	Animator enemy2;
	Animator enemy3;
	
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
		BufferedImage spriteSheetOrc = null;
		try {
			spriteSheet = loader.loadImage("AnimationSpriteSheet.png");
		} catch (IOException ex) {
			Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		try {
			spriteSheetOrc = loader.loadImage("orc.png");
		} catch (IOException ex) {
			Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		ss = new SpriteSheet(spriteSheet);
		
		sprites = new ArrayList<BufferedImage>();
		
		sprites.add(ss.grabSprite(0, 32, 32, 32));
		sprites.add(ss.grabSprite(32, 32, 32, 32));
		sprites.add(ss.grabSprite(64, 32, 32, 32));
		
		ssOrc = new SpriteSheet(spriteSheetOrc);
		
		spritesOrcHit = new ArrayList<BufferedImage>();
		spritesOrcAttack = new ArrayList<BufferedImage>();
		spritesOrcHeal = new ArrayList<BufferedImage>();
		
		// orc heal sequence
		spritesOrcHeal.add(ssOrc.grabSprite(25, 10, 75, 100));
		spritesOrcHeal.add(ssOrc.grabSprite(105, 10, 75, 100));
		spritesOrcHeal.add(ssOrc.grabSprite(190, 10, 75, 100));
		spritesOrcHeal.add(ssOrc.grabSprite(272, 10, 75, 100));
		
		// orc hit sequence
		spritesOrcHit.add(ssOrc.grabSprite(10, 1280, 100, 100));
		spritesOrcHit.add(ssOrc.grabSprite(240, 1280, 100, 100));
		spritesOrcHit.add(ssOrc.grabSprite(375, 1280, 100, 100));
		spritesOrcHit.add(ssOrc.grabSprite(500, 1280, 100, 100));
		spritesOrcHit.add(ssOrc.grabSprite(600, 1280, 100, 100)); // orc  idle
		
		// orc attack sequence
		spritesOrcAttack.add(ssOrc.grabSprite(10, 455, 100, 100));
		spritesOrcAttack.add(ssOrc.grabSprite(120, 455, 100, 100));
		spritesOrcAttack.add(ssOrc.grabSprite(220, 455, 100, 100));
		spritesOrcAttack.add(ssOrc.grabSprite(10, 830, 100, 100));
		//spritesOrcAttack.add(ssOrc.grabSprite(130, 830, 100, 100));
		
		enemy1 = new Animator(spritesOrcAttack);
		enemy1.setSpeed(200);
		enemy1.play();
		
		enemy2 = new Animator(spritesOrcHit);
		enemy2.setSpeed(200);
		enemy2.play();
		
		enemy3 = new Animator(spritesOrcHeal);
		enemy3.setSpeed(200);
		enemy3.play();
		
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
		
		if(enemy2 != null) {
			enemy2.update(System.currentTimeMillis());
			g.drawImage(enemy2.sprite, 160, 260, 150, 150, null);
		}
		if(enemy1 != null) {
			enemy1.update(System.currentTimeMillis());
			g.drawImage(enemy1.sprite, 220, 335, 160, 160, null);
		}
		if(enemy3 != null) {
			enemy3.update(System.currentTimeMillis());
			g.drawImage(enemy3.sprite, 75, 420, 150, 175, null);
		}
		
		if(dude != null) {
			dude.update(System.currentTimeMillis());
			g.drawImage(dude.sprite, x, y, 75, 75, null);
		}
		repaint();
	}

}
