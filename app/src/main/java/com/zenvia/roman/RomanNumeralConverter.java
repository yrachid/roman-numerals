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

        public static boolean hasDirectEquivalent(int arabicValue) {
            return Stream.of(values())
                    .anyMatch(roman -> roman.arabicValue == arabicValue);
        }
    }

    public static String convert(int arabicValue) {

        if (arabicValue > 3000 || arabicValue < 1) {
            return "ERROR";
        }

        if (RomanNumeral.hasDirectEquivalent(arabicValue)) {
            return RomanNumeral.fromArabicValue(arabicValue)
                    .map(Enum::name)
                    .orElse(Integer.toString(arabicValue));
        }

        if (arabicValue < 10) {
            return convertUnit(arabicValue);
        }

        return Integer.toString(arabicValue);
    }

    private static String convertUnit(int value) {
        if (value == 2) {
            return "II";
        }

        if (value == 3) {
            return "III";
        }

        if (value == 4) {
            return "IV";
        }

        if (value == 6) {
            return "VI";
        }

        if (value == 7) {
            return "VII";
        }

        if (value == 8) {
            return "VIII";
        }

        return "IX";
    }
}
