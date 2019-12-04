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
    public void converts_1_to_I() {
        assertThat(RomanNumeralConverter.convert(1), equalTo("I"));
    }

    @Test
    public void converts_5_to_V() {
        assertThat(RomanNumeralConverter.convert(5), equalTo("V"));
    }

    @Test
    public void converts_10_to_X() {
       assertThat(RomanNumeralConverter.convert(10), equalTo("X"));
    }

    @Test
    public void converts_50_to_L() {
        assertThat(RomanNumeralConverter.convert(50), equalTo("L"));
    }

    @Test
    public void converts_100_to_C() {
        assertThat(RomanNumeralConverter.convert(100), equalTo("C"));
    }

    @Test
    public void converts_500_to_D() {
        assertThat(RomanNumeralConverter.convert(500), equalTo("D"));
    }

    @Test
    public void converts_1000_to_M() {
        assertThat(RomanNumeralConverter.convert(1000), equalTo("M"));
    }
}