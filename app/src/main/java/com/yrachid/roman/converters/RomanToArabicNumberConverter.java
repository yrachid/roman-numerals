package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;
import com.yrachid.roman.numerals.RomanNumeral;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static com.yrachid.roman.numerals.RomanNumeral.M;

public class RomanToArabicNumberConverter {

    public static ArabicNumber convert(RomanNumber romanNumber) {
        List<RomanNumeral> numerals = romanNumber.numerals();
        int numberSize = numerals.size();

        if (numberSize == 1) {
            return ArabicNumber.of(numerals.get(0).intValue());
        }

        if (numberSize == 2) {
            return ArabicNumber.of(calculateCoupleValue(numerals.get(0), numerals.get(1)));
        }

        OptionalInt lastThousandIndex = lastIndexOfM(numerals);

        if (!lastThousandIndex.isPresent()) {
            return ArabicNumber.of(sumNumerals(numerals, 0, numberSize - 2));
        }

        List<RomanNumeral> thousands = numerals.subList(0, lastThousandIndex.getAsInt() + 1);
        List<RomanNumeral> rest = numerals.subList(lastThousandIndex.getAsInt() + 1, numberSize);

        int calculatedThousands = sumNumerals(thousands, 0, thousands.size() - 2);
        int calculatedRest = sumNumerals(rest, 0, rest.size() - 2);

        return ArabicNumber.of(calculatedThousands + calculatedRest);
    }

    private static int sumNumerals(List<RomanNumeral> numerals, int result, int index) {
        if (iterationFinished(index)) {
            return hasEvenSize(numerals)
                    ? result
                    : result + numerals.get(0).intValue();
        }

        int currentResult = calculateCoupleValue(
                numerals.get(index),
                numerals.get(index + 1)
        );

        return sumNumerals(numerals, result + currentResult, index - 2);
    }

    private static int calculateCoupleValue(RomanNumeral left, RomanNumeral right) {
        return left.intValue() < right.intValue()
                ? right.intValue() - left.intValue()
                : right.intValue() + left.intValue();
    }

    private static OptionalInt lastIndexOfM(List<RomanNumeral> numerals) {
        IntBinaryOperator preserveLastIndex = (currentlyStoredIndex, currentIndex) -> currentIndex;

        return IntStream.range(0, numerals.size())
                .mapToObj(IndexedNumeral.indexingNumeralOf(numerals))
                .filter(IndexedNumeral::isM)
                .mapToInt(IndexedNumeral::toIndex)
                .reduce(preserveLastIndex);
    }

    private static boolean iterationFinished(int index) {
        return index < 0;
    }

    private static boolean hasEvenSize(List<RomanNumeral> numerals) {
        return numerals.size() % 2 == 0;
    }

    private static final class IndexedNumeral {
        private final int index;
        private final RomanNumeral numeral;

        static IntFunction<IndexedNumeral> indexingNumeralOf(List<RomanNumeral> numerals) {
            return index -> new IndexedNumeral(index, numerals.get(index));
        }

        IndexedNumeral(int index, RomanNumeral numeral) {
            this.index = index;
            this.numeral = numeral;
        }

        private boolean isM() {
            return numeral == M;
        }

        private int toIndex() {
            return index;
        }
    }
}
