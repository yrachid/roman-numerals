package com.yrachid.roman.input;

import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;

import java.util.function.BiConsumer;

public final class ParameterParsingResult {

    private final String rawInput;
    private final ArabicNumber arabicNumber;
    private final InvalidParameterFailure invalidParameter;
    private final RomanNumber romanNumber;

    private ParameterParsingResult(String rawInput, ArabicNumber arabicNumber, InvalidParameterFailure invalidParameter, RomanNumber romanNumber) {
        this.rawInput = rawInput;
        this.arabicNumber = arabicNumber;
        this.invalidParameter = invalidParameter;
        this.romanNumber = romanNumber;
    }

    private ParameterParsingResult(String rawInput, ArabicNumber arabicNumber) {
        this(rawInput, arabicNumber, null, null);
    }

    private ParameterParsingResult(String rawInput, RomanNumber roman) {
        this(rawInput, null, null, roman);
    }

    private ParameterParsingResult(String rawInput, InvalidParameterFailure failure) {
        this(rawInput, null, failure, null);
    }

    static ParameterParsingResult romanNumber(String rawInput, RomanNumber romanNumber) {
        return new ParameterParsingResult(rawInput, romanNumber);
    }

    static ParameterParsingResult arabicNumber(String rawInput, ArabicNumber number) {
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

    public void arabicNumber(BiConsumer<String, ArabicNumber> callback) {
        if (arabicNumber != null) {
            callback.accept(rawInput, arabicNumber);
        }
    }

    public void romanNumber(BiConsumer<String, RomanNumber> callback) {
        if (romanNumber != null) {
            callback.accept(rawInput, romanNumber);
        }
    }
}
