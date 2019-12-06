package com.yrachid.roman.numerals;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.stream.Collectors.joining;

public class ArabicNumber {

    private static final int UNIT_POSITION = 3;
    private static final int TENS_POSITION = 2;
    private static final int HUNDRED_POSITION = 1;
    private static final int THOUSAND_POSITION = 0;

    public static final ArabicNumber MAX_VALUE = new ArabicNumber(new String[]{"3", "0", "0", "0"});
    private static final ArabicNumber MIN_VALUE = new ArabicNumber(new String[]{"0", "0", "0", "1"});

    private final String[] positionalValues;

    private ArabicNumber(String[] positionalValues) {
        this.positionalValues = positionalValues;
    }

    public int intValue() {
        return parseInt(join("", positionalValues));
    }

    public int unit() {
        return valueAt(UNIT_POSITION);
    }

    public int tens() {
        return valueAt(TENS_POSITION);
    }

    public int hundred() {
        return valueAt(HUNDRED_POSITION);
    }

    public int thousand() {
        return valueAt(THOUSAND_POSITION);
    }

    private int valueAt(int index) {
        return parseInt(positionalValues[index]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArabicNumber number = (ArabicNumber) o;
        return Arrays.equals(positionalValues, number.positionalValues);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(positionalValues);
    }

    public static ArabicNumber of(int value) {

        if (value > ArabicNumber.MAX_VALUE.intValue()) {
            throw new IllegalArgumentException(
                    format("Arabic numerals greater than %d are not supported", ArabicNumber.MAX_VALUE.intValue())
            );
        }

        if (value < ArabicNumber.MIN_VALUE.intValue()) {
            throw new IllegalArgumentException(
                    format("Arabic numerals smaller than %d are not supported", ArabicNumber.MIN_VALUE.intValue())
            );
        }

        return new ArabicNumber(leftPad(value));
    }

    private static String[] leftPad(int value) {
        String stringValue = Integer.toString(value);

        String leftPad = IntStream.rangeClosed(1, 4 - stringValue.length())
                .mapToObj(index -> "0")
                .collect(joining());

        return (leftPad + stringValue).split("");
    }

}
