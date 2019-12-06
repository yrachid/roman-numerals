package com.zenvia.roman;

import com.zenvia.roman.input.SingleParameterParser;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.zenvia.roman.converters.ArabicToRomanNumberConverter.convert;

public class Main {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Received Args: " + Arrays.toString(args));
        System.out.println();

        Stream.of(args)
                .map(SingleParameterParser::parse)
                .forEach(result -> {

                    result.error((input, err) -> {
                        System.out.println(String.format("%s\t:\t%s", input, err));
                    });

                    result.success((input, success) -> {
                        System.out.println(String.format("%s\t:\t%s", input, convert(success)));
                    });
                });
    }
}
