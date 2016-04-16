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
		String terrainArray[terrainType];
		String environmentName;
		
		while(flag == 1){
		System.out.println("To create an area, press 1");
		in.nextInt();
		System.out.println("Type the name of the New Area, no spaces");
		environmentName = in.next();
		File file = new File (environmentName+".env");
		PrintWriter PrintWriter = null;
		try {
			PrintWriter = new PrintWriter (environmentName+".env");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Choose a Terrain Type: ");
		for ( int i = 0; i<terrainType; i++)
		{
			
		}
		PrintWriter.println(environmentName);
		}
		
		PrintWriter.close();
	}
}
