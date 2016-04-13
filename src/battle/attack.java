package battle;

import character.*;

public class attack {
	public static final int DAMAGE_DEALT = 0;
	public static final int ATTACK_MISSED = 1;
	public static final int DEATH_BLOW = 2;
	
	public int attackUnarmed(character attacker, character defender) {
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
// added comment to allow comit and attempt to push