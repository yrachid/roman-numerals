package com.yrachid.roman.numerals;

import org.junit.Test;

import static com.yrachid.roman.numerals.RomanNumeral.I;
import static com.yrachid.roman.numerals.RomanNumeral.V;
import static com.yrachid.roman.numerals.RomanNumeral.X;
import static com.yrachid.roman.numerals.RomanNumeral.L;
import static com.yrachid.roman.numerals.RomanNumeral.C;
import static com.yrachid.roman.numerals.RomanNumeral.D;
import static com.yrachid.roman.numerals.RomanNumeral.M;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RomanNumeralTest {

    @Test
    public void roman_numerals_are_represented_with_their_natural_order() {
        assertThat(I.compareTo(V) < 0, is(true));
        assertThat(I.compareTo(X) < 0, is(true));
        assertThat(I.compareTo(L) < 0, is(true));
        assertThat(I.compareTo(C) < 0, is(true));
        assertThat(I.compareTo(D) < 0, is(true));
        assertThat(I.compareTo(M) < 0, is(true));

        assertThat(V.compareTo(X) < 0, is(true));
        assertThat(V.compareTo(L) < 0, is(true));
        assertThat(V.compareTo(C) < 0, is(true));
        assertThat(V.compareTo(D) < 0, is(true));
        assertThat(V.compareTo(M) < 0, is(true));

        assertThat(L.compareTo(C) < 0, is(true));
        assertThat(L.compareTo(D) < 0, is(true));
        assertThat(L.compareTo(M) < 0, is(true));

        assertThat(C.compareTo(D) < 0, is(true));
        assertThat(C.compareTo(M) < 0, is(true));

        assertThat(D.compareTo(M) < 0, is(true));
    }
}