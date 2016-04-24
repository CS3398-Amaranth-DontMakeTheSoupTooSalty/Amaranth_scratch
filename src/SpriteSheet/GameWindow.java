package SpriteSheet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import character.*;


public class GameWindow extends JFrame {
	
	int numEnemies = 4;	
	enemy[] enemyArray = new enemy[numEnemies];
	enemy enemy1 = null;
	enemy enemy2 = null;
	enemy enemy3 = null;
	enemy enemy4 = null;
	player playerCharacter = null;
	BufferedImage background = null;
	Font font = new Font("Arial", Font.BOLD, 30);
	String string = null;
	
	public void setString(String _string) {
		string = _string;
	}
	
	public class AL extends KeyAdapter {
		@SuppressWarnings("static-access")
		
		//KeyEvent preE;
		
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if(keyCode == e.VK_LEFT) {
			
			}
			if(keyCode == e.VK_RIGHT) {
				
			}
			if(keyCode == e.VK_UP) {
				
			}
			if(keyCode == e.VK_DOWN) {
				
			}
		}
		
		public void keyReleased(KeyEvent e) {
		}
	}
	
	public GameWindow(player _playerCharacter, enemy[] _enemyArray, int _numEnemies) {
		numEnemies = _numEnemies;
		enemyArray = _enemyArray;
		playerCharacter = _playerCharacter;
		
		for(int i = 0; i < numEnemies; i++) {
			switch(i) {
				case 0: 
					enemy1 = enemyArray[0];
					break;
				
				case 1:
					enemy2 = enemyArray[1];
					break;
				
				case 2:
					enemy3 = enemyArray[2];
					break;
				
				case 3:
					enemy4 = enemyArray[3];
					break;
			}
		}
		
		// load background image		
		BufferedImageLoader bgLoader = new BufferedImageLoader(); 
		try {
			background = bgLoader.loadImage("woods.png");
		} catch (IOException ex) {
			Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		// battle window properties
		addKeyListener(new AL());
		setTitle("Battle Demo");
		setSize(800, 600);
		setResizable(false);
		setVisible(true);
		setBackground(Color.GREEN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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
		g.setColor(Color.DARK_GRAY);
		g.fillRect(275, 525, 450, 75);
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		if(string != null) {
			g.drawString(string, 300, 570);
		}
		if((enemy2 != null) && (enemy2.avatar != null)) {
			enemy2.avatar.update(System.currentTimeMillis());
			g.drawImage(enemy2.avatar.sprite, 160, 260, 150, 150, null);
		}
		if((enemy1 != null) && (enemy1.avatar != null)) {
			enemy1.avatar.update(System.currentTimeMillis());
			g.drawImage(enemy1.avatar.sprite, 220, 340, 160, 160, null);
		}
		if((enemy4 != null) && (enemy4.avatar != null)) {
			enemy4.avatar.update(System.currentTimeMillis());
			g.drawImage(enemy4.avatar.sprite, 75, 310, 155, 155, null);
		}
		if((enemy3 != null) && (enemy3.avatar != null)) {
			enemy3.avatar.update(System.currentTimeMillis());
			g.drawImage(enemy3.avatar.sprite, 75, 420, 175, 175, null);
		}
		
		if((playerCharacter != null) && (playerCharacter.avatar != null)) {
			playerCharacter.avatar.update(System.currentTimeMillis());
			g.drawImage(playerCharacter.avatar.sprite, playerCharacter.getXPosition(), 
					playerCharacter.getYPosition(), 150, 150, null);
		}
		repaint();
		
	}

}
