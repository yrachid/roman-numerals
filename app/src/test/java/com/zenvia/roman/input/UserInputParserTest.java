package com.zenvia.roman.input;

import com.zenvia.roman.numerals.ArabicNumber;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserInputParserTest {

    @Test
    public void fails_when_arg_array_is_empty() {
        InputParsingResult emptyArgArray = UserInputParser.parse(new String[]{});

        assertThat(emptyArgArray.right().isPresent(), equalTo(false));
        assertFailureMessageOf(emptyArgArray, hasCause("Input is empty"));
    }

    @Test
    public void fails_when_first_position_of_arg_array_is_empty() {
        InputParsingResult emptyParam = UserInputParser.parse(new String[]{""});

        assertThat(emptyParam.right().isPresent(), equalTo(false));
        assertFailureMessageOf(emptyParam, hasCause("Input is empty"));
    }

    @Test
    public void fails_when_first_position_of_arg_array_is_empty_space() {
        InputParsingResult emptySpaceParam = UserInputParser.parse(new String[]{"  "});

        assertThat(emptySpaceParam.right().isPresent(), equalTo(false));
        assertFailureMessageOf(emptySpaceParam, hasCause("Input is empty"));
    }

    @Test
    public void fails_with_null_input() {
        InputParsingResult result = UserInputParser.parse(null);

        assertThat(result.right().isPresent(), equalTo(false));
        assertFailureMessageOf(result, hasCause("Input is empty"));
    }

    @Test
    public void fails_with_non_numeric_input() {
        InputParsingResult nanParam = UserInputParser.parse(new String[]{"A"});

        assertThat(nanParam.right().isPresent(), equalTo(false));
        assertFailureMessageOf(nanParam, hasCause("Input must be an integer number. It must also not be greater than 3000"));
    }

    @Test
    public void fails_with_numbers_beyond_maximum_allowed_value() {
        InputParsingResult hugeNumber = UserInputParser.parse(new String[]{"10000000000000000000000000000000000000000000"});

        assertThat(hugeNumber.right().isPresent(), equalTo(false));
        assertFailureMessageOf(hugeNumber, hasCause("Input must be an integer number. It must also not be greater than 3000"));
    }

    @Test
    public void fails_with_numbers_below_maximum_allowed_value() {
        InputParsingResult zero = UserInputParser.parse(new String[]{"0"});

        assertThat(zero.right().isPresent(), equalTo(false));
        assertFailureMessageOf(zero, hasCause("Arabic numerals smaller than 1 are not supported"));
    }

    @Test
    public void succeeds_with_a_value_within_the_supported_range() {
        InputParsingResult ten = UserInputParser.parse(new String[]{"10"});
        InputParsingResult threeThousand = UserInputParser.parse(new String[]{"3000"});

        assertThat(ten.left().isPresent(), equalTo(false));
        assertThat(ten.right().get(), equalTo(ArabicNumber.of(10)));
        assertThat(threeThousand.right().get(), equalTo(ArabicNumber.of(3000)));
    }

    private void assertFailureMessageOf(InputParsingResult result, Matcher<String> matcher) {
        assertThat(result.left().isPresent(), equalTo(true));
        assertThat(result.left().get().toString(), matcher);
    }

    private Matcher<String> hasCause(String partialMessage) {
        return equalTo("Invalid input: " + partialMessage);
    }
}