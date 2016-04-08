package environment;
import character.player;

public class gameWorld {
	public static void environmentGenerator(String environmentName,String description,
											  character.character playerChar){
		String areaModuleDescription = description;
		String locationVar = environmentName;
		
		playerChar.location = locationVar;
	}
}
