package com.zenvia.roman;

import com.zenvia.roman.numeral.CompoundNumeral;
import com.zenvia.roman.numeral.RomanNumeral;

import static com.zenvia.roman.numeral.RomanNumeral.I;
import static com.zenvia.roman.numeral.RomanNumeral.V;
import static com.zenvia.roman.numeral.RomanNumeral.X;
import static com.zenvia.roman.numeral.RomanNumeral.compose;

public class RomanNumeralConverter {

    public static String convert(int arabicValue) {

        if (arabicValue > 3000 || arabicValue < 1) {
            return "ERROR";
        }

        if (RomanNumeral.hasDirectEquivalent(arabicValue)) {
            return RomanNumeral.fromArabicValue(arabicValue)
                    .map(Enum::name)
                    .orElse(Integer.toString(arabicValue));
        }

        if (arabicValue <= 10) {
            return convertUnit(arabicValue).toString();
        }

        if (arabicValue <= 20) {
            return convertTens(arabicValue);
        }

        return Integer.toString(arabicValue);
    }

    private static String convertTens(int arabicValue) {
        return X.concat(convertUnit(arabicValue - 10)).toString();
    }

    private static CompoundNumeral convertUnit(int value) {
        if (value < 4) {
            return I.repeat(value);
        }

        if (value > 5 && value < 9) {
            return V.concat(I.repeat(value - 5));
        }

        if (value == 4) {
            return compose(I, V);
        }

        if (value == 9) {
            return compose(I, X);
        }

        if (value == 5) {
            return V.identity();
        }

        return X.identity();
    }
}
