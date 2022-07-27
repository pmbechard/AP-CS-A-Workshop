package com.company;

public class Self {
    private int[] mySelf;

    public Self() {
        mySelf = new int[10];
        for (int i = 0; i < mySelf.length; i++) {
            mySelf[i] = (i + 1) * 2;
        }
    }

    public int search(int n) {
        for (int i = 0; i < mySelf.length; i++) {
            if (mySelf[i] == n)
                return i;
        }
        return -1;
    }

    public void add(int n) {
        int[] temp = new int[mySelf.length + 1];
        temp[0] = n;
        for (int i = 0; i < mySelf.length; i++)
            temp[i + 1] = mySelf[i];
        mySelf = temp;
    }

    public void move(int i) {
        int temp = mySelf[i];
        for (int j = i; j > 0; j--)
            mySelf[j] = mySelf[j - 1];
        mySelf[0] = temp;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < mySelf.length; i++)
            str += mySelf[i] + " ";
        return str;
    }
}
