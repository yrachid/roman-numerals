package com.zenvia.roman;

import com.zenvia.roman.numerals.CompoundNumeral;
import com.zenvia.roman.numerals.RomanNumeral;

import java.util.function.Function;

import static com.zenvia.roman.numerals.RomanNumeral.C;
import static com.zenvia.roman.numerals.RomanNumeral.D;
import static com.zenvia.roman.numerals.RomanNumeral.I;
import static com.zenvia.roman.numerals.RomanNumeral.L;
import static com.zenvia.roman.numerals.RomanNumeral.M;
import static com.zenvia.roman.numerals.RomanNumeral.V;
import static com.zenvia.roman.numerals.RomanNumeral.X;
import static com.zenvia.roman.numerals.RomanNumeral.compose;

public class PivotBasedPositionalConverter {

    private final Pivots pivots;

    private PivotBasedPositionalConverter(Pivots pivots) {
        this.pivots = pivots;
    }

    public static final class Pivots {

        public static final Pivots UNIT = new Pivots(I, V, X, Function.identity());
        public static final Pivots TENS = new Pivots(X, L, C, n -> n / 10);
        public static final Pivots HUNDRED = new Pivots(C, D, M, n -> n / 100);

        private final RomanNumeral left;
        private final RomanNumeral middle;
        private final RomanNumeral right;
        private final Function<Integer, Integer> repetitionStrategy;

        private Pivots(RomanNumeral left, RomanNumeral middle, RomanNumeral right, Function<Integer, Integer> repetitionStrategy) {
            this.left = left;
            this.middle = middle;
            this.right = right;
            this.repetitionStrategy = repetitionStrategy;
        }

        boolean isBetweenLeftAndMiddlePredecessor(int value) {
            return value < middle.intValue() - left.intValue();
        }

        boolean isBetweenMiddleAndRightPredecessor(int value) {
            return value > middle.intValue() && value < right.intValue() - left.intValue();
        }

        boolean isMiddlePredecessor(int value) {
            return value == middle.intValue() - left.intValue();
        }

        boolean isRightPredecessor(int value) {
            return value == right.intValue() - left.intValue();
        }

        boolean isMiddle(int value) {
            return value == middle.intValue();
        }
    }

    public CompoundNumeral convert(int unit) {

        if (unit == 0) {
            return CompoundNumeral.empty();
        }

        if (pivots.isBetweenLeftAndMiddlePredecessor(unit)) {
            return pivots.left.repeat(pivots.repetitionStrategy.apply(unit));
        }

        if (pivots.isBetweenMiddleAndRightPredecessor(unit)) {
            int postfixRepetitions = pivots.repetitionStrategy.apply(unit - pivots.middle.intValue());

            return pivots.middle.concat(pivots.left.repeat(postfixRepetitions));
        }

        if (pivots.isMiddlePredecessor(unit)) {
            return compose(pivots.left, pivots.middle);
        }

        if (pivots.isRightPredecessor(unit)) {
            return compose(pivots.left, pivots.right);
        }

        if (pivots.isMiddle(unit)) {
            return pivots.middle.identity();
        }

        return pivots.right.identity();
    }

    public static PivotBasedPositionalConverter pivoting(Pivots pivots) {
        return new PivotBasedPositionalConverter(pivots);
    }

}
