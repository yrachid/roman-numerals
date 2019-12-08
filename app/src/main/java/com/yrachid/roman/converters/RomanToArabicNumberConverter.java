package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;
import com.yrachid.roman.numerals.RomanNumeral;

import java.util.List;

public class RomanToArabicNumberConverter {

    public static ArabicNumber convert(RomanNumber romanNumber) {
        List<RomanNumeral> numerals = romanNumber.numerals();

        if (numerals.size() == 1) {
            return ArabicNumber.of(numerals.get(0).intValue());
        }

        if (numerals.size() == 2) {
            return ArabicNumber.of(calculate(numerals.get(0), numerals.get(1)));
        }

        return ArabicNumber.of(sumNumerals(numerals, 0, numerals.size() - 2));
    }

    private static int sumNumerals(List<RomanNumeral> numerals, int result, int index) {
        if (iterationFinished(index)) {
            return hasEvenSize(numerals)
                    ? result
                    : result + numerals.get(0).intValue();
        }

        int currentResult = calculate(
                numerals.get(index),
                numerals.get(index + 1)
        );

        return sumNumerals(numerals, result + currentResult, index - 2);
    }

    private static int calculate(RomanNumeral left, RomanNumeral right) {
        return left.intValue() < right.intValue()
                ? right.intValue() - left.intValue()
                : right.intValue() + left.intValue();
    }


    private static boolean iterationFinished(int index) {
        return index < 0;
    }

    private static boolean hasEvenSize(List<RomanNumeral> numerals) {
        return numerals.size() % 2 == 0;
    }

}
