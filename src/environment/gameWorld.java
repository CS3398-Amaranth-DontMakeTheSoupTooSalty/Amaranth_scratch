package environment;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

import character.player;
import java.util.Scanner;

public class gameWorld {
	public static void environmentGeneratorCLI(String environmentName,String description){
		String areaModuleDescription = description;
		String locationVar = environmentName;
		
		System.out.println("You are @ " + locationVar);
		System.out.println(areaModuleDescription);
	}
	
	public static void environmentCreatorCLI( Scanner in){
		int flag = 1;
		while(flag == 1){
		System.out.println("Create an Area?");
		
		System.out.println("Type the name of the New Area");
		File file = new File (environmentName+".env");
		PrintWriter PrintWriter = null;
		try {
			PrintWriter = new PrintWriter (environmentName+".env");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintWriter.println(environmentName);
		}
		
		PrintWriter.close();
	}
}
