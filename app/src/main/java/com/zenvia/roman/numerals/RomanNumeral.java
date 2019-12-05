package com.zenvia.roman.numerals;

public enum RomanNumeral {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    public static CompoundNumeral compose(RomanNumeral... numerals) {
        return CompoundNumeral.of(numerals);
    }

    public CompoundNumeral repeat(int iterations) {
        return CompoundNumeral.repeating(this, iterations);
    }

    public CompoundNumeral concat(CompoundNumeral postfix) {
        return CompoundNumeral.concat(this, postfix);
    }

    public CompoundNumeral identity() {
        return CompoundNumeral.of(this);
    }
}
