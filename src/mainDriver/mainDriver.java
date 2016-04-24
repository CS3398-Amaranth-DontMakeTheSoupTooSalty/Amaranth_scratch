package mainDriver;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;
import javax.sound.sampled.Clip;
import charGen.charGen;
import charGen.pointAllocator;
import charGen.charGen.charStats;
import character.*;
import character.player;
import environment.gameWorld;
import environment.start;
import gui.Frame;
import sound.Sound;
import battle.*;
import saveGameState.*;
import Inventory.*;
//import spriteAnimation.*;
import SpriteSheet.*;

public class mainDriver {
	
	public static void main(String[] args) {
	
		new Sound();
		
		Scanner input = new Scanner( System.in );
		//Allows user to choose between a CLI or a GUI
		start startObj = new start();

	    startObj.interfaceChoice(input);
	    if(startObj.interfaceChoice == 2){
	    	Frame objGui = new Frame();
	    	objGui.launchGUI();
	    }
	    else if(startObj.interfaceChoice == 1){
	    	
	    	//If player selects a new game
	    	if( start.NewGameQ(input) )
	    	{
	    		//Create a player object
	    		player playerCharacter = new player();
	    		//Create a charGen object
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
	    		serialSave.saveChar(playerCharacter);
	    		
	    		/* send character to game driver */
	    	}
	    	else // User wants to load game
	    	{
	    		player playerCharacter = new player();
	    		playerCharacter = serialSave.loadChar();
	    		/* send character to game driver */
	    		
	    	}
	    }
	    else if(startObj.interfaceChoice == 3)
	    {
	    	
	    	
	    	try {
	    		enemy[] dudes; 
	    		dudes = enemy.squadGen("forestOrc.txt", 75);
	    		if(dudes == null)
	    			System.out.println("No Battle :(");
	    		else
	    		{
	    			System.out.println("BATTLE!");
		    		int size = Array.getLength(dudes);
		    		for(int i = 0; i < size; i++)
		    			dudes[i].printEnemyStats();
	    		}
			}catch(FileNotFoundException e) {e.printStackTrace();}
	    	/*potion newPotion = new potion();
	    	try {
				newPotion = potion.potionGen("healthPotion.txt");
			}catch(FileNotFoundException e) {e.printStackTrace();}
	    	newPotion.printDetailed();*/
	    }
	    	
	    else if(startObj.interfaceChoice == 4) {
	    	int numEnemies;
	    	player playerCharacter = new player();
	    	int returnVal;
	    	
	    	System.out.println("How many enemies do you want to battle?");
	    	System.out.println("Enter 1-4: ");
	    	numEnemies = input.nextInt();
	    	returnVal = battle.battleMode(playerCharacter, numEnemies, "forestOrc.txt");
	    	if(returnVal == battle.PLAYER_DEATH)
	    		System.out.println("You died!");
	    	else if(returnVal == battle.ENEMIES_VANQUISHED)
	    		System.out.println("You have vanquished all your enemies!");
	    	else if(returnVal == battle.ERROR)
	    		System.out.println("Oh no, something went wrong!");
	    }
	    
	    //TEST BLOCK, TO BE REVISED FOR FILE SYSTEM
	    String testEnvironDesc = "FOREST the thousand acre woods";
	    String testEnvironName ="START";
	    gameWorld gameWorldObj = new gameWorld();
	    gameWorldObj.environmentGeneratorCLI(testEnvironName, testEnvironDesc);
	    Scanner inputA = new Scanner( System.in );
	    environment.gameWorld.environmentCreatorCLI(inputA);
	    
	    
		input.close();
	}
} // comment for sync push
