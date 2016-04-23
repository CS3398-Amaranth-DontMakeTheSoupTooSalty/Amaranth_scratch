package character;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import SpriteSheet.Animator;
import SpriteSheet.BufferedImageLoader;
import SpriteSheet.GameWindow;
import SpriteSheet.SpriteSheet;
import charGen.charGen;

public class player extends character implements Serializable {
	
	// variables for animation
	public BufferedImage sprite;
	public ArrayList<BufferedImage> sprites;
	public SpriteSheet ss;
	public Animator avatar;
	public BufferedImageLoader loader;
	public BufferedImage spriteSheet;
	
	private static final long serialVersionUID = 1L;

	/*
	 * FUNCTION:
	 *   character()
	 * IN-PARAMETERS:
	 *   None
	 * OUT-PARAMETERS:
	 *   None
	 * DESCRIPTION:
	 *   Default constructor.
	 *   
	 *   Consider integrating a call to charGen or moving charGen
	 *   methods into this class or sub-classes.
	 */
	public player() {
		name  = "";
		health = 25;
		defense = 15;
		accuracy = 15;
		damage = 7;
		location = "START";
		blocking = false;
		
		// initialization of animation variables
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage spriteSheet = null;
		
		// set enemy graphic to idle and play
		try {
			spriteSheet = loader.loadImage("hero.png");
		} catch (IOException ex) {
			Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		ss = new SpriteSheet(spriteSheet);
		sprites = new ArrayList<BufferedImage>();
		
		// hero idle sequence
		sprites.add(ss.grabSprite(0, 220, 80, 68));
		
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
		
		// hero idle sequence
		sprites.add(ss.grabSprite(0, 220, 80, 68));
		
		avatar.play();
	}
	
	public void loadAttackFrames() {
		avatar.stop();
		sprites.clear();
		
		// hero attack sequence
		sprites.add(ss.grabSprite(0, 80, 80, 70));
		sprites.add(ss.grabSprite(0, 150, 80, 70));
		sprites.add(ss.grabSprite(0, 80, 80, 70));
		sprites.add(ss.grabSprite(0, 220, 80, 68)); // hero idle sequence
		sprites.add(ss.grabSprite(0, 220, 80, 68)); // hero idle sequence
		sprites.add(ss.grabSprite(0, 220, 80, 68)); // hero idle sequence
		sprites.add(ss.grabSprite(0, 220, 80, 68)); // hero idle sequence
		
		avatar.play();
	}
	
	public void loadBlockFrames() {
		avatar.stop();
		sprites.clear();
		
		// hero block sequence
		sprites.add(ss.grabSprite(0, 2, 80, 70));
		
		avatar.play();
	}
	
	public void loadHealFrames() {
		avatar.stop();
		sprites.clear();
		
		// hero idle sequence
		sprites.add(ss.grabSprite(0, 10, 80, 70));
		sprites.add(ss.grabSprite(0, 220, 80, 68));
		
		avatar.play();
	}
	
	public void loadHitFrames() {
		avatar.stop();
		sprites.clear();
		
		// hero idle sequence
		sprites.add(ss.grabSprite(0, 10, 80, 70));
		sprites.add(ss.grabSprite(0, 220, 80, 68));
		
		avatar.play();
	}
	
	public void loadDyingFrames() {
		avatar.stop();
		avatar = null;
	}
	
	public void printSimplePlayerStats() {
		System.out.println("Name: " + name + ", Health: " + health);
		System.out.println("************************");
	}
	
	public void statTransition( charGen.charStats playerCharacterType ){
		name = playerCharacterType.name;
		health = playerCharacterType.health;
		defense = playerCharacterType.defense;
		accuracy = playerCharacterType.accuracy;
		damage = playerCharacterType.damage;
	}
}