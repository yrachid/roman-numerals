package com.zenvia.roman.input;

import com.zenvia.roman.numerals.ArabicNumber;

public class UserInputParser {

    private static final String NUMERIC_PATTERN = "\\d{1,4}";

    public static InputParsingResult parse(String[] arguments) {

        if (arguments == null || arguments.length == 0 || arguments[0].trim().isEmpty()) {
            return InputParsingResult.failure(InvalidInputFailure.withMessage("Input is empty"));
        }

        if (!arguments[0].matches(NUMERIC_PATTERN)) {
            return InputParsingResult.failure(InvalidInputFailure.withMessage(String.format(
                    "Input must be an integer number. It must also not be greater than %d",
                    ArabicNumber.MAX_VALUE.intValue()
            )));
        }

        try {
            int inputAsInt = Integer.parseInt(arguments[0]);

            return InputParsingResult.success(ArabicNumber.of(inputAsInt));
        } catch (IllegalArgumentException exception) {
            return InputParsingResult.failure(InvalidInputFailure.withMessage(exception.getMessage()));
        }

    }
}
