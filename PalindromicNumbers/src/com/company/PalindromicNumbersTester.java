package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PalindromicNumbersTester {
    public static void main(String[] args) {
            try {
                File myObj = new File("numbers.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(PalindromicNumbers.isPalindromicNumber(data));
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        /*

        Input   Expected    Correct?    Comments
---------------------------------------------------------
        030     TRUE            √
        461     TRUE            √
        748     FALSE           √
        192     TRUE            x       should be false?
        123     FALSE           √

        888     FALSE           √
        501     TRUE            √
        151     TRUE            x       should be false?
        140     FALSE           √
        111     TRUE            √

         */
    }
}
