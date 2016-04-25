package battle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import character.*;
import battle.attack;
import java.util.Scanner;
import java.util.Random;
import SpriteSheet.*;
import sound.*;



public class battle {
	public static final int ENEMIES_VANQUISHED = 0;
	public static final int PLAYER_DEATH = 1;
	public static final int ERROR = -1;
	public static boolean validChoice = false;
	int yPosition = 0;
	
	
	public static int battleMode(player playerChar, int numEnemies, String enemyFile) {
		if((numEnemies > 4) || (numEnemies < 1))
			return ERROR;
		
		Sound sfx = new Sound("Kazoo");
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
		gameWindow.setPlayerStats(playerChar.getName() + ": " + playerChar.getHealth() + "/25"); // print to playerStats
		
		while((playerChar.getHealth() > 0) && (remainingEnemies > 0)) {
			// input player move
			System.out.println("Choose your command:");
			System.out.println("(Type the first two letters of command name to select it)");
			System.out.println("(At)tack");
			System.out.println("(Bl)ock");
			System.out.println("(He)al");
			gameWindow.setMessageString(null);
			gameWindow.setMoveSelect(true);

			validChoice = false;
			while(!validChoice) {
				choice = choiceInput.next();
				// call appropriate method
				switch(choice.toLowerCase()) {
					case "at":
						playerChar.loadIdleFrames(); // removal of block animation
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
							
						
						playerChar.setXPosition(525);
						try {
						    Thread.sleep(300);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						playerChar.loadAttackFrames(); // attack animation
						sfx.playSound("Hit"); // player attack sound				
						returnVal = attack.attackUnarmed(playerChar, enemyArray[enemyChoice-1]);
						
						if(returnVal == attack.DEATH_BLOW) {
							sfx.playSound("Die Voice");
							try {
							    Thread.sleep(250);
							} catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							} // delay after player move
							
							tempEnemy.loadDyingFrames();
							sfx.playSound("Enemy Die"); // enemy dying sound		
							System.out.println("Killed enemy " + enemyChoice);
							gameWindow.setMessageString("Killed enemy " + enemyChoice); // battle window output
							remainingEnemies--;
						}
						
						else if(returnVal == attack.damageDealt) {
							try {
							    Thread.sleep(750);
							} catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							} // delay after player move
							
							tempEnemy.loadHitFrames(); // enemy hit animation
							sfx.playSound("Hit"); // enemy getting hit sound
							System.out.println("Hit enemy " + enemyChoice + " with " + returnVal + " damage");
							gameWindow.setMessageString("Hit enemy " + enemyChoice + " with " + returnVal + " damage"); // window 
						}
						
						validChoice = true;
						try {
						    Thread.sleep(500);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						
						playerChar.setXPosition(625);
						playerChar.loadIdleFrames();
						if(returnVal == attack.DEATH_BLOW) {
							// do nothing
						}
						else
							tempEnemy.loadIdleFrames(); // reset enemy to idle animation
						
						gameWindow.setMoveSelect(false);
						break;
					
					case "bl":
						// if blocking, break
						if(playerChar.getBlocking() == true) {
							validChoice = true;
							break;
						}
						else
							defense.block(playerChar);

						playerChar.loadBlockFrames(); // block animation
						sfx.playSound("Block"); // player blocking sound
						System.out.println("Player blocking");
						gameWindow.setMessageString("Player blocking"); // print to battle window 
						validChoice = true;
						try {
						    Thread.sleep(1000);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						gameWindow.setMoveSelect(false);
						break;
						
					case "he":
						playerChar.loadIdleFrames();
						// if blocking, remove block
						if(playerChar.getBlocking() == true)
							defense.removeBlock(playerChar);

						playerChar.loadHealFrames(); // heal animation
						sfx.playSound("Heal"); // player healing sound	
						magic.heal(playerChar);
						System.out.println("Player health + 4");
						gameWindow.setMessageString("Player health + 4"); // print to battle window 
						gameWindow.setPlayerStats(playerChar.getName() + ": " + playerChar.getHealth() + "/25"); // print playerStats
						validChoice = true;
						
						try {
						    Thread.sleep(1000);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						playerChar.loadIdleFrames();
						gameWindow.setMoveSelect(false);
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
					tempEnemy.loadIdleFrames(); // removal of block animation
					if(tempEnemy.getBlocking() == true)
						defense.removeBlock(tempEnemy); // if blocking, remove block
					
					randVal = rand.nextInt(100) + 1; // generate random number (1-100)
					if((tempEnemy.getHealth() < 8) && ((randVal % 2) == 0)) {
						tempEnemy.loadHealFrames(); // enemy heal animation
						sfx.playSound("Heal"); // enemy healing sound	
						magic.heal(tempEnemy);
						System.out.println("Enemy " + (i+1) + " cast heal");
						gameWindow.setMessageString("Enemy " + (i+1) + " cast heal"); // print to battle window 
					}
					
					else if((randVal % tempEnemy.getBlockFactor()) == 0) {
						tempEnemy.loadBlockFrames(); // enemy block animation
						sfx.playSound("Block"); // enemy blocking sound	
						defense.block(tempEnemy);
						System.out.println("Enemy " + (i+1) + " is blocking");
						gameWindow.setMessageString("Enemy " + (i+1) + " is blocking"); // print to battle window 
					}
					
					else if((randVal % tempEnemy.getHealFactor()) == 0) {
						tempEnemy.loadHealFrames(); // enemy heal animation
						sfx.playSound("Heal"); // enemy healing sound	
						magic.heal(tempEnemy);
						System.out.println("Enemy " + (i+1) + " cast heal");
						gameWindow.setMessageString("Enemy " + (i+1) + " cast heal"); // print to battle window 
					}
					
					else {
						 sfx.playSound("Hit"); // enemy attacking sound
						 tempEnemy.loadAttackFrames(); // enemy attack animation
						 try {
							    Thread.sleep(500);
							} catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							} // delay after enemy move
						 returnVal = attack.attackUnarmed(tempEnemy, playerChar);
						 if(returnVal == attack.damageDealt) {
							 playerChar.setXPosition(650);		
							 sfx.playSound("Enemy Hit"); // player getting hit sound	
							 playerChar.loadHitFrames(); // player hit animation
							 System.out.println("Hit by Enemy " + (i+1));
							 gameWindow.setMessageString("Hit by Enemy " + (i+1)); // print to battle window
						 } 
						 else if(returnVal == attack.DEATH_BLOW) {
							 System.out.println("Killed by Enemy " + (i+1));
							 gameWindow.setMessageString("Killed by Enemy " + (i+1)); // print to battle window
							 sfx.playSound("Player Die"); // player dying sound
							 playerChar.loadDyingFrames(); // player death animation
						 }
						 else {
							 System.out.println("Enemy " + (i+1) + " missed");
						 	 gameWindow.setMessageString("Enemy " + (i+1) + " missed"); // print to battle window
						 }
					}
					
					playerChar.setXPosition(625);
					try {
					    Thread.sleep(1000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					} // delay after enemy move
					if(tempEnemy.getBlocking() == false)
						tempEnemy.loadIdleFrames(); // enemy idle animation
					
					playerChar.loadIdleFrames(); // player idle animation
				}
			}
			// display player stats
			System.out.println("************************");
			System.out.println("Player Stats");
			if(playerChar.getHealth() <= 0) {
				System.out.println("DEAD");
				gameWindow.setMessageString("DEAD"); // print to battle window
			}
			else {
				playerChar.printSimplePlayerStats();
				gameWindow.setPlayerStats(playerChar.getName() + ": " + playerChar.getHealth() + "/25"); // print to playerStats
			}
		}
		choiceInput.close();
		
		if(remainingEnemies == 0) {
			gameWindow.setMessageString("Enemies vanquished!"); // print to battle window
			return ENEMIES_VANQUISHED;
		}
		
		else if(playerChar.getHealth() <= 0) {
			gameWindow.setMessageString("You died!"); // print to battle window
			return PLAYER_DEATH;
		}
		
		else
			return ERROR;
	}
}
