package com.zenvia.roman.numeral;

import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.stream.Collectors.joining;

public class ArabicNumeral {

    private static final int UNIT_INDEX = 3;
    private static final int TENS_INDEX = 2;
    private static final int HUNDRED_INDEX = 1;
    private static final int THOUSAND_INDEX = 0;

    private static final ArabicNumeral MAX_VALUE = new ArabicNumeral(new String[]{"3", "0", "0", "0"});
    private static final ArabicNumeral MIN_VALUE = new ArabicNumeral(new String[]{"0", "0", "0", "1"});

    private final String[] positionalValues;

    private ArabicNumeral(String[] positionalValues) {
        this.positionalValues = positionalValues;
    }

    public int intValue() {
        return parseInt(join("", positionalValues));
    }

    public int unit() {
        return valueAt(UNIT_INDEX);
    }

    public int tens() {
        return valueAt(TENS_INDEX);
    }

    public int hundred() {
        return valueAt(HUNDRED_INDEX);
    }

    public int thousand() {
        return valueAt(THOUSAND_INDEX);
    }

    private int valueAt(int index) {
        return parseInt(positionalValues[index]);
    }

    public static ArabicNumeral of(int value) {

        if (value > ArabicNumeral.MAX_VALUE.intValue()) {
            throw new IllegalArgumentException(
                    format("Arabic numerals greater than %d are not supported", ArabicNumeral.MAX_VALUE.intValue())
            );
        }

        if (value < ArabicNumeral.MIN_VALUE.intValue()) {
            throw new IllegalArgumentException(
                    format("Arabic numerals smaller than %d are not supported", ArabicNumeral.MIN_VALUE.intValue())
            );
        }

        return new ArabicNumeral(leftPad(value));
    }

    private static String[] leftPad(int value) {
        String stringValue = Integer.toString(value);

        String leftPad = IntStream.rangeClosed(1, 4 - stringValue.length())
                .mapToObj(index -> "0")
                .collect(joining());

        return (leftPad + stringValue).split("");
    }

}
