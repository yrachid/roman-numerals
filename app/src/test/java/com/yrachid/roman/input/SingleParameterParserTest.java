package com.yrachid.roman.input;

import com.yrachid.roman.numerals.ArabicNumber;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class SingleParameterParserTest {

    @Test
    public void fails_when_param_is_empty() {
        ParameterParsingResult empty = SingleParameterParser.parse("");

        empty.error((input, error) -> {
            assertThat(input, equalTo(""));
            assertThat(error.toString(), hasCause("Input is empty"));
        });

        empty.success(failBecauseOfUnexpectedSuccessCall());
    }

    @Test
    public void fails_when_is_empty_space() {
        ParameterParsingResult emptySpace = SingleParameterParser.parse(" ");

        emptySpace.error((input, error) -> {
            assertThat(input, equalTo(" "));
            assertThat(error.toString(), hasCause("Input is empty"));
        });

        emptySpace.success(failBecauseOfUnexpectedSuccessCall());
    }

    @Test
    public void fails_when_param_is_null() {
        ParameterParsingResult nullParam = SingleParameterParser.parse(null);

        nullParam.error((input, error) -> {
            assertThat(input, equalTo(null));
            assertThat(error.toString(), hasCause("Input is empty"));
        });

        nullParam.success(failBecauseOfUnexpectedSuccessCall());
    }

    @Test
    public void fails_with_non_numeric_input() {
        ParameterParsingResult nanParam = SingleParameterParser.parse("A");

        nanParam.error((input, error) -> {
            assertThat(input, equalTo("A"));
            assertThat(error.toString(), hasCause("Input must be an integer number. It must also not be greater than 3000"));
        });

        nanParam.success(failBecauseOfUnexpectedSuccessCall());
    }

    @Test
    public void fails_with_numbers_beyond_maximum_allowed_value() {
        ParameterParsingResult hugeNumber = SingleParameterParser.parse("10000000000000000000000000000000000000000000");

        hugeNumber.error((input, error) -> {
            assertThat(input, equalTo("10000000000000000000000000000000000000000000"));
            assertThat(error.toString(), hasCause("Input must be an integer number. It must also not be greater than 3000"));
        });

        hugeNumber.success(failBecauseOfUnexpectedSuccessCall());
    }

    @Test
    public void fails_with_numbers_below_maximum_allowed_value() {
        ParameterParsingResult zero = SingleParameterParser.parse("0");

        zero.error((input, error) -> {
            assertThat(input, equalTo("0"));
            assertThat(error.toString(), hasCause("Arabic numerals smaller than 1 are not supported"));
        });

        zero.success(failBecauseOfUnexpectedSuccessCall());
    }

    @Test
    public void succeeds_with_a_value_within_the_supported_range() {
        ParameterParsingResult ten = SingleParameterParser.parse("10");
        ParameterParsingResult threeThousand = SingleParameterParser.parse("3000");

        ten.success((input, value) -> {
            assertThat(input, equalTo("10"));
            assertThat(value, equalTo(ArabicNumber.of(10)));
        });

        threeThousand.success((input, value) -> {
            assertThat(input, equalTo("3000"));
            assertThat(value, equalTo(ArabicNumber.of(3000)));
        });

        ten.error(failBecauseOfUnexpectedFailureCall());
        threeThousand.error(failBecauseOfUnexpectedFailureCall());
    }

    private BiConsumer<String, InvalidParameterFailure> failBecauseOfUnexpectedFailureCall() {
        return (input, error) -> fail("Error should not have been called");
    }

    private BiConsumer<String, ArabicNumber> failBecauseOfUnexpectedSuccessCall() {
        return (a, b) -> fail("Success should not have been called");
    }

    private Matcher<String> hasCause(String partialMessage) {
        return equalTo("Invalid input: " + partialMessage);
    }
}