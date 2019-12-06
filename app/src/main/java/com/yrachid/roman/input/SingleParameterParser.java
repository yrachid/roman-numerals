package com.yrachid.roman.input;

import com.yrachid.roman.numerals.ArabicNumber;

public class SingleParameterParser {

    private static final String NUMERIC_PATTERN = "\\d{1,4}";

    public static ParameterParsingResult parse(String param) {

        if (param == null || param.trim().isEmpty()) {
            return ParameterParsingResult.failure(param, InvalidParameterFailure.withMessage("Input is empty"));
        }

        if (!param.matches(NUMERIC_PATTERN)) {
            return ParameterParsingResult.failure(param, InvalidParameterFailure.withMessage(String.format(
                    "Input must be an integer number. It must also not be greater than %d",
                    ArabicNumber.MAX_VALUE.intValue()
            )));
        }

        try {
            int inputAsInt = Integer.parseInt(param);

            return ParameterParsingResult.success(param, ArabicNumber.of(inputAsInt));
        } catch (IllegalArgumentException exception) {
            return ParameterParsingResult.failure(param, InvalidParameterFailure.withMessage(exception.getMessage()));
        }

    }
}
