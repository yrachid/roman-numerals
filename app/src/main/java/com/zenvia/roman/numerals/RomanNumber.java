package com.zenvia.roman.numerals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public final class RomanNumber {

    private static final RomanNumber EMPTY = new RomanNumber(emptyList());

    private final List<RomanNumeral> numerals;

    private RomanNumber(List<RomanNumeral> numerals) {
        this.numerals = numerals;
    }

    static RomanNumber repeating(RomanNumeral numeral, int times) {
        List<RomanNumeral> numerals = new ArrayList<>();

        for (int i = 0; i < times; i++) {
            numerals.add(numeral);
        }

        return new RomanNumber(numerals);
    }

    static RomanNumber concat(RomanNumeral numeral, RomanNumber romanNumber) {
        List<RomanNumeral> concatenated = new ArrayList<>();
        concatenated.add(numeral);
        concatenated.addAll(romanNumber.numerals);

        return new RomanNumber(concatenated);
    }

    static RomanNumber of(RomanNumeral... numerals) {
        return new RomanNumber(new ArrayList<>(asList(numerals)));
    }

    public static RomanNumber empty() {
        return EMPTY;
    }

    public RomanNumber concat(RomanNumber other) {
        List<RomanNumeral> newNumerals = Stream
                .concat(numerals.stream(), other.numerals.stream())
                .collect(toList());

        return new RomanNumber(newNumerals);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RomanNumber that = (RomanNumber) o;
        return Objects.equals(numerals, that.numerals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerals);
    }

    public String toString() {
        return numerals.stream().map(RomanNumeral::name).collect(joining());
    }

}
