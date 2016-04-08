package mainDriver;

import java.util.Scanner;

import charGen.charGen;
import charGen.pointAllocator;
import charGen.charGen.charStats;
import character.character;
import character.player;
import environment.start;

public class mainDriver {
	public static void main(String[] args){
	
		Scanner input = new Scanner( System.in );
		//Allows user to choose between a CLI or a GUI
		start startObj = new start();
	    startObj.interfaceChoice(input);
	    if(startObj.interfaceChoice == 2)
	    	//yourGuiCodeHere
	    	System.out.println(startObj.interfaceChoice);
	    //Star a NewGame or LoadGame
	    start.NewGameQ(input);
		//Create a player object
		player playerCharacter = new player();
		//Create a charGen oobject
		charGen objectCharGen = new charGen();
		//Generic holder instantiation for character stats
		//Makes easier for us to pass as object to do stat manipulation
		charGen.charStats playerCharacterHolder = new charStats();
		//User chooses name for character. Separated intentionally from
		//other stat allocation.
		objectCharGen.charName( playerCharacterHolder, input );
		//Shows current default stats
		pointAllocator.charStatPrint( playerCharacterHolder );
		//Handles the allocation of available character creation stat points
		//prompting user to make choices on how they want to alloc their stats
		pointAllocator.statAlloc ( playerCharacterHolder );
		//Takes the place holder object stats and links it to playerChar object
		playerCharacter.statTransition( playerCharacterHolder );
		//print final stat choices to char so they see the results of their choices
		//Function can be used, when called to do so, to allow player to see their
		//current stats during play period
		playerCharacter.printStats();
		
		input.close();
	}
}
