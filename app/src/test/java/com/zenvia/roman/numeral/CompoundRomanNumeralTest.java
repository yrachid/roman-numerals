package com.zenvia.roman.numeral;

import org.junit.Test;

import static com.zenvia.roman.numeral.RomanNumeral.I;
import static com.zenvia.roman.numeral.RomanNumeral.V;
import static com.zenvia.roman.numeral.RomanNumeral.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CompoundRomanNumeralTest {

    @Test
    public void creates_compound_numeral_from_repetition() {
        CompoundNumeral three = CompoundNumeral.repeating(I, 3);
        CompoundNumeral none = CompoundNumeral.repeating(I, 0);

        assertThat(three.toString(), equalTo("III"));
        assertThat(none, equalTo(CompoundNumeral.empty()));
    }

    @Test
    public void concatenates_single_numeral_to_a_compound_one() {
        CompoundNumeral numeral = CompoundNumeral.concat(I, CompoundNumeral.of(V));

        assertThat(numeral.toString(), equalTo("IV"));
    }

    @Test
    public void concatenates_another_compound_numeral() {
        CompoundNumeral four = CompoundNumeral.of(I, V);
        CompoundNumeral twenty = CompoundNumeral.of(X, X);

        CompoundNumeral twentyFour = twenty.concat(four);

        assertThat(twentyFour.toString(), equalTo("XXIV"));
    }

    @Test
    public void empty_compound_yields_no_value() {
        assertThat(CompoundNumeral.empty().toString(), equalTo(""));
    }
}