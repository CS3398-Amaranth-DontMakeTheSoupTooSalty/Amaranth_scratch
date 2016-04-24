package battle;

import java.io.FileNotFoundException;
import character.*;
import battle.attack;
import java.util.Scanner;
import java.util.Random;
import SpriteSheet.*;

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
		try {
			enemyArray = enemy.enemyGen(enemyFile, numEnemies);
		}catch(FileNotFoundException e) {e.printStackTrace();}
		
		GameWindow gameWindow = new GameWindow(playerChar, enemyArray, numEnemies);
		
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
						playerChar.loadIdleFrames();
						// if blocking, remove block
						if(playerChar.getBlocking() == true)
							defense.removeBlock(playerChar);
			
							System.out.println("Enter the number of the enemy you wish to attack"
									+ "(1-" + numEnemies + "): ");
							enemyChoice = choiceInput.nextInt();
							tempEnemy = enemyArray[enemyChoice-1];
							if(tempEnemy.getHealth() <= 0) {
								tempEnemy = enemyArray[(enemyChoice++)%numEnemies];
							} // handles player choosing a dead enemy
							
						
						playerChar.loadAttackFrames(); // attack animation
						
						returnVal = attack.attackUnarmed(playerChar, enemyArray[enemyChoice-1]);
						
						if(returnVal == attack.DEATH_BLOW) {
							try {
							    Thread.sleep(500);
							} catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							} // delay after player move
							
							tempEnemy.loadDyingFrames();
							System.out.println("Killed enemy " + enemyChoice);
							remainingEnemies--;
						}
						
						else if(returnVal == attack.damageDealt) {
							try {
							    Thread.sleep(500);
							} catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							} // delay after player move
							
							tempEnemy.loadHitFrames();
							System.out.println("Hit enemy " + enemyChoice + " with " + returnVal + " damage");
						}
						
						validChoice = true;
						try {
						    Thread.sleep(500);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						
						playerChar.loadIdleFrames();
						if(returnVal == attack.DEATH_BLOW) {
							// set enemy avatar to null
						}
						else
							tempEnemy.loadIdleFrames();
						
						break;
					
					case "bl":
						// if blocking, break
						if(playerChar.getBlocking() == true) {
							validChoice = true;
							break;
						}
						else
							defense.block(playerChar);
						
						playerChar.loadBlockFrames();
						System.out.println("Player blocking");
						validChoice = true;
						try {
						    Thread.sleep(1000);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						break;
						
					case "he":
						playerChar.loadIdleFrames();
						// if blocking, remove block
						if(playerChar.getBlocking() == true)
							defense.removeBlock(playerChar);
						
						playerChar.loadHealFrames();
						magic.heal(playerChar);
						System.out.println("Player health + 4");
						validChoice = true;
						
						try {
						    Thread.sleep(1000);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						playerChar.loadIdleFrames();
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
				System.out.println("************************");
				System.out.println("Enemy " + (i+1) + " - ");
				if(tempEnemy.getHealth() <= 0)
					System.out.println("DEAD");
				else
					tempEnemy.printSimpleEnemyStats();
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
						 if(returnVal == attack.damageDealt)
							 System.out.println("Hit by Enemy " + (i+1));
						 else if(returnVal == attack.DEATH_BLOW)
							 System.out.println("Killed by Enemy " + (i+1));
						 else
							 System.out.println("Enemy " + (i+1) + " missed");
					}
					
					try {
					    Thread.sleep(1000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					} // delay after enemy move
				}
			}
			// display player stats
			System.out.println("************************");
			System.out.println("Player Stats");
			if(playerChar.getHealth() <= 0)
				System.out.println("DEAD");
			else
				playerChar.printSimplePlayerStats();
		}
		choiceInput.close();
		
		if(remainingEnemies == 0)
			return ENEMIES_VANQUISHED;
		
		else if(playerChar.getHealth() <= 0)
			return PLAYER_DEATH;
		
		else
			return ERROR;
	}
}
