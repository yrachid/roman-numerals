package com.zenvia.roman;

import com.zenvia.roman.converters.ArabicToRomanNumeralConverter;
import com.zenvia.roman.input.InputParsingResult;
import com.zenvia.roman.input.UserInputParser;

public class Main {

    public static void main(String[] args) {

        InputParsingResult result = UserInputParser.parse(args);

        result.error().ifPresent(error -> {
            System.out.println(error);
            System.exit(1);
        });

        result.success().ifPresent(value -> {
            System.out.println(ArabicToRomanNumeralConverter.convert(value));
        });
    }
}
