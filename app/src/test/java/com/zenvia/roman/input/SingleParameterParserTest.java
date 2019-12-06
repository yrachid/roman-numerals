package com.zenvia.roman.input;

import com.zenvia.roman.numerals.ArabicNumber;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SingleParameterParserTest {

    @Test
    public void fails_when_param_is_empty() {
        InputParsingResult empty = SingleParameterParser.parse("");

        assertThat(empty.success().isPresent(), equalTo(false));
        assertFailureMessageOf(empty, hasCause("Input is empty"));
    }

    @Test
    public void fails_when_is_empty_space() {
        InputParsingResult emptySpace = SingleParameterParser.parse(" ");

        assertThat(emptySpace.success().isPresent(), equalTo(false));
        assertFailureMessageOf(emptySpace, hasCause("Input is empty"));
    }

    @Test
    public void fails_when_param_is_null() {
        InputParsingResult nullParam = SingleParameterParser.parse(null);

        assertThat(nullParam.success().isPresent(), equalTo(false));
        assertFailureMessageOf(nullParam, hasCause("Input is empty"));
    }

    @Test
    public void fails_with_non_numeric_input() {
        InputParsingResult nanParam = SingleParameterParser.parse("A");

        assertThat(nanParam.success().isPresent(), equalTo(false));
        assertFailureMessageOf(nanParam, hasCause("Input must be an integer number. It must also not be greater than 3000"));
    }

    @Test
    public void fails_with_numbers_beyond_maximum_allowed_value() {
        InputParsingResult hugeNumber = SingleParameterParser.parse("10000000000000000000000000000000000000000000");

        assertThat(hugeNumber.success().isPresent(), equalTo(false));
        assertFailureMessageOf(hugeNumber, hasCause("Input must be an integer number. It must also not be greater than 3000"));
    }

    @Test
    public void fails_with_numbers_below_maximum_allowed_value() {
        InputParsingResult zero = SingleParameterParser.parse("0");

        assertThat(zero.success().isPresent(), equalTo(false));
        assertFailureMessageOf(zero, hasCause("Arabic numerals smaller than 1 are not supported"));
    }

    @Test
    public void succeeds_with_a_value_within_the_supported_range() {
        InputParsingResult ten = SingleParameterParser.parse("10");
        InputParsingResult threeThousand = SingleParameterParser.parse("3000");

        assertThat(ten.error().isPresent(), equalTo(false));
        assertThat(ten.success().get(), equalTo(ArabicNumber.of(10)));
        assertThat(threeThousand.success().get(), equalTo(ArabicNumber.of(3000)));
    }

    private void assertFailureMessageOf(InputParsingResult result, Matcher<String> matcher) {
        assertThat(result.error().isPresent(), equalTo(true));
        assertThat(result.error().get().toString(), matcher);
    }

    private Matcher<String> hasCause(String partialMessage) {
        return equalTo("Invalid input: " + partialMessage);
    }
}