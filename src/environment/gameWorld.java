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
		int terrainType = 4;
		int choice;
		String terrainArray[] = {"FOREST","CAVE","PLAINS","DESERT"};
		String environmentName;
		
		while(flag == 1){
		System.out.println("To create an area, press 1");
		choice = in.nextInt();
		if (choice != 1)
			break;
		System.out.println("Type the name of the New Area, no spaces");
		environmentName = in.next();
		File file = new File (environmentName+".env");
		PrintWriter PrintWriter = null;
		try {
			PrintWriter = new PrintWriter (environmentName+".env");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Choose a Terrain Type By Number: ");
		for ( int i = 0; i<terrainType; i++)
		{
			System.out.println((i+1)+" "+terrainArray[i]);
		}
		choice = in.nextInt();
		PrintWriter.println(terrainArray[choice-1]);
		
		PrintWriter.close();
		}
	}
}
