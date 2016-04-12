package character;
import java.io.*;
import java.util.*;
public class enemy extends character implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String type;
	public String getType() {return type;}
	
	
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
		
		/* call to charGen */
	}
	
	public void printEnemyStats() {
		System.out.println("************************");
		System.out.println("Enemy Print Stats: ");
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
	
	public static enemy enemyGen(String enemyFile) throws FileNotFoundException{
		enemy newEnemy = new enemy();
		String statDescriptor = "";
		
		java.io.File input = new java.io.File(enemyFile);
		Scanner infile = new Scanner(input);
		while(infile.hasNextLine())
		{
			statDescriptor = infile.nextLine();
			switch(statDescriptor)
			{
				case "name":
					
					newEnemy.name = infile.nextLine();
					break;
				case "type":
					newEnemy.type = infile.nextLine();
					break;
				case "location":
					newEnemy.location = infile.nextLine();
					break;
				case "health":
					newEnemy.health = infile.nextInt();
					infile.nextLine();
					break;
				case "level":
					newEnemy.level = infile.nextInt();
					infile.nextLine();
					break;
				case "damage":
					newEnemy.damage = infile.nextInt();
					infile.nextLine();
					break;
				case "accuracy":
					newEnemy.accuracy = infile.nextInt();
					infile.nextLine();
					break;
				case "defense":
					newEnemy.defense = infile.nextInt();
					infile.nextLine();
					break;
			}
			statDescriptor = "";
		}
		newEnemy.printEnemyStats();
		infile.close();
		return newEnemy; 
	}
	
}