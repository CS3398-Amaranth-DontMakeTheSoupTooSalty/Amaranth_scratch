package environment;

import java.util.Scanner;

public class start {
	static int interfaceChoice;
	
	public static void interfaceChoice(Scanner in){
	    System.out.println("Choose an interface\n"
	    		+ "For an old school Command Line Interface, press 1\n"
	    		+ "For a Graphical Interface, press 2\n");
	    interfaceChoice = in.nextInt();
	    //place holder, will beed to pass this to a function or class that switches interface
	    System.out.println(interfaceChoice);
	}
	

}
