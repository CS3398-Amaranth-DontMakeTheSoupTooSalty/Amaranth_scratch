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
	public static String[] choice = { "" };
	public static int[] enemyChoice = {0};
	public static boolean[] enemySelected = {false};
	
	public static int battleMode(player playerChar, int numEnemies, String enemyFile) {
		if((numEnemies > 4) || (numEnemies < 1))
			return ERROR;
		
		Sound sfx = new Sound("Kazoo");
		int remainingEnemies = numEnemies;
		enemy[] enemyArray = new enemy[numEnemies];
		try {
			enemyArray = enemy.enemyGen(enemyFile, numEnemies);
		}catch(FileNotFoundException e) {e.printStackTrace();}
		
		GameWindow gameWindow = new GameWindow(playerChar, enemyArray, numEnemies, choice, enemyChoice, enemySelected);
		
		Scanner choiceInput = new Scanner ( System.in );
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
			gameWindow.setMessageString(null); // blanks out message window
			gameWindow.setMoveSelect(true); // displays move select prompt

			choice[0] = "";
			validChoice = false;
			while(validChoice == false) {
				//choice[0] = choiceInput.next(); // gets choice from command line
				// call appropriate method
				switch(choice[0]) {
					case "at":
						gameWindow.setMoveSelect(false); // hide move select window
						playerChar.loadIdleFrames(); // removal of block animation
						// if blocking, remove block
						if(playerChar.getBlocking() == true)
							defense.removeBlock(playerChar);
			
//						System.out.println("Enter the number of the enemy you wish to attack"
//								+ "(1-" + numEnemies + "): ");
						//enemyChoice[0] = choiceInput.nextInt();
						gameWindow.setEnemySelect(true);
						while(enemySelected[0] == false) {
							tempEnemy = enemyArray[enemyChoice[0]];
							while(tempEnemy.getHealth() <= 0) {
								enemyChoice[0] = (enemyChoice[0]+1)%numEnemies;
								tempEnemy = enemyArray[enemyChoice[0]];
							} // handles player choosing a dead enemy
							try {
							    Thread.sleep(10);
							} catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							} // delay after player move
						}
						enemySelected[0] = false; // reset enemyChoice for next iteration
						gameWindow.setEnemySelect(false);
						
						playerChar.setXPosition(525);
						try {
						    Thread.sleep(300);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						playerChar.loadAttackFrames(); // attack animation
						sfx.playSound("Hit"); // player attack sound				
						returnVal = attack.attackUnarmed(playerChar, tempEnemy);
						
						if(returnVal == attack.DEATH_BLOW) {
							sfx.playSound("Die Voice");
							try {
							    Thread.sleep(100); // was 250
							} catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							} // delay after player move
							
							tempEnemy.loadDyingFrames();
							sfx.playSound("Enemy Die"); // enemy dying sound		
							System.out.println("Killed enemy " + (enemyChoice[0]+1));
							gameWindow.setMessageString("Killed enemy " + (enemyChoice[0]+1)); // battle window output
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
							System.out.println("Hit enemy " + (enemyChoice[0]+1) + " with " + returnVal + " damage");
							gameWindow.setMessageString("Hit enemy " + (enemyChoice[0]+1) + " with " + returnVal + " damage"); // window 
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
						
						break;
					
					case "bl":
						gameWindow.setMoveSelect(false); // hide move select window
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
						break;
						
					case "he":
						gameWindow.setMoveSelect(false); // hide move select window
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
						break;
					
					default:
						/* do nothing */
//						System.out.println("Invalid choice - Try again");
//						validChoice = false;
						choice[0] = "";
						try {
						    Thread.sleep(100);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						} // delay after player move
						break;			
				}
			}
			choice[0] = ""; // resets choice for next iteration
			
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
