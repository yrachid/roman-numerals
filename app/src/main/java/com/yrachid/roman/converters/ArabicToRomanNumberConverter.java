package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;

import java.util.Optional;
import java.util.stream.Stream;

import static com.yrachid.roman.converters.PivotBasedPositionalConverter.pivoting;
import static com.yrachid.roman.converters.Pivots.HUNDRED;
import static com.yrachid.roman.converters.Pivots.TENS;
import static com.yrachid.roman.converters.Pivots.UNIT;
import static com.yrachid.roman.numerals.RomanNumeral.M;

public class ArabicToRomanNumberConverter {

    public static RomanNumber convert(ArabicNumber arabicNumber) {

        Optional<RomanNumber> thousand = arabicNumber.thousand() > 0
                ? Optional.of(M.repeat(arabicNumber.thousand()))
                : Optional.empty();

        Optional<RomanNumber> hundred = convertIfPositionIsNotZeroed(HUNDRED, arabicNumber.hundred() * 100);
        Optional<RomanNumber> tens = convertIfPositionIsNotZeroed(TENS, arabicNumber.tens() * 10);
        Optional<RomanNumber> unit = convertIfPositionIsNotZeroed(UNIT, arabicNumber.unit());

        return Stream.of(thousand, hundred, tens, unit)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(RomanNumber::concat)
                .orElseThrow(() -> new IllegalStateException("The roman system does not support zero"));
    }

    private static Optional<RomanNumber> convertIfPositionIsNotZeroed(Pivots pivots, int value) {
        return value > 0
                ? Optional.of(pivoting(pivots).convert(value))
                : Optional.empty();
    }
}
