package com.company;

public class PalindromicNumbers {

    public static boolean isPalindromicNumber(String num) {
        int intTotal = 0;
        String stringTotal = num + "";
        for (int i = 0; i < 3; i++) {
            intTotal = stringToInt(stringTotal) + stringToInt(getReversedNumString(stringTotal));
            stringTotal = String.format("%03d", intTotal);
        }
        for (int i = 0; i < stringTotal.length() / 2; i++) {
            if (stringTotal.charAt(i) != stringTotal.charAt(stringTotal.length() - 1 - i))
                return false;
        }
        return true;
    }

    private static String getReversedNumString(String num) {
        String reversedNum = "";
        for (int i = num.length() - 1; i >= 0; i--) {
            reversedNum += num.charAt(i);
        }
        return reversedNum;
    }

    private static int stringToInt(String num) {
        return Integer.parseInt(num);
    }
}