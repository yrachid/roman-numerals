package com.yrachid.roman.numerals;

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

    public static RomanNumber compose(RomanNumeral numeral, RomanNumeral... numerals) {
        return RomanNumber.of(numeral, numerals);
    }

    public RomanNumber repeat(int iterations) {
        return RomanNumber.repeating(this, iterations);
    }

    public RomanNumber concat(RomanNumber postfix) {
        return RomanNumber.concat(this, postfix);
    }

    public RomanNumber identity() {
        return RomanNumber.of(this);
    }
}
