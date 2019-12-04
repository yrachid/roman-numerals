package com.zenvia.roman;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

        public String repeated(int times) {
            return IntStream.rangeClosed(1, times)
                    .mapToObj(i -> name())
                    .collect(Collectors.joining());
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

        if (arabicValue <= 10) {
            return convertUnit(arabicValue);
        }

        if (arabicValue <= 20) {
            return convertTens(arabicValue);
        }

        return Integer.toString(arabicValue);
    }

    private static String convertTens(int arabicValue) {
        return "X" + convertUnit(arabicValue - 10);
    }

    private static String convertUnit(int value) {
        if (value < 4) {
            return RomanNumeral.I.repeated(value);
        }

        if (value > 5 && value < 9) {
            return "V" + RomanNumeral.I.repeated(value - 5);
        }

        if (value == 4) {
            return "IV";
        }

        if (value == 9) {
            return "IX";
        }

        if (value == 5) {
            return "V";
        }

        return "X";
    }
}
