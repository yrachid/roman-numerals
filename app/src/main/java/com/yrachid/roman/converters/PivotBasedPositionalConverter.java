package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.RomanNumber;

import static com.yrachid.roman.numerals.RomanNumeral.compose;

public class PivotBasedPositionalConverter {

    private final Pivots pivots;

    private PivotBasedPositionalConverter(Pivots pivots) {
        this.pivots = pivots;
    }

    public RomanNumber convert(int unit) {

        if (unit == 0) {
            return RomanNumber.empty();
        }

        if (pivots.isBetweenLeftAndMiddlePredecessor(unit)) {
            return pivots.left().repeat(pivots.repetitionStrategy().apply(unit));
        }

        if (pivots.isBetweenMiddleAndRightPredecessor(unit)) {
            int postfixRepetitions = pivots.repetitionStrategy().apply(unit - pivots.middle().intValue());

            return pivots.middle().concat(pivots.left().repeat(postfixRepetitions));
        }

        if (pivots.isMiddlePredecessor(unit)) {
            return compose(pivots.left(), pivots.middle());
        }

        if (pivots.isRightPredecessor(unit)) {
            return compose(pivots.left(), pivots.right());
        }

        if (pivots.isMiddle(unit)) {
            return pivots.middle().identity();
        }

        return pivots.right().identity();
    }

    public static PivotBasedPositionalConverter pivoting(Pivots pivots) {
        return new PivotBasedPositionalConverter(pivots);
    }

}
