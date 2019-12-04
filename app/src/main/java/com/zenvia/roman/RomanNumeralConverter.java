package com.zenvia.roman;

public class RomanNumeralConverter {

    public static String convert(int arabicValue) {
        if (arabicValue > 3000) {
            return "ERROR";
        }

        if (arabicValue == 1) {
            return "I";
        }

        if (arabicValue == 5) {
            return "V";
        }

        if (arabicValue == 10) {
            return "X";
        }

        if (arabicValue == 50) {
            return "L";
        }

        if (arabicValue == 100) {
            return "C";
        }

        if (arabicValue == 500) {
            return "D";
        }

        if (arabicValue == 1000) {
            return "M";
        }

        return Integer.toString(arabicValue);
    }
}
