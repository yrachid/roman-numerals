package com.yrachid.roman.input;

import com.yrachid.roman.numerals.ArabicNumber;

import static com.yrachid.roman.input.InvalidParameterFailure.withMessage;

public class SingleParameterParser {

    private static final String ARABIC_NUMBER_PATTERN = "\\d{1,4}";
    static final String ROMAN_NUMBER_PATTERN = "[IVXLCDM]{1,14}";

    public static ParameterParsingResult parse(String param) {

        if (param == null || param.trim().isEmpty()) {
            return ParameterParsingResult.failure(param, withMessage("Input is empty"));
        }

//       TODO: Seria possivel remover esta duplicacao com polimorfismo?
        if (param.matches(ROMAN_NUMBER_PATTERN)) {
            try {
                return ParameterParsingResult.romanNumber(param, RomanNumberParser.parse(param));
            } catch (IllegalArgumentException exception) {
                return ParameterParsingResult.failure(param, withMessage(exception.getMessage()));
            }
        }

        if (param.matches(ARABIC_NUMBER_PATTERN)) {
            try {
                int inputAsInt = Integer.parseInt(param);

                return ParameterParsingResult.arabicNumber(param, ArabicNumber.of(inputAsInt));
            } catch (IllegalArgumentException exception) {
                return ParameterParsingResult.failure(param, withMessage(exception.getMessage()));
            }
        }

        return ParameterParsingResult.failure(param, withMessage(
                String.format("Input must be an integer number within [%s, %s] or a roman number",
                        ArabicNumber.MIN_VALUE,
                        ArabicNumber.MAX_VALUE
                )
        ));
    }
}
