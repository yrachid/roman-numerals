package com.zenvia.roman.converters;

import com.zenvia.roman.numerals.ArabicNumber;
import com.zenvia.roman.numerals.RomanNumber;

import static com.zenvia.roman.converters.Pivots.HUNDRED;
import static com.zenvia.roman.converters.Pivots.TENS;
import static com.zenvia.roman.converters.Pivots.UNIT;
import static com.zenvia.roman.converters.PivotBasedPositionalConverter.pivoting;
import static com.zenvia.roman.numerals.RomanNumeral.M;

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
