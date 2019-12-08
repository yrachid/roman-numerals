package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;
import org.junit.Test;

import static com.yrachid.roman.numerals.RomanNumeral.C;
import static com.yrachid.roman.numerals.RomanNumeral.I;
import static com.yrachid.roman.numerals.RomanNumeral.L;
import static com.yrachid.roman.numerals.RomanNumeral.M;
import static com.yrachid.roman.numerals.RomanNumeral.V;
import static com.yrachid.roman.numerals.RomanNumeral.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RomanToArabicNumberConverterTest {

    @Test
    public void converts_single_numerals() {
        ArabicNumber one = RomanToArabicNumberConverter.convert(RomanNumber.of(I));

        assertThat(one, equalTo(ArabicNumber.of(1)));
    }

    @Test
    public void converts_couple() {
        ArabicNumber two = RomanToArabicNumberConverter.convert(RomanNumber.of(I, I));

        assertThat(two, equalTo(ArabicNumber.of(2)));
    }

    @Test
    public void converts_correctly_when_a_pair_must_be_subtracted_instead_of_added() {
        ArabicNumber four = RomanToArabicNumberConverter.convert(RomanNumber.of(I, V));

        assertThat(four, equalTo(ArabicNumber.of(4)));
    }

    @Test
    public void converts_numbers_with_odd_sizes() {
        ArabicNumber threeThousand = RomanToArabicNumberConverter.convert(RomanNumber.of(M, M, M));
        ArabicNumber seventyNine = RomanToArabicNumberConverter.convert(RomanNumber.of(L, X, X, I, X));

        assertThat(threeThousand, equalTo(ArabicNumber.of(3000)));
        assertThat(seventyNine, equalTo(ArabicNumber.of(79)));
    }

    @Test
    public void converts_numbers_with_even_sizes() {
        ArabicNumber twentyEleven = RomanToArabicNumberConverter.convert(RomanNumber.of(M, M, X, I));
        ArabicNumber nineteenSeventyNine = RomanToArabicNumberConverter.convert(RomanNumber.of(M, C, M, L, X, X, I, X));

//        (
//                M, C, -> 1100
//                M, L, -> 1050
//                X, X, -> 20
//                I, X -> 9
//        );

        assertThat(twentyEleven, equalTo(ArabicNumber.of(2011)));
        assertThat(nineteenSeventyNine, equalTo(1979));
    }

    @Test
    public void converts_a_large_number() {
        ArabicNumber four = RomanToArabicNumberConverter.convert(RomanNumber.of(M, M, C, C, C, X, X, X, I, I, I));

        assertThat(four, equalTo(ArabicNumber.of(2333)));
    }
}