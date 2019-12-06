package com.zenvia.roman.converters;

import com.zenvia.roman.numerals.RomanNumeral;

import java.util.function.Function;

import static com.zenvia.roman.numerals.RomanNumeral.C;
import static com.zenvia.roman.numerals.RomanNumeral.D;
import static com.zenvia.roman.numerals.RomanNumeral.I;
import static com.zenvia.roman.numerals.RomanNumeral.L;
import static com.zenvia.roman.numerals.RomanNumeral.M;
import static com.zenvia.roman.numerals.RomanNumeral.V;
import static com.zenvia.roman.numerals.RomanNumeral.X;

final class Pivots {

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

    RomanNumeral left() {
        return left;
    }

    RomanNumeral right() {
        return right;
    }

    RomanNumeral middle() {
        return middle;
    }

    Function<Integer, Integer> repetitionStrategy() {
        return repetitionStrategy;
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
