package com.zenvia.roman;

import com.zenvia.roman.input.SingleParameterParser;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.zenvia.roman.converters.ArabicToRomanNumeralConverter.convert;

public class Main {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Received Args: " + Arrays.toString(args));
        System.out.println();
        Stream.of(args)
                .map(SingleParameterParser::parse)
                .forEach(result -> {

                    result.error().ifPresent(err -> {
                        System.out.println(String.format("%s\t:\t%s", result.rawInput(), err));
                    });

                    result.success().ifPresent(success -> {
                        System.out.println(String.format("%s\t:\t%s", result.rawInput(), convert(success)));
                    });
                });
    }
}
