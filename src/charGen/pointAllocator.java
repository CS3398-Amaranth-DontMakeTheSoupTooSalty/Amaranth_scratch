package charGen;
import java.util.Scanner;
import charGen.charGen.charStats;

public class pointAllocator {
	
	static void charStatPrint( charStats charObject ){
		System.out.println("Your current stats are: ");
		System.out.println("Health: " + charObject.health); 
		System.out.println("Defense: " + charObject.defense);
		System.out.println("Accuracy: " + charObject.accuracy);
		System.out.println("Damage: " + charObject.damage);
	}
	
	static void statAllocChoice(String choice, charStats charObject, int statChoice, Scanner choiceInput )
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
			statAllocChoice( choice, charObject, statChoice, choiceInput );
		}
		
		System.out.println(charObject.name);
		charStatPrint( charObject );
		choiceInput.close();
	}
}
