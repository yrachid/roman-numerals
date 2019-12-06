package com.zenvia.roman;

import com.zenvia.roman.input.InputParsingResult;
import com.zenvia.roman.input.SingleParameterParser;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.zenvia.roman.converters.ArabicToRomanNumeralConverter.convert;

public class Main {

    private static final class Pair {
        private final String param;
        private final InputParsingResult result;

        Pair(String param, InputParsingResult result) {
            this.param = param;
            this.result = result;
        }
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Received Args: " + Arrays.toString(args));
        System.out.println();
        Stream.of(args)
                .map(param -> new Pair(param, SingleParameterParser.parse(param)))
                .forEach(parsed -> {

                    parsed.result.error().ifPresent(err -> {
                        System.out.println(String.format("%s\t:\t%s", parsed.param, err));
                    });

                    parsed.result.success().ifPresent(success -> {
                        System.out.println(String.format("%s\t:\t%s", parsed.param, convert(success)));
                    });
                });
    }
}
