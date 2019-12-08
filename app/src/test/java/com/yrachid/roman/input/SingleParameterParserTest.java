package com.yrachid.roman.input;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.function.BiConsumer;

import static com.yrachid.roman.numerals.RomanNumeral.I;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class SingleParameterParserTest {

    @Test
    public void fails_when_param_is_empty() {
        expectErrorFrom("", (input, error) -> {
            assertThat(input, equalTo(""));
            assertThat(error.toString(), hasCause("Input is empty"));
        });
    }

    @Test
    public void fails_when_is_empty_space() {
        expectErrorFrom(" ", (input, error) -> {
            assertThat(input, equalTo(" "));
            assertThat(error.toString(), hasCause("Input is empty"));
        });
    }

    @Test
    public void fails_when_param_is_null() {
        expectErrorFrom(null, (input, error) -> {
            assertThat(input, equalTo(null));
            assertThat(error.toString(), hasCause("Input is empty"));
        });
    }

    @Test
    public void fails_with_non_numeric_input() {
        expectErrorFrom("A", (input, error) -> {
            assertThat(input, equalTo("A"));
            assertThat(error.toString(), hasCause("Input must be an integer number. It must also not be greater than 3000"));
        });
    }

    @Test
    public void fails_with_numbers_beyond_maximum_allowed_value() {
        expectErrorFrom("10000000000000000000000000000000000000000000", (input, error) -> {
            assertThat(input, equalTo("10000000000000000000000000000000000000000000"));
            assertThat(error.toString(), hasCause("Input must be an integer number. It must also not be greater than 3000"));
        });
    }

    @Test
    public void fails_with_numbers_below_maximum_allowed_value() {
        expectErrorFrom("0", (input, error) -> {
            assertThat(input, equalTo("0"));
            assertThat(error.toString(), hasCause("Arabic numerals smaller than 1 are not supported"));
        });
    }

    @Test
    public void succeeds_with_roman_numeral_input() {
        expectRomanValueFrom("I", (input, value) -> {
            assertThat(input, equalTo("I"));
            assertThat(value, equalTo(RomanNumber.of(I)));
        });

        expectRomanValueFrom("II", (input, value) -> {
            assertThat(input, equalTo("II"));
            assertThat(value, equalTo(RomanNumber.of(I, I)));
        });
    }

    @Test
    public void succeeds_with_a_value_within_the_supported_range() {
        expectArabicNumberFrom("10", (input, value) -> {
            assertThat(input, equalTo("10"));
            assertThat(value, equalTo(ArabicNumber.of(10)));
        });

        expectArabicNumberFrom("3000", (input, value) -> {
            assertThat(input, equalTo("3000"));
            assertThat(value, equalTo(ArabicNumber.of(3000)));
        });
    }

    private void expectErrorFrom(String input, BiConsumer<String, InvalidParameterFailure> resultConsumer) {
        ParameterParsingResult result = SingleParameterParser.parse(input);

        result.error(resultConsumer);

        result.romanNumber(failBecauseOfUnexpectedRomanNumberCall());
        result.arabicNumber(failBecauseOfUnexpectedSuccessCall());
    }

    private void expectArabicNumberFrom(String input, BiConsumer<String, ArabicNumber> resultConsumer) {
        ParameterParsingResult result = SingleParameterParser.parse(input);

        result.arabicNumber(resultConsumer);

        result.romanNumber(failBecauseOfUnexpectedRomanNumberCall());
        result.error(failBecauseOfUnexpectedFailureCall());
    }

    private void expectRomanValueFrom(String input, BiConsumer<String, RomanNumber> resultConsumer) {
        ParameterParsingResult result = SingleParameterParser.parse(input);

        result.romanNumber(resultConsumer);

        result.arabicNumber(failBecauseOfUnexpectedSuccessCall());
        result.error(failBecauseOfUnexpectedFailureCall());
    }

    private BiConsumer<String, RomanNumber> failBecauseOfUnexpectedRomanNumberCall() {
        return (input, error) -> fail("Error should not have been called");
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