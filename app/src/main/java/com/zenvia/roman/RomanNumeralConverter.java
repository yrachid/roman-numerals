package com.zenvia.roman;

import com.zenvia.roman.numeral.ArabicNumeral;
import com.zenvia.roman.numeral.CompoundNumeral;
import com.zenvia.roman.numeral.RomanNumeral;

import static com.zenvia.roman.numeral.RomanNumeral.C;
import static com.zenvia.roman.numeral.RomanNumeral.I;
import static com.zenvia.roman.numeral.RomanNumeral.L;
import static com.zenvia.roman.numeral.RomanNumeral.V;
import static com.zenvia.roman.numeral.RomanNumeral.X;
import static com.zenvia.roman.numeral.RomanNumeral.compose;

public class RomanNumeralConverter {

    public static String convert(int arabicValue) {

        try {

            ArabicNumeral arabicNumeral = ArabicNumeral.of(arabicValue);

            if (RomanNumeral.hasDirectEquivalent(arabicValue)) {
                return RomanNumeral.fromArabicValue(arabicValue)
                        .map(Enum::name)
                        .orElse(Integer.toString(arabicValue));
            }

            CompoundNumeral tens = arabicNumeral.tens() == 0
                    ? CompoundNumeral.empty()
                    : convertTens(arabicNumeral.tens() * 10);

            CompoundNumeral unit = arabicNumeral.unit() == 0
                    ? CompoundNumeral.empty()
                    : convertUnit(arabicNumeral.unit());

            return tens.concat(unit).toString();

        } catch (IllegalArgumentException ex) {
            return "ERROR";
        }
    }

    private static CompoundNumeral convertTens(int tens) {
        if (tens < 40) {
            return X.repeat(tens / 10);
        }

        if (tens > 50 && tens < 90) {
            return L.concat(X.repeat((tens - 50) / 10));
        }

        if (tens == 40) {
            return compose(X, L);
        }

        if (tens == 90) {
            return compose(X, C);
        }

        if (tens == 50) {
            return L.identity();
        }

        return C.identity();
    }

    private static CompoundNumeral convertUnit(int unit) {
        if (unit < 4) {
            return I.repeat(unit);
        }

        if (unit > 5 && unit < 9) {
            return V.concat(I.repeat(unit - 5));
        }

        if (unit == 4) {
            return compose(I, V);
        }

        if (unit == 9) {
            return compose(I, X);
        }

        if (unit == 5) {
            return V.identity();
        }

        return X.identity();
    }
}
