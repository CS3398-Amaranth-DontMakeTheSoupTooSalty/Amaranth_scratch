package saveGameState;
import java.io.*;
import character.player;

public class serialSave
{
		
   public static void saveChar(player myChar)
   {
	   try{
         FileOutputStream fileOut = new FileOutputStream("characterObj.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(myChar);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in characterObj.ser");
   }catch(IOException i)
      {
          i.printStackTrace();
      }
   }
   
   public static player loadChar()
   {
	   player myChar = null;
	   try
	   {
		   FileInputStream fileIn = new FileInputStream("characterObj.ser");
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       myChar = (player) in.readObject();
	       in.close();
	       fileIn.close();
	       return myChar; 
	   }
	   catch(IOException i)
	   {
	      i.printStackTrace();
	      return myChar;
	   }
	   catch(ClassNotFoundException c)
	   {
	       System.out.println("Class not found");
	       c.printStackTrace();
	       return myChar; 
	   }
   }
}

