package character;

//import charGen.*;

public class character {
	
	String name;
	int health;
	int defense;
	int accuracy;
	int damage;
	
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
	public character() {
		name = "";
		health = 0;
		defense = 0;
		accuracy = 0;
		damage = 0;
		
		/* call to charGen */
	}
}