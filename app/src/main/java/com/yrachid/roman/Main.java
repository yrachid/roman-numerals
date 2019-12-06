package com.yrachid.roman;

import com.yrachid.roman.converters.ArabicToRomanNumberConverter;
import com.yrachid.roman.input.SingleParameterParser;

import java.util.Arrays;
import java.util.stream.Stream;

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
                        System.out.println(String.format("%s\t:\t%s", input, ArabicToRomanNumberConverter.convert(success)));
                    });
                });
    }
}
