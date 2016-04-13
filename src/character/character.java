package character;

import java.io.Serializable;

public class character implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	String name;
	String location;
	int health;
	int defense;
	int accuracy;
	int damage;
	int level;
	boolean blocking;
	
	public String getName() {return name;}
	public String getLoc() {return location;}
	public int getHealth() {return health;}
	public int getDefense() {return defense;}
	public int getAccuracy() {return accuracy;}
	public int getDamage() {return damage;}
	public int getLevel() {return level;}
	public void setHealth(int _health) {health = _health;}
	public void setDefense(int _defense) {defense = _defense;}
	public void setAccuracy(int _accuracy) {accuracy = _accuracy;}
	public void setDamage(int _damage) {damage = _damage;}
	public boolean getBlocking() {return blocking;}
	public void setBlocking(boolean block) {blocking = block;}

	public character() {
		name = "";
		location = "";
		health = 0;
		defense = 0;
		accuracy = 0;
		damage = 0;
		level = 0;
		blocking = false;
		
		/* call to charGen */
	}
	
	
	public void printStats() {
		System.out.println("************************");
		System.out.println("Character Print Stats: ");
		System.out.println("Name: " + name);
		System.out.println("Health: " + health); 
		System.out.println("Defense: " + defense);
		System.out.println("Accuracy: " + accuracy);
		System.out.println("Damage: " + damage);
		System.out.println("************************");
	}
}