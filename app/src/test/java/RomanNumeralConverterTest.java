import org.junit.Test;

import static com.zenvia.roman.RomanNumeralConverter.convert;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RomanNumeralConverterTest {

    @Test
    public void must_not_convert_values_greater_than_3000_or_smaller_than_1() {
        assertThat(convert(3001), equalTo("ERROR"));
        assertThat(convert(0), equalTo("ERROR"));
    }

    @Test
    public void converts_values_with_direct_equivalent_numerals() {
        assertThat(convert(1), equalTo("I"));
        assertThat(convert(5), equalTo("V"));
        assertThat(convert(10), equalTo("X"));
        assertThat(convert(50), equalTo("L"));
        assertThat(convert(100), equalTo("C"));
        assertThat(convert(500), equalTo("D"));
        assertThat(convert(1000), equalTo("M"));
    }

    @Test
    public void converts_units() {
        assertThat(convert(1), equalTo("I"));
        assertThat(convert(2), equalTo("II"));
        assertThat(convert(3), equalTo("III"));
        assertThat(convert(4), equalTo("IV"));
        assertThat(convert(5), equalTo("V"));
        assertThat(convert(6), equalTo("VI"));
        assertThat(convert(7), equalTo("VII"));
        assertThat(convert(8), equalTo("VIII"));
        assertThat(convert(9), equalTo("IX"));
        assertThat(convert(10), equalTo("X"));
    }

    @Test
    public void converts_from_ten_to_twenty() {
        assertThat(convert(10), equalTo("X"));
        assertThat(convert(11), equalTo("XI"));
        assertThat(convert(12), equalTo("XII"));
        assertThat(convert(13), equalTo("XIII"));
        assertThat(convert(14), equalTo("XIV"));
        assertThat(convert(15), equalTo("XV"));
        assertThat(convert(16), equalTo("XVI"));
        assertThat(convert(17), equalTo("XVII"));
        assertThat(convert(18), equalTo("XVIII"));
        assertThat(convert(19), equalTo("XIX"));
        assertThat(convert(20), equalTo("XX"));
    }

    @Test
    public void converts_numbers_with_both_tens_and_units() {
        assertThat(convert(21), equalTo("XXI"));
        assertThat(convert(24), equalTo("XXIV"));
        assertThat(convert(29), equalTo("XXIX"));
    }
}