package com.zenvia.roman;

import com.zenvia.roman.numerals.CompoundNumeral;
import org.junit.Test;

import static com.zenvia.roman.PivotBasedPositionalConverter.Pivots.HUNDRED;
import static com.zenvia.roman.PivotBasedPositionalConverter.Pivots.TENS;
import static com.zenvia.roman.PivotBasedPositionalConverter.Pivots.UNIT;
import static com.zenvia.roman.PivotBasedPositionalConverter.pivoting;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PivotsBasedPositionalConverterTest {

    @Test
    public void returns_empty_when_value_is_zero_regardless_of_pivoting_strategy() {
        assertThat(pivoting(UNIT).convert(0), equalTo(CompoundNumeral.empty()));
        assertThat(pivoting(TENS).convert(0), equalTo(CompoundNumeral.empty()));
        assertThat(pivoting(HUNDRED).convert(0), equalTo(CompoundNumeral.empty()));
    }

    @Test
    public void converts_units_using_the_units_pivot() {

        PivotBasedPositionalConverter pivotConverter = pivoting(UNIT);

        CompoundNumeral one = pivotConverter.convert(1);
        CompoundNumeral four = pivotConverter.convert(4);
        CompoundNumeral five = pivotConverter.convert(5);
        CompoundNumeral seven = pivotConverter.convert(7);
        CompoundNumeral nine = pivotConverter.convert(9);
        CompoundNumeral ten = pivotConverter.convert(10);

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

        CompoundNumeral ten = pivotConverter.convert(10);
        CompoundNumeral thirty = pivotConverter.convert(30);
        CompoundNumeral forty = pivotConverter.convert(40);
        CompoundNumeral fifty = pivotConverter.convert(50);
        CompoundNumeral seventy = pivotConverter.convert(70);
        CompoundNumeral ninety = pivotConverter.convert(90);
        CompoundNumeral oneHundred = pivotConverter.convert(100);

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

        CompoundNumeral oneHundred = pivotConverter.convert(100);
        CompoundNumeral threeHundred = pivotConverter.convert(300);
        CompoundNumeral fourHundred = pivotConverter.convert(400);
        CompoundNumeral fiveHundred = pivotConverter.convert(500);
        CompoundNumeral sevenHundred = pivotConverter.convert(700);
        CompoundNumeral nineHundred = pivotConverter.convert(900);
        CompoundNumeral oneThousand = pivotConverter.convert(1000);

        assertThat(oneHundred.toString(), equalTo("C"));
        assertThat(threeHundred.toString(), equalTo("CCC"));
        assertThat(fourHundred.toString(), equalTo("CD"));
        assertThat(fiveHundred.toString(), equalTo("D"));
        assertThat(sevenHundred.toString(), equalTo("DCC"));
        assertThat(nineHundred.toString(), equalTo("CM"));
        assertThat(oneThousand.toString(), equalTo("M"));
    }
}