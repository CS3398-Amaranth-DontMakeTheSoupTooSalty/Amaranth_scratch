package character;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import SpriteSheet.*;

import java.util.ArrayList;
import java.util.Random;

public class enemy extends character {
	
	String type;
	int blockFactor;
	int healFactor;
	public String getType() {return type;}
	public int getBlockFactor() {return blockFactor;}
	public int getHealFactor() {return healFactor;}
	
	// variables for animation
	public BufferedImage sprite;
	public ArrayList<BufferedImage> sprites;
	public SpriteSheet ss;
	public Animator avatar;
	public BufferedImageLoader loader;
	public BufferedImage spriteSheet;
	
	public enemy() {
		name = "";
		type = "";
		location = ""; 
		health = 0;
		defense = 0;
		accuracy = 0;
		damage = 0;
		level = 0;
		blocking = false;
		blockFactor = 5;
		healFactor = 13;
		
		// initialization of animation variables
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage spriteSheet = null;
		
		// set enemy graphic to idle and play
		try {
			spriteSheet = loader.loadImage("orc.png");
		} catch (IOException ex) {
			Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		ss = new SpriteSheet(spriteSheet);
		sprites = new ArrayList<BufferedImage>();
		
		// orc idle sequence
		sprites.add(ss.grabSprite(600, 1280, 100, 100));
		
		avatar = new Animator(sprites);
		avatar.setSpeed(200);;
		avatar.play();
		try {
		    Thread.sleep(300);                 
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	public void loadIdleFrames() {
		avatar.stop();
		sprites.clear();
		
		// orc idle sequence
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		
		avatar.play();
	}
	
	public void loadAttackFrames() {
		avatar.stop();
		sprites.clear();
		
		// orc attack sequence
		sprites.add(ss.grabSprite(10, 455, 100, 100));
		sprites.add(ss.grabSprite(120, 455, 100, 100));
		sprites.add(ss.grabSprite(220, 455, 100, 100));
		sprites.add(ss.grabSprite(10, 830, 100, 100));
		sprites.add(ss.grabSprite(220, 455, 100, 100));
		sprites.add(ss.grabSprite(120, 455, 100, 100));
		sprites.add(ss.grabSprite(10, 455, 100, 100));
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		
		avatar.play();
	}
	
	public void loadBlockFrames() {
		avatar.stop();
		sprites.clear();
		
		// orc blocking sequence
		sprites.add(ss.grabSprite(25, 1165, 75, 100));
		sprites.add(ss.grabSprite(105, 1165, 75, 100));
		sprites.add(ss.grabSprite(200, 1165, 75, 100));
		sprites.add(ss.grabSprite(285, 1165, 75, 100));
		sprites.add(ss.grabSprite(285, 1165, 75, 100));
		sprites.add(ss.grabSprite(285, 1165, 75, 100));
		
		avatar.play();
	}
	
	public void loadHealFrames() {
		avatar.stop();
		sprites.clear();
		
		// orc heal sequence
		sprites.add(ss.grabSprite(25, 10, 75, 100));
		sprites.add(ss.grabSprite(105, 10, 75, 100));
		sprites.add(ss.grabSprite(190, 10, 75, 100));
		sprites.add(ss.grabSprite(272, 10, 75, 100));
		sprites.add(ss.grabSprite(190, 10, 75, 100));
		sprites.add(ss.grabSprite(105, 10, 75, 100));
		sprites.add(ss.grabSprite(25, 10, 75, 100));
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		
		avatar.play();
	}
	
	public void loadHitFrames() {
		avatar.stop();
		sprites.clear();
		
		// orc hit sequence
		sprites.add(ss.grabSprite(10, 1280, 100, 100));
		sprites.add(ss.grabSprite(240, 1280, 100, 100));
		sprites.add(ss.grabSprite(375, 1280, 100, 100));
		sprites.add(ss.grabSprite(500, 1280, 100, 100));
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		sprites.add(ss.grabSprite(600, 1280, 100, 100)); // orc  idle
		
		avatar.play();
	}
	
	public void loadDyingFrames() {
		avatar.stop();
		sprites.clear();
		
		// orc dying sequence
		sprites.add(ss.grabSprite(10, 1280, 100, 100));
		sprites.add(ss.grabSprite(240, 1280, 100, 100));
		sprites.add(ss.grabSprite(485, 1043, 100, 100));
		sprites.add(ss.grabSprite(585, 1043, 100, 100));
		sprites.add(ss.grabSprite(585, 1043, 100, 100));
		sprites.add(ss.grabSprite(585, 1043, 100, 100));
		sprites.add(ss.grabSprite(585, 1043, 100, 100));
		sprites.add(ss.grabSprite(585, 1043, 100, 100));
		sprites.add(ss.grabSprite(585, 1043, 100, 100));
		
		avatar.play();
		try {
		    Thread.sleep(1500);                 
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		avatar = null;
	}
	
	public void printEnemyStats() {
		System.out.println("************************");
		System.out.println("Enemy Stats: ");
		System.out.println("Name: " + name);
		System.out.println("Type: " + type);
		System.out.println("Location: " + location);
		System.out.println("Level: " + level);
		System.out.println("Health: " + health); 
		System.out.println("Defense: " + defense);
		System.out.println("Accuracy: " + accuracy);
		System.out.println("Damage: " + damage);
		System.out.println("************************");
	}
	
	public void printSimpleEnemyStats() {
		System.out.println("Name: " + name + ", Health: " + health);
		System.out.println("************************");
	}
	
	/********************************************************************
	 *  enemyGen()
	 * Given an enemy.txt file, this function will initialize the corresponding
	 * enemy object       */
	public static enemy[] enemyGen(String enemyFile, int num_enemies) throws FileNotFoundException{
		enemy enemySquad[] = new enemy[num_enemies];
		for(int i = 0; i < num_enemies; i++)
		{
			
			enemy newEnemy = new enemy();
			String statDescriptor = "";
			
			java.io.File input = new java.io.File(enemyFile);
			Scanner infile = new Scanner(input);
			int iteration = 0;
			while(infile.hasNextLine())
			{
				if(iteration != 0)
					infile.nextLine();
				statDescriptor = infile.next();
				switch(statDescriptor)
				{
					case "name:":
						infile.skip(" "); //Skips the space between "name:" and the text in input file
						newEnemy.name = infile.nextLine();
						break;
					case "health:":
						newEnemy.health = infile.nextInt();
						break;
					case "defense:":
						newEnemy.defense = infile.nextInt();
						break;
					case "accuracy:":
						newEnemy.accuracy = infile.nextInt();
						break;
					case "damage:":
						newEnemy.damage = infile.nextInt();
						break;
					case "type:":
						infile.skip(" "); //Skips the space between "type:" and the text in input file
						newEnemy.type = infile.nextLine();
						break;
					case "location:":
						infile.skip(" "); //Skips the space between "location:" and the text in input file
						newEnemy.location = infile.nextLine();
						break;
					case "blockFactor:":
						newEnemy.blockFactor = infile.nextInt();
						break;
					case "healFactor:":
						newEnemy.healFactor = infile.nextInt();
						break;
					case "level:":
						newEnemy.level = infile.nextInt();
						break;
				}
				iteration++;
			}
			infile.close();
			enemySquad[i] = newEnemy;
		}
		return enemySquad;
	}
	
	// Based on a a random number, squadGen decides whether or not a battle will occur.
	// battle_thresh -> the % probability of a battle occuring (battle_thresh = 75 -> 75% chance of a fight)
	// If a fight occurs then the number of enemies will be set based on (randVal % 4) + 1
	// If a fight doesn't occur then the squad object is just set to null. This can be tested
	// by simply doing if(squadObj == null) 
	public static enemy[] squadGen(String enemyFile, int battle_thresh) throws FileNotFoundException{
		Random rand = new Random(); 
		int randVal = rand.nextInt(100) + 1;
		if (randVal > battle_thresh)
			return null;
		randVal = (randVal % 4) + 1; 
		enemy[] enemySquad = enemy.enemyGen(enemyFile, randVal); 
		
		return enemySquad;
	}
	
	
	
}