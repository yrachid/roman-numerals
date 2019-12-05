package com.zenvia.roman.converters;

import com.zenvia.roman.numerals.RomanNumber;
import org.junit.Test;

import static com.zenvia.roman.converters.PivotBasedPositionalConverter.Pivots.HUNDRED;
import static com.zenvia.roman.converters.PivotBasedPositionalConverter.Pivots.TENS;
import static com.zenvia.roman.converters.PivotBasedPositionalConverter.Pivots.UNIT;
import static com.zenvia.roman.converters.PivotBasedPositionalConverter.pivoting;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PivotsBasedPositionalConverterTest {

    @Test
    public void returns_empty_when_value_is_zero_regardless_of_pivoting_strategy() {
        assertThat(pivoting(UNIT).convert(0), equalTo(RomanNumber.empty()));
        assertThat(pivoting(TENS).convert(0), equalTo(RomanNumber.empty()));
        assertThat(pivoting(HUNDRED).convert(0), equalTo(RomanNumber.empty()));
    }

    @Test
    public void converts_units_using_the_units_pivot() {

        PivotBasedPositionalConverter pivotConverter = pivoting(UNIT);

        RomanNumber one = pivotConverter.convert(1);
        RomanNumber four = pivotConverter.convert(4);
        RomanNumber five = pivotConverter.convert(5);
        RomanNumber seven = pivotConverter.convert(7);
        RomanNumber nine = pivotConverter.convert(9);
        RomanNumber ten = pivotConverter.convert(10);

        assertThat(one.toString(), equalTo("I"));
        assertThat(four.toString(), equalTo("IV"));
        assertThat(five.toString(), equalTo("V"));
        assertThat(seven.toString(), equalTo("VII"));
        assertThat(nine.toString(), equalTo("IX"));
        assertThat(ten.toString(), equalTo("X"));
    }

    @Test
    public void converts_tens_using_tens_pivot() {

        PivotBasedPositionalConverter pivotConverter = pivoting(TENS);

        RomanNumber ten = pivotConverter.convert(10);
        RomanNumber thirty = pivotConverter.convert(30);
        RomanNumber forty = pivotConverter.convert(40);
        RomanNumber fifty = pivotConverter.convert(50);
        RomanNumber seventy = pivotConverter.convert(70);
        RomanNumber ninety = pivotConverter.convert(90);
        RomanNumber oneHundred = pivotConverter.convert(100);

        assertThat(ten.toString(), equalTo("X"));
        assertThat(thirty.toString(), equalTo("XXX"));
        assertThat(forty.toString(), equalTo("XL"));
        assertThat(fifty.toString(), equalTo("L"));
        assertThat(seventy.toString(), equalTo("LXX"));
        assertThat(ninety.toString(), equalTo("XC"));
        assertThat(oneHundred.toString(), equalTo("C"));
    }

    @Test
    public void converts_hundred_using_hundred_pivot() {

        PivotBasedPositionalConverter pivotConverter = pivoting(HUNDRED);

        RomanNumber oneHundred = pivotConverter.convert(100);
        RomanNumber threeHundred = pivotConverter.convert(300);
        RomanNumber fourHundred = pivotConverter.convert(400);
        RomanNumber fiveHundred = pivotConverter.convert(500);
        RomanNumber sevenHundred = pivotConverter.convert(700);
        RomanNumber nineHundred = pivotConverter.convert(900);
        RomanNumber oneThousand = pivotConverter.convert(1000);

        assertThat(oneHundred.toString(), equalTo("C"));
        assertThat(threeHundred.toString(), equalTo("CCC"));
        assertThat(fourHundred.toString(), equalTo("CD"));
        assertThat(fiveHundred.toString(), equalTo("D"));
        assertThat(sevenHundred.toString(), equalTo("DCC"));
        assertThat(nineHundred.toString(), equalTo("CM"));
        assertThat(oneThousand.toString(), equalTo("M"));
    }
}