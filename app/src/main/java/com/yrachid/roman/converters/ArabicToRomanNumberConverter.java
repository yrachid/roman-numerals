package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;

import static com.yrachid.roman.converters.Pivots.HUNDRED;
import static com.yrachid.roman.converters.Pivots.TENS;
import static com.yrachid.roman.converters.Pivots.UNIT;
import static com.yrachid.roman.converters.PivotBasedPositionalConverter.pivoting;
import static com.yrachid.roman.numerals.RomanNumeral.M;

public class ArabicToRomanNumberConverter {

    public static RomanNumber convert(ArabicNumber arabicNumber) {

        RomanNumber thousand = M.repeat(arabicNumber.thousand());
        RomanNumber hundred = pivoting(HUNDRED).convert(arabicNumber.hundred() * 100);
        RomanNumber tens = pivoting(TENS).convert(arabicNumber.tens() * 10);
        RomanNumber unit = pivoting(UNIT).convert(arabicNumber.unit());

        return thousand
                .concat(hundred)
                .concat(tens)
                .concat(unit);
    }
}
