package com.yrachid.roman.input;

import com.yrachid.roman.numerals.RomanNumber;
import com.yrachid.roman.numerals.RomanNumeral;

import java.util.stream.Stream;

import static com.yrachid.roman.input.SingleParameterParser.ROMAN_NUMBER_PATTERN;

public class RomanNumberParser {

    public static RomanNumber parse(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Failed to parse roman number from null");
        }

        if (!value.matches(ROMAN_NUMBER_PATTERN)) {
            throw new IllegalArgumentException("Failed to parse roman number from " + value);
        }

        return Stream.of(value.split(""))
                .map(RomanNumberParser::toNumeral)
                .map(RomanNumber::of)
                .reduce(RomanNumber::concat)
                .get();
    }

    private static RomanNumeral toNumeral(String value) {
        return Stream.of(RomanNumeral.values())
                .filter(numeral -> numeral.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid roman numeral: " + value));
    }
}
