package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;
import com.yrachid.roman.numerals.RomanNumeral;
import org.junit.Test;

import static com.yrachid.roman.numerals.RomanNumeral.C;
import static com.yrachid.roman.numerals.RomanNumeral.D;
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
        assertThat(conversionOf(L, X, X, I, X), equalTo(79));
        assertThat(conversionOf(C, M, L, I, X), equalTo(959));
        assertThat(conversionOf(M, D, C, C, L, X, X, I, X), equalTo(1779));
        assertThat(conversionOf(M, C, M, L, X), equalTo(1960));
        assertThat(conversionOf(M, C, M), equalTo(1900));
        assertThat(conversionOf(M, M, M), equalTo(3000));
    }

    @Test
    public void converts_numbers_with_even_sizes() {
        assertThat(conversionOf(M, M, I, X), equalTo(2009));
        assertThat(conversionOf(M, M, X, I), equalTo(2011));
        assertThat(conversionOf(M, C, M, L), equalTo(1950));
        assertThat(conversionOf(M, C, M, L, I, X), equalTo(1959));
        assertThat(conversionOf(M, C, M, L, X, X, I, X), equalTo(1979));
    }

    @Test
    public void converts_numbers_with_size_3() {
        assertThat(conversionOf(X, L, I), equalTo(41));
        assertThat(conversionOf(X, L, V), equalTo(45));
        assertThat(conversionOf(X, C, I), equalTo(91));
        assertThat(conversionOf(X, C, V), equalTo(95));
        assertThat(conversionOf(C, D, I), equalTo(401));
        assertThat(conversionOf(C, D, V), equalTo(405));
        assertThat(conversionOf(C, D, X), equalTo(410));
    }

    @Test
    public void converts_numbers_with_size_4() {
        assertThat(conversionOf(C, X, L, I), equalTo(141));
    }

    @Test
    public void converts_numbers_with_size_5() {
        assertThat(conversionOf(M, D, X, C, I), equalTo(1591));
    }

    @Test
    public void converts_number_with_size_6() {
        assertThat(conversionOf(C, X, L, I, I, I), equalTo(143));
        assertThat(conversionOf(C, X, L, V, I, I), equalTo(147));
    }

    @Test
    public void converts_numbers_with_size_7() {
        assertThat(conversionOf(C, C, X, L, I, I, I), equalTo(243));
        assertThat(conversionOf(C, C, X, C, I, I, I), equalTo(293));
        assertThat(conversionOf(C, D, X, V, I, I, I), equalTo(418));
        assertThat(conversionOf(M, D, X, C, V, I, I), equalTo(1597));
        assertThat(conversionOf(M, M, C, M, X, C, I), equalTo(2991));
    }

    @Test
    public void converts_numbers_with_size_8() {
        assertThat(conversionOf(M, M, D, X, C, V, I, I), equalTo(2597));
    }

    @Test
    public void converts_numbers_with_size_9() {
        assertThat(conversionOf(C, D, X, X, X, V, I, I, I), equalTo(438));
        assertThat(conversionOf(C, D, L, X, X, V, I, I, I), equalTo(478));
        assertThat(conversionOf(C, D, L, X, X, X, I, I, I), equalTo(483));
        assertThat(conversionOf(M, M, D, C, C, C, X, C, I), equalTo(2891));
    }

    @Test
    public void converts_numbers_with_size_10() {
        assertThat(conversionOf(M, M, D, C, C, X, L, I, I, I), equalTo(2743));
    }

    @Test
    public void converts_numbers_with_size_11() {
        assertThat(conversionOf(M, M, D, C, C, C, X, C, I, I, I), equalTo(2893));
    }

    @Test
    public void converts_a_large_number() {
        assertThat(conversionOf(M, M, C, C, C, X, X, X, I, I, I), equalTo(2333));
    }

    private int conversionOf(RomanNumeral numeral, RomanNumeral... numerals) {
        return RomanToArabicNumberConverter.convert(RomanNumber.of(numeral, numerals)).intValue();
    }
}