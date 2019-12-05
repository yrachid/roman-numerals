package com.zenvia.roman.converters;

import com.zenvia.roman.numerals.ArabicNumber;
import com.zenvia.roman.numerals.RomanNumber;

import static com.zenvia.roman.converters.PivotBasedPositionalConverter.Pivots.HUNDRED;
import static com.zenvia.roman.converters.PivotBasedPositionalConverter.Pivots.TENS;
import static com.zenvia.roman.converters.PivotBasedPositionalConverter.Pivots.UNIT;
import static com.zenvia.roman.converters.PivotBasedPositionalConverter.pivoting;
import static com.zenvia.roman.numerals.RomanNumeral.M;

public class ArabicToRomanNumeralConverter {

    public static String convert(int arabicValue) {

        try {

            ArabicNumber arabicNumber = ArabicNumber.of(arabicValue);

            RomanNumber thousand = M.repeat(arabicNumber.thousand());
            RomanNumber hundred = pivoting(HUNDRED).convert(arabicNumber.hundred() * 100);
            RomanNumber tens = pivoting(TENS).convert(arabicNumber.tens() * 10);
            RomanNumber unit = pivoting(UNIT).convert(arabicNumber.unit());

            return thousand
                    .concat(hundred)
                    .concat(tens)
                    .concat(unit)
                    .toString();

        } catch (IllegalArgumentException ex) {
            return "ERROR";
        }
    }
}
