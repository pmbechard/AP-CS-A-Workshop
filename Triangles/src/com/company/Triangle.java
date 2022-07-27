package com.company;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    private ArrayList<Integer> nums = new ArrayList<>();

    public Triangle(String numsString) {
        numsStringToIntArrayList(numsString);
    }

    public ArrayList<Integer> getNums() {
        return nums;
    }

    public void setNums(String numsString) {
        this.nums.clear();
        numsStringToIntArrayList(numsString);
    }

    public int getPoint() {
        ArrayList<Integer> nextRow = new ArrayList<>();
        ArrayList<Integer> currentRow = new ArrayList<>(this.nums);

        while (true) {
            for (int i = currentRow.size() - 2; i >= 0; i--) {
                int sum = this.getSum(currentRow.subList(i, currentRow.size()));
                nextRow.add(0, sum);
            }

            if (nextRow.size() == 1) {
                return nextRow.get(0);
            } else {
                currentRow = new ArrayList<>(nextRow);
                nextRow.clear();
            }
        }

    }

    private int getSum(List<Integer> nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        return sum;
    }

    private void numsStringToIntArrayList(String numsString) {
        String[] numsStringArr= numsString.split(" ");
        for (int i = 1; i < numsStringArr.length; i++) {
            this.nums.add(Integer.parseInt(numsStringArr[i]));
        }
    }
}

