package com.yrachid.roman.converters;

import com.yrachid.roman.numerals.ArabicNumber;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArabicToRomanNumberConverterTest {

    @Test
    public void converts_values_with_direct_equivalent_numerals() {
        converted(1, is("I"));
        converted(5, is("V"));
        converted(10, is("X"));
        converted(50, is("L"));
        converted(100, is("C"));
        converted(500, is("D"));
        converted(1000, is("M"));
    }

    @Test
    public void converts_units() {
        converted(1, is("I"));
        converted(2, is("II"));
        converted(3, is("III"));
        converted(4, is("IV"));
        converted(5, is("V"));
        converted(6, is("VI"));
        converted(7, is("VII"));
        converted(8, is("VIII"));
        converted(9, is("IX"));
        converted(10, is("X"));
    }

    @Test
    public void converts_from_ten_to_twenty() {
        converted(10, is("X"));
        converted(11, is("XI"));
        converted(12, is("XII"));
        converted(13, is("XIII"));
        converted(14, is("XIV"));
        converted(15, is("XV"));
        converted(16, is("XVI"));
        converted(17, is("XVII"));
        converted(18, is("XVIII"));
        converted(19, is("XIX"));
        converted(20, is("XX"));
    }

    @Test
    public void converts_numbers_with_both_tens_and_units() {
        converted(21, is("XXI"));
        converted(24, is("XXIV"));
        converted(29, is("XXIX"));
    }

    @Test
    public void converts_compound_numbers_regardless_of_position() {
        converted(900, is("CM"));
        converted(1814, is("MDCCCXIV"));
        converted(2011, is("MMXI"));
        converted(2301, is("MMCCCI"));
        converted(2901, is("MMCMI"));
        converted(2444, is("MMCDXLIV"));
        converted(2950, is("MMCML"));
        converted(2999, is("MMCMXCIX"));
        converted(3000, is("MMM"));
    }

    private void converted(int value, Matcher<String> matcher) {
        assertThat(ArabicToRomanNumberConverter.convert(ArabicNumber.of(value)).toString(), matcher);
    }
}
