package com.yrachid.roman.input;

import com.yrachid.roman.numerals.ArabicNumber;

import java.util.function.BiConsumer;

public final class ParameterParsingResult {

    private final String rawInput;
    private final ArabicNumber arabicNumber;
    private final InvalidParameterFailure invalidParameter;
    private String roman;

    private ParameterParsingResult(String rawInput, ArabicNumber arabicNumber, InvalidParameterFailure invalidParameter, String roman) {
        this.rawInput = rawInput;
        this.arabicNumber = arabicNumber;
        this.invalidParameter = invalidParameter;
        this.roman = roman;
    }

    private ParameterParsingResult(String rawInput, ArabicNumber arabicNumber) {
        this(rawInput, arabicNumber, null, null);
    }

    private ParameterParsingResult(String rawInput, String roman) {
        this(rawInput, null, null, roman);
    }

    private ParameterParsingResult(String rawInput, InvalidParameterFailure failure) {
        this(rawInput, null, failure, null);
    }

    static ParameterParsingResult success(String rawInput, String number) {
        return new ParameterParsingResult(rawInput, number);
    }

    static ParameterParsingResult success(String rawInput, ArabicNumber number) {
        return new ParameterParsingResult(rawInput, number);
    }

    static ParameterParsingResult failure(String rawInput, InvalidParameterFailure error) {
        return new ParameterParsingResult(rawInput, error);
    }

    public void error(BiConsumer<String, InvalidParameterFailure> callback) {
        if (invalidParameter != null) {
            callback.accept(rawInput, invalidParameter);
        }
    }

    public void success(BiConsumer<String, ArabicNumber> callback) {
        if (arabicNumber != null) {
            callback.accept(rawInput, arabicNumber);
        }
    }

    public void romanNumber(BiConsumer<String, String> callback) {
        if (roman != null) {
            callback.accept(rawInput, roman);
        }
    }
}
