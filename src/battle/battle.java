package battle;

import java.io.FileNotFoundException;
import character.*;
import battle.attack;
import java.util.Scanner;

public class battle {
	public static final int ENEMIES_VANQUISHED = 0;
	public static final int PLAYER_DEATH = 1;
	public static final int ERROR = -1;
	
	
	public static int battleMode(player playerChar, int numEnemies, String enemyFile) {
		if((numEnemies > 4) || (numEnemies < 1))
			return ERROR;
		
		int remainingEnemies = numEnemies;
		enemy[] enemyArray = new enemy[numEnemies];
		
		for(int i = 0; i < numEnemies; i++) {
			enemy newEnemy = new enemy();
			try {
				newEnemy = enemy.enemyGen(enemyFile);
			}catch(FileNotFoundException e) {e.printStackTrace();}
			
			enemyArray[i] = newEnemy;
		} // populates an array of enemy characters
		
		Scanner choiceInput = new Scanner ( System.in );
		String choice = "";
		int enemyChoice;
		int returnVal;
		enemy tempEnemy = new enemy();
		
		while((playerChar.getHealth() > 0) && (remainingEnemies > 0)) {
			// display player stats
			playerChar.printStats();
			// input player move
			System.out.println("Choose your command:");
			System.out.println("(Type the first two letters of command name to select it)");
			System.out.println("(At)tack");
			System.out.println("(Bl)ock");
			System.out.println("(He)al");
			choice = choiceInput.nextLine();			
			// call appropriate method
			switch(choice.toLowerCase()) {
				case "at":
					// if blocking, remove block
					if(playerChar.getBlocking() == true)
						defense.removeBlock(playerChar);
			
					System.out.println("Enter the number of the enemy you wish to attack"
							+ "(1-" + numEnemies + "): ");
					enemyChoice = choiceInput.nextInt();
					returnVal = attack.attackUnarmed(playerChar, enemyArray[enemyChoice-1]);
					
					if(returnVal == attack.DEATH_BLOW)
						remainingEnemies--;
					break;
				
				case "bl":
					// if blocking, break
					if(playerChar.getBlocking() == true)
						break;
					else
						defense.block(playerChar);
					break;
					
				case "he":
					// if blocking, remove block
					if(playerChar.getBlocking() == true)
						defense.removeBlock(playerChar);
					
					magic.heal(playerChar);
					break;
				
				default:
					System.out.println("Invalid choice - You lost your turn!");
					break;			
			}
			
			for(int i = 0; i < numEnemies; i++) {
				tempEnemy = enemyArray[i];
				tempEnemy.printEnemyStats();
				attack.attackUnarmed(enemyArray[i], playerChar);
			}
		}
		choiceInput.close();
		
		if(numEnemies == 0)
			return ENEMIES_VANQUISHED;
		
		else if(playerChar.getHealth() < 1)
			return PLAYER_DEATH;
		
		else
			return ERROR;
	}
}
