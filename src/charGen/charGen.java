package charGen;
import charGen.pointAllocator;
import java.util.Scanner;

/*Test for Eclipse Push
//Test Success if this goes out there -kam271
//This parent class will be the means by which NPCs and PCs are generated for the game world.
//This class is designed so that multiple future classes can utilize it's methods for generation
//of content based upon design team decisions - kam271*/

public class charGen {
	
	/*CharStats handles Single Responsibility by separating the storage of the variables for the generic
	//class that will be used for generation of all PC and NPC types in the game - kam271
	//Class will hold a name for the entity, statistics for health (which @ 0, leads to death/removal of entity)
	//If this entity is the player, leads to end-game state. Defense and Accuracy will be directly opposed.
	//If Accuracy > Defense, it is considered a hit, with the entity being attacked taking damage. Reverss logic
	//is true. Damage will be applied if Accuracy > Defense condition is met, which will be subtracted from the health pool
	//This design is intentionally left open for expansion so that we can add additional stats if we believe resources
	//allow, based upon client feedback -kam271*/
	static class charStats{
		String name = "";
		int health = 20;
		int defense = 10;
		int accuracy = 10;
		int damage = 2;
		
		int statPoints = 20;
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
		pointAllocator.charStatPrint( playerCharacter );
		
		pointAllocator.statAlloc ( playerCharacter );
		
		input.close();
	}
}
