package com.yrachid.roman.input;

import com.yrachid.roman.numerals.RomanNumber;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.yrachid.roman.numerals.RomanNumeral.C;
import static com.yrachid.roman.numerals.RomanNumeral.I;
import static com.yrachid.roman.numerals.RomanNumeral.L;
import static com.yrachid.roman.numerals.RomanNumeral.M;
import static com.yrachid.roman.numerals.RomanNumeral.V;
import static com.yrachid.roman.numerals.RomanNumeral.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RomanNumberParserTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void fails_when_parsing_null() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Failed to parse roman number from null");

        RomanNumberParser.parse(null);
    }

    @Test
    public void fails_when_parsing_value_that_is_not_a_roman_number() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Failed to parse roman number from ABC");

        RomanNumberParser.parse("ABC");
    }

    @Test
    public void parses_single_numerals() {
        assertThat(RomanNumberParser.parse("I"), equalTo(RomanNumber.of(I)));
        assertThat(RomanNumberParser.parse("V"), equalTo(RomanNumber.of(V)));
        assertThat(RomanNumberParser.parse("X"), equalTo(RomanNumber.of(X)));
    }

    @Test
    public void parses_compound_numbers() {
        assertThat(RomanNumberParser.parse("MMCCCXXXIII"), equalTo(RomanNumber.of(M, M, C, C, C, X, X, X, I, I, I)));
        assertThat(RomanNumberParser.parse("XLIX"), equalTo(RomanNumber.of(X, L, I, X)));
    }
}