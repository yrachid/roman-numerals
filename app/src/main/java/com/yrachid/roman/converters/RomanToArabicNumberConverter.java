package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;
import com.yrachid.roman.numerals.RomanNumeral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RomanToArabicNumberConverter {

    public static ArabicNumber convert(RomanNumber romanNumber) {
        List<RomanNumeral> numerals = romanNumber.numerals();

        List<Integer> indexes = new ArrayList<>();
        List<RomanPair> pairs = new ArrayList<>();

        for (int i = 0; i < numerals.size() - 1; i++) {
            if (numerals.get(i).intValue() < numerals.get(i + 1).intValue()) {
                RomanPair pair = new RomanPair(
                        numerals.get(i),
                        numerals.get(i + 1)
                );
                pairs.add(pair);
                indexes.add(i);
                indexes.add(i + 1);
            }
        }

        Collections.reverse(indexes);
        List<RomanNumeral> copy = new ArrayList<>(numerals);

        for (int i = 0; i < indexes.size(); i++) {
            copy.remove(indexes.get(i).intValue());
        }

        int subtotal1 = copy.stream().map(RomanNumeral::intValue).reduce(0, Integer::sum);
        int subtotal2 = pairs.stream().map(RomanPair::toInt).reduce(0, Integer::sum);

        return ArabicNumber.of(subtotal1 + subtotal2);
    }

    static final class RomanPair {
        private RomanNumeral left;
        private RomanNumeral right;

        RomanPair(RomanNumeral left, RomanNumeral right) {
            this.left = left;
            this.right = right;
        }

        public int toInt() {
            return right.intValue() - left.intValue();
        }

        @Override
        public String toString() {
            return "RomanPair{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
