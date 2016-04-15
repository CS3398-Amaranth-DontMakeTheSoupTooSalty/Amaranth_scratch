package battle;

import java.io.FileNotFoundException;
import character.*;
import battle.attack;
import java.util.Scanner;
import java.util.Random;

public class battle {
	public static final int ENEMIES_VANQUISHED = 0;
	public static final int PLAYER_DEATH = 1;
	public static final int ERROR = -1;
	public static boolean validChoice = false;
	
	
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
		Random rand = new Random();
		int  randVal;
		
		while((playerChar.getHealth() > 0) && (remainingEnemies > 0)) {
			// input player move
			System.out.println("Choose your command:");
			System.out.println("(Type the first two letters of command name to select it)");
			System.out.println("(At)tack");
			System.out.println("(Bl)ock");
			System.out.println("(He)al");

			validChoice = false;
			while(!validChoice) {
				choice = choiceInput.next();
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
						
						validChoice = true;
						try {
						    Thread.sleep(500);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						break;
					
					case "bl":
						// if blocking, break
						if(playerChar.getBlocking() == true) {
							validChoice = true;
							break;
						}
						else
							defense.block(playerChar);
						
						validChoice = true;
						try {
						    Thread.sleep(500);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						break;
						
					case "he":
						// if blocking, remove block
						if(playerChar.getBlocking() == true)
							defense.removeBlock(playerChar);
						
						magic.heal(playerChar);
						validChoice = true;
						
						try {
						    Thread.sleep(500);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						break;
					
					default:
						System.out.println("Invalid choice - Try again");
						validChoice = false;
						
						try {
						    Thread.sleep(500);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						break;			
				}
			}
			
			// enemy move selection
			for(int i = 0; i < numEnemies; i++) {
				tempEnemy = enemyArray[i];
				tempEnemy.printEnemyStats();
				if(tempEnemy.getHealth() > 0) {
					if(tempEnemy.getBlocking() == true)
						defense.removeBlock(tempEnemy); // if blocking, remove block
					
					randVal = rand.nextInt(100) + 1; // generate random number (1-100)
					if((tempEnemy.getHealth() < 8) && ((randVal % 2) == 0)) {
						magic.heal(tempEnemy);
						System.out.println("Enemy " + (i+1) + " cast heal");
					}
					
					else if((randVal % tempEnemy.getBlockFactor()) == 0) {
						defense.block(tempEnemy);
						System.out.println("Enemy " + (i+1) + " is blocking");
					}
					
					else if((randVal % tempEnemy.getHealFactor()) == 0) {
						magic.heal(tempEnemy);
						System.out.println("Enemy " + (i+1) + " cast heal");
					}
					
					else {
						 returnVal = attack.attackUnarmed(tempEnemy, playerChar);
						 if(returnVal == attack.DAMAGE_DEALT)
							 System.out.println("Hit by Enemy " + (i+1));
						 else if(returnVal == attack.DEATH_BLOW)
							 System.out.println("Killed by Enemy " + (i+1));
						 else
							 System.out.println("Enemy " + (i+1) + " missed");
					}
					
					try {
					    Thread.sleep(500);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					} // delay after enemy move
				}
			}
			// display player stats
			playerChar.printStats();
		}
		choiceInput.close();
		
		if(remainingEnemies == 0)
			return ENEMIES_VANQUISHED;
		
		else if(playerChar.getHealth() < 1)
			return PLAYER_DEATH;
		
		else
			return ERROR;
	}
}
