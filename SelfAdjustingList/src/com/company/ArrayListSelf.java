package com.company;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSelf {
    private List<Integer> mySelf;

    public ArrayListSelf() {
        mySelf = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++)
            mySelf.add((i+1)*2);
    }

    public int search(int n) {
        for (int i = 0; i < mySelf.size(); i++) {
            if (mySelf.get(i) == n)
                return i;
        }
        return -1;
    }

    public void add(int n) {
        mySelf.add(0, n);
    }

    public void move(int i) {
        mySelf.add(0, mySelf.remove(i));
    }

    public String toString() {
        String str = "";
        for (int num: mySelf) {
            str += num + " ";
        }
        return str;
    }
}