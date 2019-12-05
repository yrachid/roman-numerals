package com.zenvia.roman.input;

import com.zenvia.roman.Either;
import com.zenvia.roman.numerals.ArabicNumber;

import java.util.Optional;

public final class InputParsingResult implements Either<InvalidInputFailure, ArabicNumber> {

    private final ArabicNumber right;
    private final InvalidInputFailure left;

    private InputParsingResult(ArabicNumber right, InvalidInputFailure left) {
        this.right = right;
        this.left = left;
    }

    public static InputParsingResult success(ArabicNumber number) {
        return new InputParsingResult(number, null);
    }

    public static InputParsingResult failure(InvalidInputFailure error) {
        return new InputParsingResult(null, error);
    }

    @Override
    public Optional<ArabicNumber> right() {
        return Optional.ofNullable(right);
    }

    @Override
    public Optional<InvalidInputFailure> left() {
        return Optional.ofNullable(left);
    }
}
