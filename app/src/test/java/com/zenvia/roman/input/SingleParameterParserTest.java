package com.zenvia.roman.input;

import com.zenvia.roman.numerals.ArabicNumber;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SingleParameterParserTest {

    @Test
    public void fails_when_param_is_empty() {
        ParameterParsingResult empty = SingleParameterParser.parse("");

        assertThat(empty.success().isPresent(), equalTo(false));
        assertThat(empty.rawInput(), equalTo(""));
        assertFailureMessageOf(empty, hasCause("Input is empty"));
    }

    @Test
    public void fails_when_is_empty_space() {
        ParameterParsingResult emptySpace = SingleParameterParser.parse(" ");

        assertThat(emptySpace.success().isPresent(), equalTo(false));
        assertThat(emptySpace.rawInput(), equalTo(" "));
        assertFailureMessageOf(emptySpace, hasCause("Input is empty"));
    }

    @Test
    public void fails_when_param_is_null() {
        ParameterParsingResult nullParam = SingleParameterParser.parse(null);

        assertThat(nullParam.success().isPresent(), equalTo(false));
        assertThat(nullParam.rawInput(), equalTo(null));
        assertFailureMessageOf(nullParam, hasCause("Input is empty"));
    }

    @Test
    public void fails_with_non_numeric_input() {
        ParameterParsingResult nanParam = SingleParameterParser.parse("A");

        assertThat(nanParam.success().isPresent(), equalTo(false));
        assertThat(nanParam.rawInput(), equalTo("A"));
        assertFailureMessageOf(nanParam, hasCause("Input must be an integer number. It must also not be greater than 3000"));
    }

    @Test
    public void fails_with_numbers_beyond_maximum_allowed_value() {
        ParameterParsingResult hugeNumber = SingleParameterParser.parse("10000000000000000000000000000000000000000000");

        assertThat(hugeNumber.success().isPresent(), equalTo(false));
        assertThat(hugeNumber.rawInput(), equalTo("10000000000000000000000000000000000000000000"));
        assertFailureMessageOf(hugeNumber, hasCause("Input must be an integer number. It must also not be greater than 3000"));
    }

    @Test
    public void fails_with_numbers_below_maximum_allowed_value() {
        ParameterParsingResult zero = SingleParameterParser.parse("0");

        assertThat(zero.success().isPresent(), equalTo(false));
        assertThat(zero.rawInput(), equalTo("0"));
        assertFailureMessageOf(zero, hasCause("Arabic numerals smaller than 1 are not supported"));
    }

    @Test
    public void succeeds_with_a_value_within_the_supported_range() {
        ParameterParsingResult ten = SingleParameterParser.parse("10");
        ParameterParsingResult threeThousand = SingleParameterParser.parse("3000");

        assertThat(ten.rawInput(), equalTo("10"));
        assertThat(ten.error().isPresent(), equalTo(false));
        assertThat(ten.success().get(), equalTo(ArabicNumber.of(10)));

        assertThat(threeThousand.rawInput(), equalTo("3000"));
        assertThat(threeThousand.error().isPresent(), equalTo(false));
        assertThat(threeThousand.success().get(), equalTo(ArabicNumber.of(3000)));
    }

    private void assertFailureMessageOf(ParameterParsingResult result, Matcher<String> matcher) {
        assertThat(result.error().isPresent(), equalTo(true));
        assertThat(result.error().get().toString(), matcher);
    }

    private Matcher<String> hasCause(String partialMessage) {
        return equalTo("Invalid input: " + partialMessage);
    }
}