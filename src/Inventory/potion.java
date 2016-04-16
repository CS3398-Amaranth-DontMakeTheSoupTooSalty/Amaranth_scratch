package Inventory;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class potion extends item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	int stat_amnt; // amount added to character stats
	String stat_field; 
	public potion(){
		name = "";
		description = null;
		value = 0;
		count = 0;
		rarity = 0;
		stat_amnt = 0;
		stat_field = null; 
	}
	
	public void printDetailed(){
		System.out.println("************************");
		System.out.println("Name: " + this.name);
		System.out.println("Value: " + this.value);
		System.out.println("Count: " + this.count);
		System.out.println("Rarity: " + this.rarity);
		System.out.println("Stat Amnt: " + this.stat_amnt);
		System.out.println("Stat Modifier: " + this.stat_field);
		System.out.println("Description: " + "\"" + this.description + "\""); 
		System.out.println("************************");
	}
	
	public static potion potionGen(String potionFile) throws FileNotFoundException{
		potion newPotion = new potion();
		String statDescriptor = "";
		
		java.io.File input = new java.io.File(potionFile);
		Scanner infile = new Scanner(input);
		
		while(infile.hasNextLine())
		{
			statDescriptor = infile.next();
			switch(statDescriptor)
			{
				case "name:":
					infile.skip(" "); //Skips the space between "description:" and the description text in input file
					newPotion.name = infile.nextLine();
					infile.nextLine();
					break;
				case "value:":
					newPotion.value = infile.nextInt();
					break;
				case "count:":
					newPotion.count = infile.nextInt();
					break;
				case "rarity:":
					newPotion.rarity = infile.nextInt();
					break;
				case "stat_amnt:":
					newPotion.stat_amnt = infile.nextInt();
					break;
				case "description:":
					infile.skip(" "); //Skips the space between "description:" and the description text in input file
					newPotion.description = infile.nextLine();
					infile.nextLine();
					break;
				case "stat_field:":
					newPotion.stat_field = infile.next();
					break;
			}
		}
		infile.close();
		return newPotion;
	}
	
} // comment added to allow push