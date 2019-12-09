package com.yrachid.roman;

import com.yrachid.roman.converters.ArabicToRomanNumberConverter;
import com.yrachid.roman.converters.RomanToArabicNumberConverter;
import com.yrachid.roman.input.SingleParameterParser;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Application {

    private final Consumer<String> output;

    public Application(Consumer<String> output) {
        this.output = output;
    }

    public void run(String[] args) {
        output.accept("");
        output.accept("Received Args: " + Arrays.toString(args));
        output.accept("");

        Stream.of(args)
                .map(SingleParameterParser::parse)
                .forEach(result -> {

                    result.error(this::printFormattedOutput);

                    result.romanNumber((input, number) ->
                            printFormattedOutput(input, RomanToArabicNumberConverter.convert(number)));

                    result.arabicNumber((input, number) ->
                            printFormattedOutput(input, ArabicToRomanNumberConverter.convert(number)));
                });
    }

    private <T> void printFormattedOutput(String input, T output) {
        this.output.accept(String.format("%s\t:\t%s", input, output.toString()));
    }
}
