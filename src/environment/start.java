package environment;

import java.util.Scanner;

public class start {
	public static int interfaceChoice;
	public static String NewGameChoice;
	
	public static void interfaceChoice(Scanner in){
	    System.out.println("Choose an interface\n"
	    		+ "For an old school Command Line Interface, press 1\n"
	    		+ "For a Graphical Interface, press 2\n");
	    interfaceChoice = in.nextInt();
	    //place holder, will need to pass this to a function or class that switches interface
	    System.out.println(interfaceChoice);
	}
	
	public static boolean NewGameQ(Scanner in){
		System.out.println("(N)ew Game\n");
		System.out.println("(L)oad Game");
		do{
			NewGameChoice = in.next();
			if(NewGameChoice.equals("n") || NewGameChoice.equals("N") || NewGameChoice.equals("new"))
			{
				return true;
			}
			else if(NewGameChoice.equals("l") || NewGameChoice.equals("L") || NewGameChoice.equals("load"))
				return false;
			else
			{
				System.out.println("You made an invalid selection, please enter either N for a new game, or L to load a saved game!");
			}
		}while(true);
		
	}

}
