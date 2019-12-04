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
}