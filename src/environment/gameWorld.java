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
	
	public static void environmentCreatorCLI(String environmentName, Scanner in){
		File file = new File (environmentName+".env");
		PrintWriter PrintWriter = null;
		try {
			PrintWriter = new PrintWriter (environmentName+".env");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter.println(environmentName);
		PrintWriter.println(environmentName);
		System.out.println("Write a description for this area");
		PrintWriter.close();
	}
}
