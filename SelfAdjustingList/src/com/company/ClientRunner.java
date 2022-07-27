package com.company;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class ClientRunner {
    public static void main(String[] args) throws IOException {
//        Self mySelf;
        ArrayListSelf mySelf;

        int num;
        int index;
        boolean done;
        Scanner inFile = new Scanner(new File("self.txt"));

        while (inFile.hasNext()) {
//            mySelf = new Self();
            mySelf = new ArrayListSelf();
            done = false;
            while (!done) {
                num = inFile.nextInt();
                if (num == 0)
                    done = true;
                else {
                    index = mySelf.search(num);
                    if (index == -1)
                        mySelf.add(num);
                    else
                        mySelf.move(index);
                }
            }
            System.out.println(mySelf);
        }
        inFile.close();
    }
}