package character;

import charGen.charGen;

public class player extends character {
	
	/*
	 * FUNCTION:
	 *   character()
	 * IN-PARAMETERS:
	 *   None
	 * OUT-PARAMETERS:
	 *   None
	 * DESCRIPTION:
	 *   Default constructor.
	 *   
	 *   Consider integrating a call to charGen or moving charGen
	 *   methods into this class or sub-classes.
	 */
	public player() {
		name  = "";
		health = 0;
		defense = 0;
		accuracy = 0;
		damage = 0;
		location="";
		/* call to charGen point allocator */
	}
	
	public void statTransition( charGen.charStats playerCharacterType ){
		name = playerCharacterType.name;
		health = playerCharacterType.health;
		defense = playerCharacterType.defense;
		accuracy = playerCharacterType.accuracy;
		damage = playerCharacterType.damage;
	}
}