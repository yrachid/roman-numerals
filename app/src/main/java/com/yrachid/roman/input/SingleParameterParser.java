package com.yrachid.roman.input;

import com.yrachid.roman.numerals.ArabicNumber;

public class SingleParameterParser {

    private static final String ARABIC_NUMBER_PATTERN = "\\d{1,4}";
    private static final String ROMAN_NUMBER_PATTERN = "[IVXLCDM]{1,11}";

    public static ParameterParsingResult parse(String param) {

        if (param == null || param.trim().isEmpty()) {
            return ParameterParsingResult.failure(param, InvalidParameterFailure.withMessage("Input is empty"));
        }

        if (param.matches(ROMAN_NUMBER_PATTERN)) {
            return ParameterParsingResult.success(param, param);
        }

        if (param.matches(ARABIC_NUMBER_PATTERN)) {
            try {
                int inputAsInt = Integer.parseInt(param);

                return ParameterParsingResult.success(param, ArabicNumber.of(inputAsInt));
            } catch (IllegalArgumentException exception) {
                return ParameterParsingResult.failure(param, InvalidParameterFailure.withMessage(exception.getMessage()));
            }
        }

        return ParameterParsingResult.failure(param, InvalidParameterFailure.withMessage(String.format(
                "Input must be an integer number. It must also not be greater than %d",
                ArabicNumber.MAX_VALUE.intValue()
        )));
    }
}
