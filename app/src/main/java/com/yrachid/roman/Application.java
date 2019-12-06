package com.yrachid.roman;

import com.yrachid.roman.converters.ArabicToRomanNumberConverter;
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

                    result.romanNumber(this::printFormattedOutput);

                    result.success((input, success) ->
                            printFormattedOutput(input, ArabicToRomanNumberConverter.convert(success)));
                });
    }

    private void printFormattedOutput(String input, Object err) {
        output.accept(String.format("%s\t:\t%s", input, err.toString()));
    }
}
