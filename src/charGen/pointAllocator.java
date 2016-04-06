package charGen;
import java.util.Scanner;
import charGen.charGen.charStats;

public class pointAllocator {
	/* Christinas comment section
	 * function Name: charStatPrint

	 * Description:  The Functio will display to the screen the the palyers current stats 
	 *               health, defense, accuracy, and Damage.
	 * variables: char stats, char objects
	 * input: none 
	 * output: current defense, health accuracy and damage
	 * return nothing 
	*/
	public static void charStatPrint( charStats charObject ){
		System.out.println("------------------------");
		System.out.println("Your current stats are: ");
		System.out.println("(He)alth: " + charObject.health); 
		System.out.println("(De)fense: " + charObject.defense);
		System.out.println("(Ac)curacy: " + charObject.accuracy);
		System.out.println("(Da)mage: " + charObject.damage);
		System.out.println("------------------------");
	}
	/*	This helper function will be used when the player is awarded new skill points and must choose where 
	* 	to allocate them. By staying as simple as possible, this function conforms tp the SRP by ensuring that it's 
	*	sole purpose is to map user inputs into their character's stat values. - Noah Dunstatter
	*/
	public static void statAllocChoice(String choice, charStats charObject, int statChoice, Scanner choiceInput )
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
	
	public static void statAlloc( charStats charObject ){
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
			statAllocChoice( choice, charObject, statChoice, choiceInput );
		}
		
		System.out.println(charObject.name);
		charStatPrint( charObject );
		choiceInput.close();
	}
}
