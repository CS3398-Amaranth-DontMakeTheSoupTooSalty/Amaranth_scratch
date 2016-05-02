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
import battle.*;

public class GameWindow extends JFrame {
	
	String[] choice = {""};
	
	int numEnemies = 5;	
	enemy[] enemyArray = new enemy[numEnemies];
//	enemy enemy1 = null;
//	enemy enemy2 = null;
//	enemy enemy3 = null;
//	enemy enemy4 = null;
//	boss enemy5 = null;
	player playerCharacter = null;
	enemy tempEnemy = null;
	boolean[] bossBattle = {false};
	
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
	boolean displayEnemySelect = false;
	int[] choiceArrowPosition = {490, 520, 550}; // attack, block, heal
	int[][] enemySelect = {
			{330, 265, 195, 185},
			{330, 220, 430, 290}
	}; /* 330, 400 - enemy 1
		  265, 320 - enemy 2
		  195, 480 - enemy 3
		  185, 370 - enemy 4 */
	int yPosition = 0;
	int[] enemyChoice = {0};
	boolean[] enemySelected = {false};
	
	public void setYPosition(int value) {
		yPosition = value;
	}
	
	public void setMoveSelect(boolean boolVal) {
		displayMoveSelect = boolVal;
	}
	
	public void setEnemySelect(boolean value) {
		displayEnemySelect = value;
	}
	
	public void setMessageString(String string) {
		messageString = string;
	}
	
	public void setPlayerStats(String string) {
		playerStats = string;
	}
	
	public class AL extends KeyAdapter {
		@SuppressWarnings("static-access")
		
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if(keyCode == e.VK_LEFT) {
				
			}
			if(keyCode == e.VK_RIGHT) {
				
			}
			if(keyCode == e.VK_UP) {
				if(displayMoveSelect == true) {
					yPosition--;
					if(yPosition < 0)
						yPosition = 0;
				}
				else if(displayEnemySelect == true) {
					enemyChoice[0] = (enemyChoice[0]+1)%numEnemies;
				}
			}
			if(keyCode == e.VK_DOWN) { //|| keyCode == e.VK_END
				if(displayMoveSelect == true) {
					yPosition++;
					if(yPosition > 2)
						yPosition = 2;
				}
				else if(displayEnemySelect == true) {
					if(enemyChoice[0] == 0) {
						enemyChoice[0] = numEnemies - 1;
						tempEnemy = enemyArray[enemyChoice[0]];
						while(tempEnemy.getHealth() <= 0) {
							enemyChoice[0] = enemyChoice[0] - 1;
							if(enemyChoice[0] <= 0)
								enemyChoice[0] = numEnemies - 1;
							tempEnemy = enemyArray[enemyChoice[0]];
						}
					}					
					else {
						enemyChoice[0] = enemyChoice[0] - 1;
						tempEnemy = enemyArray[enemyChoice[0]];
						while(tempEnemy.getHealth() <= 0) {
							enemyChoice[0] = enemyChoice[0] - 1;
							if(enemyChoice[0] <= 0)
								enemyChoice[0] = numEnemies - 1;
							tempEnemy = enemyArray[enemyChoice[0]];
						}
					}
				}
			}
			if(keyCode == e.VK_ENTER) {
				if(displayMoveSelect == true) {
					switch(yPosition){
						case 0: 
							choice[0] = "at";
							displayMoveSelect = false;
							break;
							
						case 1:
							choice[0] = "bl";
							displayMoveSelect = false;
							break;
							
						case 2:
							choice[0] = "he";
							displayMoveSelect = false;
							break;
					}
				}
				else if(displayEnemySelect == true) {
					enemySelected[0] = true;
					try {
					    Thread.sleep(10);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					} // delay after player move
				}
			}
		}
		
		public void keyReleased(KeyEvent e) {
		}
	}
	
	public GameWindow(player _playerCharacter, enemy[] _enemyArray, int _numEnemies, String[] _choice, 
			int[] _enemyChoice, boolean[] _enemySelected, boolean[] _bossBattle) {
	
		numEnemies = _numEnemies;
		enemyArray = _enemyArray;
		playerCharacter = _playerCharacter;
		choice = _choice;
		enemyChoice = _enemyChoice;
		enemySelected = _enemySelected;
		bossBattle = _bossBattle;	
		
//		if(bossBattle[0] == true) {
//			boss enemy1 = null;
//		}
//		else {
//			enemy enemy1 = null;
//		}
//		enemy enemy1 = null;
//		enemy enemy2 = null;
//		enemy enemy3 = null;
//		enemy enemy4 = null;
		
//		for(int i = 0; i < numEnemies; i++) {
//			switch(i) {
//				case 0: 
//					if(bossBattle[0] == true)
//						enemy1 = (boss) enemyArray[0];
//					else
//						enemy1 = enemyArray[0];
//					break;
//				
//				case 1:
//					enemy2 = enemyArray[1];
//					break;
//				
//				case 2:
//					enemy3 = enemyArray[2];
//					break;
//				
//				case 3:
//					enemy4 = enemyArray[3];
//					break;
//			}
//		}
		
//		if(bossBattle[0] == true) {
//			boss evilBoss = new boss();
//			enemyArray[0] = evilBoss;
//		}
		
		// load background image		
		BufferedImageLoader bgLoader = new BufferedImageLoader(); 
		try {
			background = bgLoader.loadImage("forest_light.png");
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
		setResizable(true);
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
		g.setFont(font1);
		if(messageString != null) {
			g.drawImage(messageWindow, 220, 515, 525, 80, null);
			if(messageString == null) {
				g.drawImage(background, 0, 0, 800, 600, null);
				messageString = ""; 
			} // prevents null pointer error
			g.drawString(messageString, 270, 570);
		}
		g.setFont(font3);
		if(displayMoveSelect == true) {
			g.drawImage(moveSelectWindow, 626, 455, 150, 140, null);
			g.drawString("Attack", 650, 512);
			g.drawString("Block", 650, 542);
			g.drawString("Heal", 650, 572);
			g.drawImage(rightArrow, 620, choiceArrowPosition[yPosition], 25, 30, null);
		}
		g.setFont(font2);
		if(playerStats != null) {
			g.drawImage(playerStatsWindow, 625, 448, 150, 50, null);
			g.drawString(playerStats, 635, 479);
		}
		if((numEnemies >= 2) && (enemyArray[1] != null) && (enemyArray[1].avatar != null)) { // enemy2
			enemyArray[1].avatar.update(System.currentTimeMillis());
			g.drawImage(enemyArray[1].avatar.sprite, 110, 110, 200, 200, null);
		}
		if((numEnemies >= 1) && (enemyArray[0] != null) && (enemyArray[0].avatar != null)) { // enemy1
			enemyArray[0].avatar.update(System.currentTimeMillis());
			g.drawImage(enemyArray[0].avatar.sprite, 170, 220, 210, 210, null);
		}
		if((numEnemies == 4) && (enemyArray[3] != null) && (enemyArray[3].avatar != null)) { // enemy4
			enemyArray[3].avatar.update(System.currentTimeMillis());
			g.drawImage(enemyArray[3].avatar.sprite, 25, 180, 205, 205, null);
		}
		if((numEnemies >= 3) && (enemyArray[2] != null) && (enemyArray[2].avatar != null)) { // enemy3
			enemyArray[2].avatar.update(System.currentTimeMillis());
			g.drawImage(enemyArray[2].avatar.sprite, 25, 320, 225, 225, null);
		}
		if(displayEnemySelect == true) {
			g.drawImage(leftArrow, enemySelect[0][enemyChoice[0]], enemySelect[1][enemyChoice[0]], 25, 30, null);
		}
		
		if((playerCharacter != null) && (playerCharacter.avatar != null)) {
			playerCharacter.avatar.update(System.currentTimeMillis());
			g.drawImage(playerCharacter.avatar.sprite, playerCharacter.getXPosition(), 
					playerCharacter.getYPosition(), 225, 225, null);
		}
		repaint();
		
	}

}
