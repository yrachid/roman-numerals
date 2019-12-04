package com.zenvia.roman.numeral;

import org.junit.Test;

import static com.zenvia.roman.numeral.RomanNumeral.I;
import static com.zenvia.roman.numeral.RomanNumeral.V;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CompoundNumeralTest {

    @Test
    public void creates_compound_numeral_from_repetition() {
        CompoundNumeral numeral = CompoundNumeral.repeating(I, 3);

        assertThat(numeral.toString(), equalTo("III"));
    }

    @Test
    public void concatenates_single_numeral_to_a_compound_one() {
        CompoundNumeral numeral = CompoundNumeral.concat(I, CompoundNumeral.of(V));

        assertThat(numeral.toString(), equalTo("IV"));
    }
}