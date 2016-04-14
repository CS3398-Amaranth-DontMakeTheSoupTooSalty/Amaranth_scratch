package battle;

import character.*;

public class magic {
	public static final int SUCCESS = 0;
	
	public static int heal(character caller) {
		caller.setHealth(caller.getHealth() + 2);
		return SUCCESS;
	}
}
