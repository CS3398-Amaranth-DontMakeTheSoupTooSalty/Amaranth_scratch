package character;

//import charGen.*;

public class character {
	
	String name;
	int health;
	int defense;
	int accuracy;
	int damage;
	
	public String getName() {return name;}
	public int getHealth() {return health;}
	public int getDefense() {return defense;}
	public int getAccuracy() {return accuracy;}
	public int getDamage() {return damage;}
	
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
	public character() {
		name = "";
		health = 0;
		defense = 0;
		accuracy = 0;
		damage = 0;
		
		/* call to charGen */
	}
	
	/*
	 * FUNCTION:
	 *   printStats()
	 * IN-PARAMETERS:
	 *   None
	 * OUT-PARAMETERS:
	 *   None
	 * DESCRIPTION:
	 *   Helper function to print stats of a character object.
	 */
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