package battle;

import character.*;
import java.util.Random;

public class attack {
	public static final int ATTACK_MISSED = -1;
	public static final int DEATH_BLOW = -2;
	public static final int ERROR = -3;
	public static final int missBase = 10;
	public static int damageDealt;
	public static int missFactor;
	public static int qualityFactor;
	
	// FUNCTION:
	//  int attackUnarmedcharacter attacker, character defender)
	//IN-PARAMETERS:
	//  attacker (character), defender (character)
	//OUT-PARAMETERS:
	//  int
	//DESCRIPTION:
	//  This method allows one character to attack another character
	//  The attacker and defender can be either a player or enemy
	//  type character. An attack is valid and damage is dealt if
	//  the attacker's accuracy stat is greater than the defeneder's 
	//  defense stat. Otherwise, the attack is a miss. If either the
	//  attacker or defender has health less than 1, the method will
	//  return -1. If the attack is a hit, 0 is returned, if it is a
	//  miss, 1 is returned, and if it results in the defender's 
	//  death, 2 is returned.
	public static int attackUnarmed(character attacker, character defender) {
		if((attacker.getHealth() < 1) || (defender.getHealth() < 1))
			return ERROR;
		
		Random rand = new Random();
		int  randVal;
		randVal = rand.nextInt(10) + 1; // generate random number (1-100)
		
		if(attacker.getAccuracy() > defender.getDefense()) {
			qualityFactor = (attacker.getAccuracy() - defender.getDefense())/5;
			missFactor = missBase + (attacker.getAccuracy() - defender.getDefense());
		}
		
		else {
			missFactor = missBase - (defender.getDefense() - attacker.getAccuracy())/5;
			qualityFactor = 0;
		}
			
		if(missFactor <= 0)
			missFactor = 1; // ensures no divide by 0
		
		if((randVal % missFactor) == 0)
			return ATTACK_MISSED;
		
		else {
			damageDealt = attacker.getDamage() + qualityFactor;
			defender.setHealth(defender.getHealth() - damageDealt);
			if (defender.getHealth() <= 0) {
				return DEATH_BLOW;
			}
			
			else
				return damageDealt;
		}	
	}

}