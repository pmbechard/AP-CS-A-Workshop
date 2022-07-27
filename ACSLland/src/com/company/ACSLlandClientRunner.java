package com.company;
/*
 * Reg Hahne
 * This is the client to run ACSLand
 * 7/18/2016
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class ACSLlandClientRunner {
	public static void main(String[] args) throws IOException {
		//Addressing file situation
		Scanner inFile = new Scanner(new File("ACSL.txt"));
		
		//variables
		int roll;
		Player playerA, playerB;
		boolean done;
		
		//play game
		for (int game = 1; game <= 5; game++) {
			playerA = new Player("Walt");
			playerB = new Player("Ellen");
			done = false;
			while (!done) {
				//PlayerA
				roll = inFile.nextInt();
				if (roll == 0)
					done = true;
				if (!done) {
					playerA.move(roll);
					if(playerA.getLocation() >= 40) {
						done = true;
						inFile.nextLine();
					} else if(playerA.collision(playerB))
						playerB.setLocation(0);
				}
				//Player B
				if (!done)
					roll = inFile.nextInt();
				if (roll == 0)
					done = true;
				if (!done) {
					playerB.move(roll);
					if (playerB.getLocation() >= 40) {
						done = true;
						inFile.nextLine();
					} else if (playerB.collision(playerA))
						playerA.setLocation(0);
				}
			}

			//working with toString
			System.out.print(playerA + ", " + playerB + "\n");
		}
		inFile.close();
	}
}