import com.zenvia.roman.RomanNumeralConverter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RomanNumeralConverterTest {

    @Test
    public void must_not_convert_values_greater_than_3000() {
        assertThat(RomanNumeralConverter.convert(3001), equalTo("ERROR"));
        assertThat(RomanNumeralConverter.convert(3000), equalTo("3000"));
    }

    @Test
    public void converts_single_numerals() {
        assertThat(RomanNumeralConverter.convert(1), equalTo("I"));
        assertThat(RomanNumeralConverter.convert(5), equalTo("V"));
        assertThat(RomanNumeralConverter.convert(10), equalTo("X"));
        assertThat(RomanNumeralConverter.convert(50), equalTo("L"));
        assertThat(RomanNumeralConverter.convert(100), equalTo("C"));
        assertThat(RomanNumeralConverter.convert(500), equalTo("D"));
        assertThat(RomanNumeralConverter.convert(1000), equalTo("M"));
    }

}