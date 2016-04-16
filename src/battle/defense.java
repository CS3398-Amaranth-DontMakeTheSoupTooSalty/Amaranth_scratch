package battle;

import character.*;

public class defense {
	public static final int BLOCK_SET = 1;
	public static final int BLOCK_OFF = 0;
	public static final int ERROR = -1;
	
	public static int block(character caller) {
		caller.setDefense(caller.getDefense() + 5);
		caller.setBlocking(true);
		return BLOCK_SET;
	}
	
	public static int removeBlock(character caller) {
		if (caller.getBlocking() == true) {
			caller.setDefense(caller.getDefense() - 5);
			caller.setBlocking(false);
			return BLOCK_OFF;
		}
		
		else
			return ERROR;
		
	}
	
}
