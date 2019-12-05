package com.zenvia.roman;

import com.zenvia.roman.converters.ArabicToRomanNumeralConverter;
import com.zenvia.roman.input.InputParsingResult;
import com.zenvia.roman.input.UserInputParser;

public class Main {

    public static void main(String[] args) {

        InputParsingResult result = UserInputParser.parse(args);

        result.left().ifPresent(error -> {
            System.out.println(error);
            System.exit(1);
        });

        result.right().ifPresent(value -> {
            String conversionResult = ArabicToRomanNumeralConverter.convert(value.intValue());
            System.out.println(conversionResult);
        });
    }
}
