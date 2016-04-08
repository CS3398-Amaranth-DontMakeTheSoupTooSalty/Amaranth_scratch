package environment;
import character.player;

public class gameWorld {
	public static void environmentGeneratorCLI(String environmentName,String description){
		String areaModuleDescription = description;
		String locationVar = environmentName;
		
		System.out.println("You are @ " + locationVar);
		System.out.println(areaModuleDescription);
	}
}
