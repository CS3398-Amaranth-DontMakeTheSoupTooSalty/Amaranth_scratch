package Inventory; 
import java.io.Serializable;


public class item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String name;
	String description; 
	int value;
	int count; // the amount of the item held
	int rarity; // Either 0 -> common, 1 -> rare, 2 -> super rare
	
	public String getName(){ return name;}
	public String getDescr(){return description;}
	public int getCount(){return count;}
	public int getValue(){return value;}
	public int getRarity(){return value;}
	
	public item(){
		name = "";
		description = "";
		value = 0;
		count = 0;
		rarity = 0;
	}
	
	public void print(){
		System.out.println("************************");
		System.out.println("Name: " + this.name);
		System.out.println("Value: " + this.value);
		System.out.println("Description: " + "\"" + this.description + "\""); 
		System.out.println("************************");
	}
	
	
}