package charGen;

import java.util.Scanner;
//Test for Eclipse Push
//Test Success if this goes out there -kam271
//This parent class will be the means by which NPCs and PCs are generated for the game world.
//This class is designed so that multiple future classes can utilize it's methods for generation
//of content based upon design team decisions - kam271
public class charGen {
	
	//CharStats handles Single Responsibility by separating the storage of the variabvles for the generic
	//class that will be used for generation of all PC and NPC types in the game - kam271
	//Class will hold a name for the entity, statistics for health (which @ 0, leads to death/removal of entity)
	//If this entity is the player, leads to end-game state. Defense and Accuracy will be directly opposed.
	//If Accuracy > Defense, it is considered a hit, with the entity being attacked taking damage. Reverss logic
	//is true. Damage will be applied if Accuracy > Defense condition is met, which will be subtracted from the health pool
	//This design is intentionally left open for expansion so that we can add additional stats if we believe resources
	//allow, based upon client feedback -kam271
	static class charStats{
		String name = "";
		int health = 20;
		int defense = 10;
		int accuracy = 10;
		int damage = 2;
		
		int statPoints = 20;
	}
	// Christinas comment section
	/*
	 * function Name: charStatPrint
	 * Description:  The Functio will display to the screen the the palyers current stats 
	 *               health, defense, accuracy, and Damage.
	 * variables: char stats, char objects
	 * input: none 
	 * output: current defense, health accuracy and damage
	 * return nothing 
	*/
	static void charStatPrint( charStats charObject ){
		System.out.println("Your current stats are: ");
		System.out.println("Health: " + charObject.health); 
		System.out.println("Defense: " + charObject.defense);
		System.out.println("Accuracy: " + charObject.accuracy);
		System.out.println("Damage: " + charObject.damage);
	}
	
	static void statAlloc( charStats charObject ){
		Scanner choiceInput = new Scanner ( System.in );
		
		while( charObject.statPoints > 0)
		{
			String choice = "";
			int statChoice = 0;
			System.out.println("You have " + charObject.statPoints + " points to allocate to your stats");
			System.out.println("Please choose a stat to add points to: ");
			System.out.println("(Type the first two letters of the stat name to select it)");
			charStatPrint( charObject );
			choice = choiceInput.nextLine();
			statAllocChoice( choice, charObject, statChoice );
/*			
			if( choice.equals("He") || choice.equals("he") )
			{
				System.out.println("How many points will you allocate to Health");
				System.out.println("You have " + charObject.statPoints + " available points to allocate");
				statChoice = choiceInput.nextInt();
				if (statChoice > charObject.statPoints)
					System.out.println("You can only allocate up to " + charObject.statPoints + " points" );
				else
				{
					charObject.health += statChoice;
					charObject.statPoints -= statChoice;
				}
			}
*/
		}
		
		System.out.println(charObject.name);
		charStatPrint( charObject );
		choiceInput.close();
	}
	
	/*	This helper function will be used when the player is awarded new skill points and must choose where 
	* 	to allocate them. By staying as simple as possible, this function conforms tp the SRP by ensuring that it's 
	*	sole purpose is to map user inputs into their character's stat values. - Noah Dunstatter
	*/
	static void statAllocChoice(String choice, charStats charObject, int statChoice)
	{
		if( choice.equals("He") || choice.equals("he") )
		{
			System.out.println("How many points will you allocate to Health");
			System.out.println("You have " + charObject.statPoints + " available points to allocate");
			statChoice = choiceInput.nextInt();
			if (statChoice > charObject.statPoints)
				System.out.println("You can only allocate up to " + charObject.statPoints + " points" );
			else
			{
				charObject.health += statChoice;
				charObject.statPoints -= statChoice;
			}
		}
		if( choice.equals("Da") || choice.equals("da") )
		{
			System.out.println("How many points will you allocate to Damage");
			System.out.println("You have " + charObject.statPoints + " available points to allocate");
			statChoice = choiceInput.nextInt();
			if (statChoice > charObject.statPoints)
				System.out.println("You can only allocate up to " + charObject.statPoints + " points" );
			else
			{
				charObject.damage += statChoice;
				charObject.statPoints -= statChoice;
			}
		}
		if( choice.equals("De") || choice.equals("de") )
		{
			System.out.println("How many points will you allocate to Defense");
			System.out.println("You have " + charObject.statPoints + " available points to allocate");
			statChoice = choiceInput.nextInt();
			if (statChoice > charObject.statPoints)
				System.out.println("You can only allocate up to " + charObject.statPoints + " points" );
			else
			{
				charObject.defense += statChoice;
				charObject.statPoints -= statChoice;
			}
		}
		if( choice.equals("Ac") || choice.equals("ac") )
		{
			System.out.println("How many points will you allocate to Accuracy");
			System.out.println("You have " + charObject.statPoints + " available points to allocate");
			statChoice = choiceInput.nextInt();
			if (statChoice > charObject.statPoints)
				System.out.println("You can only allocate up to " + charObject.statPoints + " points" );
			else
			{
				charObject.accuracy += statChoice;
				charObject.statPoints -= statChoice;
			}
		}
	}

	public static void main(String[] args){
		
		Scanner input = new Scanner( System.in );
		

		

		
		charGen.charStats playerCharacter = new charStats();
		
		System.out.println("What is your Character's Name?");
		playerCharacter.name = input.nextLine();
		System.out.println(playerCharacter.name);
		
		//Stat points to distribute 20
		System.out.println("You have 20 stat points to distribute. Your current stats are: ");
		
		//System.out.println("Health: " + playerCharacter.health);
		//System.out.println("Defense: " + playerCharacter.defense);
		//System.out.println("Accuracy: " + playerCharacter.accuracy);
		//System.out.println("Damage: " + playerCharacter.damage);
		charGen.charStatPrint( playerCharacter );
		
		charGen.statAlloc ( playerCharacter );
		
		input.close();
	}
}
