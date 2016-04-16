package battle;

import character.*;

public class attack {
	public static final int DAMAGE_DEALT = 0;
	public static final int ATTACK_MISSED = 1;
	public static final int DEATH_BLOW = 2;
	public static final int ERROR = -1;
	
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
		
		if (attacker.getAccuracy() > defender.getDefense()) {
			defender.setHealth(defender.getHealth() - attacker.getDamage());
			if (defender.getHealth() <= 0) {
				return DEATH_BLOW;
			}
			
			else
				return DAMAGE_DEALT;
		}
		
		else
			return ATTACK_MISSED;
	}

}