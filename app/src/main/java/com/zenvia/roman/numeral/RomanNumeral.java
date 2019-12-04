package com.zenvia.roman.numeral;

import java.util.Optional;
import java.util.stream.Stream;

public enum RomanNumeral {
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

    public static CompoundNumeral compose(RomanNumeral ... numerals) {
        return CompoundNumeral.of(numerals);
    }

    public CompoundNumeral repeat(int value) {
        return CompoundNumeral.repeating(this, value);
    }

    public CompoundNumeral concat(CompoundNumeral postfix) {
        return CompoundNumeral.concat(this, postfix);
    }

    public CompoundNumeral identity() {
        return CompoundNumeral.of(this);
    }
}
