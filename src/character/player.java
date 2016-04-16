package character;

import java.io.Serializable;

import charGen.charGen;

public class player extends character implements Serializable {
	

	private static final long serialVersionUID = 1L;

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
		health = 25;
		defense = 15;
		accuracy = 15;
		damage = 7;
		location = "START";
		blocking = false;
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