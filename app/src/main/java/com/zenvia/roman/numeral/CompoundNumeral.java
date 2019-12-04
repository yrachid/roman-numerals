package com.zenvia.roman.numeral;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public final class CompoundNumeral {

    private static final CompoundNumeral EMPTY = new CompoundNumeral(emptyList());

    private final List<RomanNumeral> numerals;

    private CompoundNumeral(List<RomanNumeral> numerals) {
        this.numerals = numerals;
    }

    static CompoundNumeral repeating(RomanNumeral numeral, int times) {
        List<RomanNumeral> numerals = new ArrayList<>();

        for (int i = 0; i < times; i++) {
            numerals.add(numeral);
        }

        return new CompoundNumeral(numerals);
    }

    static CompoundNumeral concat(RomanNumeral numeral, CompoundNumeral compoundNumeral) {
        List<RomanNumeral> concatenated = new ArrayList<>();
        concatenated.add(numeral);
        concatenated.addAll(compoundNumeral.numerals);

        return new CompoundNumeral(concatenated);
    }

    static CompoundNumeral of(RomanNumeral... numerals) {
        return new CompoundNumeral(new ArrayList<>(asList(numerals)));
    }

    public static CompoundNumeral empty() {
        return EMPTY;
    }

    public CompoundNumeral concat(CompoundNumeral other) {
        List<RomanNumeral> newNumerals = Stream
                .concat(numerals.stream(), other.numerals.stream())
                .collect(toList());

        return new CompoundNumeral(newNumerals);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompoundNumeral that = (CompoundNumeral) o;
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
