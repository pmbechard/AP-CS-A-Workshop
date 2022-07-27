package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClientRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inFile = new Scanner(new File("scores.txt"));

        while (inFile.hasNextLine()) {
            ScoreCalculator scoreTotal = new ScoreCalculator(inFile.nextLine());
            System.out.println(scoreTotal.calculateTotals());
        }

        /*
        EXPECTED OUTPUT:
        20 46 65 74 92 101 129 149 167 186
        20 40 60 78 86 95 125 155 183 203
        6 15 23 32 32 41 47 55 63 72
        30 60 90 120 15 18 210 240 270 300
        20 40 60 80 100 120 140 160 180 200
         */
    }
}
