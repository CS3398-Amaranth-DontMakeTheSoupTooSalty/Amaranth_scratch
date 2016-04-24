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
	
	// gui display components
	BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage spriteSheet = null;
	BufferedImage spriteSheetCursor = null;
	BufferedImage background = null;
	BufferedImage leftArrow = null;
	BufferedImage rightArrow = null;
	BufferedImage upArrow = null;
	BufferedImage downArrow = null;
	BufferedImage playerStatsWindow = null;
	BufferedImage messageWindow = null;
	BufferedImage moveSelectWindow = null;
	Font font1 = new Font("Arial", Font.BOLD, 27);
	Font font2 = new Font("Arial", Font.BOLD, 15);
	Font font3 = new Font("Arial", Font.BOLD, 20);
	String messageString = null;
	String playerStats = null;
	SpriteSheet ss;
	SpriteSheet ssCursor;
	boolean displayMoveSelect = false;
	int[] choiceArrowPosition = {490, 520, 550};
	int yPosition = 0;
	
	public void setMoveSelect(boolean boolVal) {
		displayMoveSelect = boolVal;
	}
	
	public void setMessageString(String string) {
		messageString = string;
	}
	
	public void setPlayerStats(String string) {
		playerStats = string;
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
				yPosition--;
				if(yPosition < 0)
					yPosition = 0;
			}
			if(keyCode == e.VK_DOWN) {
				yPosition++;
				if(yPosition > 2)
					yPosition = 2;
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
		
		// load gui components
		try {
			spriteSheet = bgLoader.loadImage("gui_game.png");
		} catch (IOException ex) {
			Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		ss = new SpriteSheet(spriteSheet);
		playerStatsWindow = ss.grabSprite(20, 20, 320, 40);
		messageWindow = ss.grabSprite(20, 70, 310, 135);
		moveSelectWindow = ss.grabSprite(20, 200, 310, 340);
		
		// load cursor components
		try {
			spriteSheetCursor = bgLoader.loadImage("cursor.png");
		} catch (IOException ex) {
			Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		ssCursor = new SpriteSheet(spriteSheetCursor);
		leftArrow = ssCursor.grabSprite(120, 38, 15, 20);
		rightArrow = ssCursor.grabSprite(153, 38, 15, 20);
		upArrow = ssCursor.grabSprite(135, 25, 20, 15);
		downArrow = ssCursor.grabSprite(135, 58, 20, 15);
				
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
		g.setColor(Color.BLACK);
		if(messageString != null) {
			g.setFont(font1);
			g.drawImage(messageWindow, 220, 515, 525, 80, null);
			g.drawString(messageString, 270, 570);
		}
		if(displayMoveSelect == true) {
			g.setFont(font3);
			g.drawImage(moveSelectWindow, 626, 455, 150, 140, null);
			g.drawString("Attack", 650, 512);
			g.drawString("Block", 650, 542);
			g.drawString("Heal", 650, 572);
			g.drawImage(rightArrow, 620, choiceArrowPosition[yPosition], 25, 30, null);
		}
		if(playerStats != null) {
			g.setFont(font2);
			g.drawImage(playerStatsWindow, 625, 448, 150, 50, null);
			g.drawString(playerStats, 635, 479);
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
