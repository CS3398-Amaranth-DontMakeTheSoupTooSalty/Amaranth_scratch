package battle;

import character.*;

public class magic {
	public static final int SUCCESS = 0;
	
	public static int heal(character caller) {
		caller.setHealth(caller.getHealth() + 4);
		//if(caller.getHealth() + 4 > caller.getMaxHealth())
			//caller.setHealth(caller.getMaxHealth());
		//else
			//caller.setHealth(caller.getHealth() + 4);
		return SUCCESS;
	}
}
