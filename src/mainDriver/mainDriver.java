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
		
		charGen objectCharGen = new charGen();
		
		charGen.charStats playerCharacter = new charStats();
		
		objectCharGen.charName( playerCharacter, input );
		
		pointAllocator.charStatPrint( playerCharacter );
		
		pointAllocator.statAlloc ( playerCharacter );
		
		input.close();
	}
}
