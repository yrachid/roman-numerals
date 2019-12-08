package com.yrachid.roman.numerals;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public final class RomanNumber {

    private final List<RomanNumeral> numerals;

    private RomanNumber(List<RomanNumeral> numerals) {
        if (numerals == null || numerals.isEmpty()) {
            throw new IllegalArgumentException("A roman number must have at least one numeral");
        }

        this.numerals = numerals;
    }

    static RomanNumber repeating(RomanNumeral numeral, int times) {
        return new RomanNumber(
                IntStream.rangeClosed(1, times)
                        .mapToObj(idx -> numeral)
                        .collect(toList())
        );
    }

    public static RomanNumber concat(RomanNumeral numeral, RomanNumber romanNumber) {
        return new RomanNumber(
                Stream.concat(
                        Stream.of(numeral),
                        romanNumber.numerals.stream()
                ).collect(toList())
        );
    }

    public static RomanNumber of(RomanNumeral numeral, RomanNumeral... numerals) {
        return new RomanNumber(
                Stream.concat(
                        Stream.of(numeral),
                        Stream.of(numerals)
                ).collect(toList())
        );
    }

    public RomanNumber concat(RomanNumber other) {
        return new RomanNumber(
                Stream.concat(
                        numerals.stream(),
                        other.numerals.stream()
                ).collect(toList())
        );
    }

    public List<RomanNumeral> numerals() {
        return numerals;
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
