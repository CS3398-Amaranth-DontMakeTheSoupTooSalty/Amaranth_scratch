package mainDriver;

import java.util.Scanner;

import charGen.charGen;
import charGen.pointAllocator;
import charGen.charGen.charStats;
import character.character;
import character.player;

public class mainDriver {
	public static void main(String[] args){
		
		Scanner input = new Scanner( System.in );
		
		player playerCharacter = new player();
		
		charGen objectCharGen = new charGen();
		
		charGen.charStats playerCharacterHolder = new charStats();
		
		objectCharGen.charName( playerCharacterHolder, input );
		
		pointAllocator.charStatPrint( playerCharacterHolder );
		
		pointAllocator.statAlloc ( playerCharacterHolder );
		
		playerCharacter.statTransition( playerCharacterHolder );
		
		input.close();
	}
}
