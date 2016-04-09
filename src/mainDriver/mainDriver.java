package mainDriver;

import java.awt.Container;
import java.io.PrintWriter;
import java.io.File;
import java.awt.Font;
//import java.awt.Insets;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JTextField;

import charGen.charGen;
import charGen.pointAllocator;
import charGen.charGen.charStats;
import character.character;
import character.player;
import environment.gameWorld;
import environment.start;
import saveGameState.*;

public class mainDriver {
	@SuppressWarnings("static-access")
	public static void main(String[] args){
	
		Scanner input = new Scanner( System.in );
		//Allows user to choose between a CLI or a GUI
		start startObj = new start();
	    startObj.interfaceChoice(input);
	    if(startObj.interfaceChoice == 2){
	        JFrame frame1;
	        Container pane;
	        JButton btnNewGame, btnExit, btnLoadGame;
	        JLabel lblGameName;
	        //JTextField txtServer;
	        //Insets insets;
	    	
	        //Create the frame
	        frame1 = new JFrame ("Game GUI Test");
	        //Set its size to 800x800 pixels
	        frame1.setSize (800,800);
	        //Prepare panel
	        pane = frame1.getContentPane();
	        //insets = pane.getInsets();
	        //Apply the null layout
	        pane.setLayout (null);
	        
	        btnNewGame = new JButton ("New Game");
	        btnLoadGame = new JButton ("Load Game");
	        btnExit = new JButton ("Exit");
	        lblGameName = new JLabel ("Le Creative Game Name");
	        //txtServer = new JTextField (10);
	        
	        //set font
	        lblGameName.setFont(new Font("Century Gothic", Font.PLAIN, 30));
	        
	        pane.add (lblGameName); //Add component to panel
	        lblGameName.setBounds (215, 275, 400, 35);

	        //Add all components to panel
	        pane.add (btnNewGame);
			pane.add (btnLoadGame);
	        pane.add (btnExit);
	 
	        //Place all components  
	/*      lblServer.setBounds (insets.left + 5, insets.top + 5, lblServer.getPreferredSize().width, lblServer.getPreferredSize().height);
	        txtServer.setBounds (lblServer.getX() + lblServer.getWidth() + 5, insets.top + 5, txtServer.getPreferredSize().width, txtServer.getPreferredSize().height);*/
	        btnNewGame.setBounds (350, 350, 100, 30);
	        btnLoadGame.setBounds (350, 400, 100, 30);
	        btnExit.setBounds (350, 450, 100, 30);

	        frame1.setVisible (true);
	    }
	    else if(startObj.interfaceChoice == 1){
	    	
	    	//If player selects a new game
	    	if( start.NewGameQ(input) )
	    	{
	    		//Create a player object
	    		player playerCharacter = new player();
	    		//Create a charGen object
	    		charGen objectCharGen = new charGen();
	    		//Generic holder instantiation for character stats
	    		//Makes easier for us to pass as object to do stat manipulation
	    		charGen.charStats playerCharacterHolder = new charStats();
	    		//User chooses name for character. Separated intentionally from
	    		//other stat allocation.
	    		objectCharGen.charName( playerCharacterHolder, input );
	    		//Shows current default stats
	    		pointAllocator.charStatPrint( playerCharacterHolder );
	    		//Handles the allocation of available character creation stat points
	    		//prompting user to make choices on how they want to alloc their stats
	    		pointAllocator.statAlloc ( playerCharacterHolder );
	    		//Takes the place holder object stats and links it to playerChar object
	    		playerCharacter.statTransition( playerCharacterHolder );
	    		//print final stat choices to char so they see the results of their choices
	    		//Function can be used, when called to do so, to allow player to see their
	    		//current stats during play period
	    		playerCharacter.printStats();
	    		serialSave.saveChar(playerCharacter);
	    		
	    		/* send character to game driver */
	    	}
	    	else // User wants to load game
	    	{
	    		player characterPlayer = new player();
	    		characterPlayer = serialSave.loadChar();
	    		/* send character to game driver */
	    		
	    	}
	    }
	    
	    //TEST BLOCK, TO BE REVISED FOR FILE SYSTEM
	    PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
	    String testEnvironDesc = "FOREST the thousand acre woods";
	    String testEnvironName ="START";
	    gameWorld gameWorldObj = new gameWorld();
	    gameWorldObj.environmentGeneratorCLI(testEnvironName, testEnvironDesc);
	    PrintWriter.close();
	    
	    
		input.close();
	}
}
