package com.yrachid.roman.numerals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.yrachid.roman.numerals.RomanNumeral.I;
import static com.yrachid.roman.numerals.RomanNumeral.V;
import static com.yrachid.roman.numerals.RomanNumeral.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RomanNumberTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void creates_compound_numeral_from_repetition() {
        RomanNumber three = RomanNumber.repeating(I, 3);

        assertThat(three.toString(), equalTo("III"));
    }

    @Test
    public void concatenates_single_numeral_to_a_compound_one() {
        RomanNumber numeral = RomanNumber.concat(I, RomanNumber.of(V));

        assertThat(numeral.toString(), equalTo("IV"));
    }

    @Test
    public void concatenates_another_compound_numeral() {
        RomanNumber four = RomanNumber.of(I, V);
        RomanNumber twenty = RomanNumber.of(X, X);

        RomanNumber twentyFour = twenty.concat(four);

        assertThat(twentyFour.toString(), equalTo("XXIV"));
    }

    @Test
    public void fails_when_repeating_a_numeral_zero_times() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("A roman number must have at least one numeral");

        RomanNumber.repeating(I, 0);
    }
}