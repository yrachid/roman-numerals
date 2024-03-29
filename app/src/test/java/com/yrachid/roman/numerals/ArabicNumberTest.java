package com.yrachid.roman.numerals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ArabicNumberTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void does_not_support_values_greater_than_3000() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Arabic numerals greater than 3000 are not supported");

        ArabicNumber.of(3001);
    }

    @Test
    public void does_not_support_values_smaller_than_1() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Arabic numerals smaller than 1 are not supported");

        ArabicNumber.of(0);
    }

    @Test
    public void allocates_values_with_multiple_position_layouts() {
        ArabicNumber one = ArabicNumber.of(1);
        ArabicNumber ten = ArabicNumber.of(10);
        ArabicNumber twoHundred = ArabicNumber.of(201);
        ArabicNumber oneThousandAndOne = ArabicNumber.of(1001);

        assertThat(one.unit(), equalTo(1));
        assertThat(one.tens(), equalTo(0));
        assertThat(one.hundred(), equalTo(0));
        assertThat(one.thousand(), equalTo(0));

        assertThat(ten.tens(), equalTo(1));
        assertThat(ten.unit(), equalTo(0));

        assertThat(twoHundred.hundred(), equalTo(2));
        assertThat(twoHundred.tens(), equalTo(0));
        assertThat(twoHundred.unit(), equalTo(1));

        assertThat(oneThousandAndOne.thousand(), equalTo(1));
        assertThat(oneThousandAndOne.hundred(), equalTo(0));
        assertThat(oneThousandAndOne.tens(), equalTo(0));
        assertThat(oneThousandAndOne.unit(), equalTo(1));
    }
}