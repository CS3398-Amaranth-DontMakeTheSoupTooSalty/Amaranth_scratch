package character;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import SpriteSheet.*;

public class character implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	String name;
	String location;
	int health;
	int maxHealth;
	int defense;
	int accuracy;
	int damage;
	int level;
	boolean blocking;
	
	// variables for animation
//	BufferedImage sprite;
//	public ArrayList<BufferedImage> sprites;
//	public SpriteSheet ss;
//	public Animator avatar;
//	public BufferedImageLoader loader;
//	public BufferedImage spriteSheet;
	
	
	public String getName() {return name;}
	public String getLoc() {return location;}
	public int getHealth() {return health;}
	public int getMaxHealth() {return maxHealth;}
	public int getDefense() {return defense;}
	public int getAccuracy() {return accuracy;}
	public int getDamage() {return damage;}
	public int getLevel() {return level;}
	public void setHealth(int _health) {health = _health;}
	public void setMaxHealth(int _maxHealth) {maxHealth = _maxHealth;}
	public void setDefense(int _defense) {defense = _defense;}
	public void setAccuracy(int _accuracy) {accuracy = _accuracy;}
	public void setDamage(int _damage) {damage = _damage;}
	public boolean getBlocking() {return blocking;}
	public void setBlocking(boolean block) {blocking = block;}

	public character() {
		name = "";
		location = "";
		health = 0;
		maxHealth = 0;
		defense = 0;
		accuracy = 0;
		damage = 0;
		level = 1;
		blocking = false;
		
//		// initialization of animation variables
//		BufferedImageLoader loader = new BufferedImageLoader();
//		BufferedImage spriteSheet = null;
//		ss = new SpriteSheet(spriteSheet);
//		sprites = new ArrayList<BufferedImage>();
//		//avatar = new Animator(sprites);
//		//avatar.setSpeed(200);;
//		//avatar.play();
	}
	
//Change to only take five lines
//Delete second set of stars
	public void printStats() {
		System.out.println("************************");
		System.out.println("Character Print Stats: ");
		System.out.println("Name: " + name +"  Health: " + health +"  Defense: " + defense);
		System.out.println("Accuracy: " + accuracy + "  Damage: " + damage);
	}
}