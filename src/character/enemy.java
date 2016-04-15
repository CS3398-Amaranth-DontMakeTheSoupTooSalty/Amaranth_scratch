package character;
import java.io.*;
import java.util.*;

public class enemy extends character implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String type;
	static int blockFactor;
	static int healFactor;
	public String getType() {return type;}
	public static int getBlockFactor() {return blockFactor;}
	public static int getHealFactor() {return healFactor;}	
	
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
	
	/********************************************************************
	 *  enemyGen()
	 * Given an enemy.txt file, this function will initialize the corresponding
	 * enemy object       */
	public static enemy enemyGen(String enemyFile) throws FileNotFoundException{
		enemy newEnemy = new enemy();
		String statDescriptor = "";
		
		java.io.File input = new java.io.File(enemyFile);
		Scanner infile = new Scanner(input);
		
		while(infile.hasNextLine())
		{
			statDescriptor = infile.next();
			System.out.println(statDescriptor);
			switch(statDescriptor)
			{
				case "name:":
					newEnemy.name = infile.next();
					infile.nextLine();
					break;
				case "type:":
					newEnemy.type = infile.next();
					infile.nextLine();
					break;
				case "location:":
					newEnemy.location = infile.next();
					infile.nextLine();
					break;
				case "health:":
					newEnemy.health = infile.nextInt();
					break;
				case "level:":
					newEnemy.level = infile.nextInt();
					break;
				case "damage:":
					newEnemy.damage = infile.nextInt();
					break;
				case "accuracy:":
					newEnemy.accuracy = infile.nextInt();
					break;
				case "defense:":
					newEnemy.defense = infile.nextInt();
					break;
			}
		}
		newEnemy.printEnemyStats();
		infile.close();
		return newEnemy;
	}
	
}