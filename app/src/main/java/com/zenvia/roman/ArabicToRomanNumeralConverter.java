package com.zenvia.roman;

import com.zenvia.roman.numeral.ArabicNumeral;
import com.zenvia.roman.numeral.CompoundNumeral;

import static com.zenvia.roman.PivotBasedPositionalConverter.Pivots.HUNDRED;
import static com.zenvia.roman.PivotBasedPositionalConverter.Pivots.TENS;
import static com.zenvia.roman.PivotBasedPositionalConverter.Pivots.UNIT;
import static com.zenvia.roman.PivotBasedPositionalConverter.pivoting;
import static com.zenvia.roman.numeral.RomanNumeral.M;

public class ArabicToRomanNumeralConverter {

    public static String convert(int arabicValue) {

        try {

            ArabicNumeral arabicNumeral = ArabicNumeral.of(arabicValue);

            CompoundNumeral thousand = M.repeat(arabicNumeral.thousand());
            CompoundNumeral hundred = pivoting(HUNDRED).convert(arabicNumeral.hundred() * 100);
            CompoundNumeral tens = pivoting(TENS).convert(arabicNumeral.tens() * 10);
            CompoundNumeral unit = pivoting(UNIT).convert(arabicNumeral.unit());

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
