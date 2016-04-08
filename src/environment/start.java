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
	    //place holder, will beed to pass this to a function or class that switches interface
	    System.out.println(interfaceChoice);
	}
	
	public static void NewGameQ(Scanner in){
		System.out.println("(N)ew Game\n");
		System.out.println("(L)oad Game");
		
		NewGameChoice = in.next();
	}

}
