package com.zenvia.roman.input;

import com.zenvia.roman.numerals.ArabicNumber;

import java.util.Optional;

public final class ParameterParsingResult {

    private final String rawInput;
    private final ArabicNumber right;
    private final InvalidParameterFailure left;

    private ParameterParsingResult(String rawInput, ArabicNumber right, InvalidParameterFailure left) {
        this.rawInput = rawInput;
        this.right = right;
        this.left = left;
    }

    static ParameterParsingResult success(String rawInput, ArabicNumber number) {
        return new ParameterParsingResult(rawInput, number, null);
    }

    static ParameterParsingResult failure(String rawInput, InvalidParameterFailure error) {
        return new ParameterParsingResult(rawInput, null, error);
    }

    public String rawInput() {
        return rawInput;
    }

    public Optional<ArabicNumber> success() {
        return Optional.ofNullable(right);
    }

    public Optional<InvalidParameterFailure> error() {
        return Optional.ofNullable(left);
    }
}
