package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;
import com.yrachid.roman.numerals.RomanNumeral;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RomanToArabicNumberConverter {

    public static ArabicNumber convert(RomanNumber romanNumber) {
        List<RomanNumeral> numerals = romanNumber.numerals();

        SubtractionPairs subtractionPairs = new SubtractionPairs(numerals);

        int subtotal = IntStream.range(0, numerals.size())
                .mapToObj(IndexedNumeral.fromIndex(numerals))
                .filter(subtractionPairs::notContainsIndex)
                .map(IndexedNumeral::toNumeral)
                .mapToInt(RomanNumeral::intValue)
                .reduce(0, Integer::sum);

        int subtractionPairsSubtotal = subtractionPairs.total();

        return ArabicNumber.of(subtotal + subtractionPairsSubtotal);
    }

    private static final class SubtractionPairs {

        private final List<IndexedRomanPair> pairs;

        SubtractionPairs(List<RomanNumeral> numerals) {
            this.pairs = IntStream.range(0, numerals.size() - 1)
                    .filter(isSubtractionPair(numerals))
                    .mapToObj(idx -> new IndexedRomanPair(
                                    idx,
                                    numerals.get(idx),
                                    numerals.get(idx + 1)
                            )
                    )
                    .collect(Collectors.toList());
        }

        IntPredicate isSubtractionPair(List<RomanNumeral> numerals) {
            return index -> numerals.get(index).compareTo(numerals.get(index + 1)) < 0;
        }

        boolean notContainsIndex(IndexedNumeral numeral) {
            return pairs.stream().flatMap(IndexedRomanPair::indices).noneMatch(idx -> idx == numeral.index);
        }

        int total() {
            return pairs.stream().map(IndexedRomanPair::toInt).reduce(0, Integer::sum);
        }
    }

    private static final class IndexedNumeral {
        private final int index;
        private final RomanNumeral numeral;

        static IntFunction<IndexedNumeral> fromIndex(List<RomanNumeral> numerals) {
            return index -> new IndexedNumeral(index, numerals.get(index));
        }

        IndexedNumeral(int index, RomanNumeral numeral) {
            this.index = index;
            this.numeral = numeral;
        }

        RomanNumeral toNumeral() {
            return numeral;
        }
    }

    private static final class IndexedRomanPair {
        private final int leftIndex;
        private final RomanNumeral left;
        private final RomanNumeral right;

        IndexedRomanPair(int leftIndex, RomanNumeral left, RomanNumeral right) {
            this.leftIndex = leftIndex;
            this.left = left;
            this.right = right;
        }

        int toInt() {
            return right.intValue() - left.intValue();
        }

        Stream<Integer> indices() {
            return Stream.of(leftIndex, leftIndex + 1);
        }
    }
}
