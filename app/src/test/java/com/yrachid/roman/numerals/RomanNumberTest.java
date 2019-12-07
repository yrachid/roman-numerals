package com.yrachid.roman.numerals;

import org.junit.Test;

import static com.yrachid.roman.numerals.RomanNumeral.I;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RomanNumberTest {

    @Test
    public void creates_compound_numeral_from_repetition() {
        RomanNumber three = RomanNumber.repeating(I, 3);
        RomanNumber none = RomanNumber.repeating(I, 0);

        assertThat(three.toString(), equalTo("III"));
        assertThat(none, equalTo(RomanNumber.empty()));
    }

    @Test
    public void concatenates_single_numeral_to_a_compound_one() {
        RomanNumber numeral = RomanNumber.concat(I, RomanNumber.of(RomanNumeral.V));

        assertThat(numeral.toString(), equalTo("IV"));
    }

    @Test
    public void concatenates_another_compound_numeral() {
        RomanNumber four = RomanNumber.of(I, RomanNumeral.V);
        RomanNumber twenty = RomanNumber.of(RomanNumeral.X, RomanNumeral.X);

        RomanNumber twentyFour = twenty.concat(four);

        assertThat(twentyFour.toString(), equalTo("XXIV"));
    }

    @Test
    public void empty_compound_yields_no_value() {
        assertThat(RomanNumber.empty().toString(), equalTo(""));
    }
}