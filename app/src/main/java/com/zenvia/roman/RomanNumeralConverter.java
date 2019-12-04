package com.zenvia.roman;

import java.util.Optional;
import java.util.stream.Stream;

public class RomanNumeralConverter {

    private enum RomanNumeral {

        I(1),
        V(5),
        X(10),
        L(50),
        C(100),
        D(500),
        M(1000);

        private int arabicValue;

        RomanNumeral(int arabicValue) {
            this.arabicValue = arabicValue;
        }

        public static Optional<RomanNumeral> fromArabicValue(int arabicValue) {
            return Stream.of(values())
                    .filter(roman -> roman.arabicValue == arabicValue)
                    .findFirst();
        }
    }

    public static String convert(int arabicValue) {

        if (arabicValue > 3000) {
            return "ERROR";
        }

        return RomanNumeral.fromArabicValue(arabicValue)
                .map(Enum::name)
                .orElse(Integer.toString(arabicValue));
    }
}
