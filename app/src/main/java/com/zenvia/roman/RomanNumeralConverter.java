package com.zenvia.roman;

public class RomanNumeralConverter {

    public static String convert(int arabicValue) {
        if (arabicValue > 3000) {
            return "ERROR";
        }

        return Integer.toString(arabicValue);
    }
}
