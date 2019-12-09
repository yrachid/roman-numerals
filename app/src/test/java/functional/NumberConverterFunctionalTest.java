package functional;

import com.yrachid.roman.Application;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class NumberConverterFunctionalTest {

    @Test
    public void outputs_one_conversion_per_line() {
        List<String> output = new ArrayList<>();

        new Application(output::add).run(new String[]{
                "1", "10", "49",
                "lizard", "0", "3001"
        });

        assertThat(output.get(0), equalTo(""));
        assertThat(output.get(1), equalTo("Received Args: [1, 10, 49, lizard, 0, 3001]"));
        assertThat(output.get(2), equalTo(""));
        assertThat(output.get(3), equalTo("1\t:\tI"));
        assertThat(output.get(4), equalTo("10\t:\tX"));
        assertThat(output.get(5), equalTo("49\t:\tXLIX"));
        assertThat(output.get(6), equalTo("lizard\t:\tInvalid input: Input must be an integer number within [0001, 3000] or a roman number"));
        assertThat(output.get(7), equalTo("0\t:\tInvalid input: Arabic numerals smaller than 1 are not supported"));
        assertThat(output.get(8), equalTo("3001\t:\tInvalid input: Arabic numerals greater than 3000 are not supported"));
    }

    @Test
    public void converts_romans_to_arabics_as_well() {
        List<String> output = new ArrayList<>();

        new Application(output::add).run(new String[]{
                "I", "XLIX", "CCC", "MMCCCXXXIII"
        });

        assertThat(output.get(0), equalTo(""));
        assertThat(output.get(1), equalTo("Received Args: [I, XLIX, CCC, MMCCCXXXIII]"));
        assertThat(output.get(2), equalTo(""));
        assertThat(output.get(3), equalTo("I\t:\t0001"));
        assertThat(output.get(4), equalTo("XLIX\t:\t0049"));
        assertThat(output.get(5), equalTo("CCC\t:\t0300"));
        assertThat(output.get(6), equalTo("MMCCCXXXIII\t:\t2333"));
    }
}
