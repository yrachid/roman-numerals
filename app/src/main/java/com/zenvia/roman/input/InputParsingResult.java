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

    static InputParsingResult success(ArabicNumber number) {
        return new InputParsingResult(number, null);
    }

    static InputParsingResult failure(InvalidInputFailure error) {
        return new InputParsingResult(null, error);
    }

    @Override
    public Optional<ArabicNumber> success() {
        return Optional.ofNullable(right);
    }

    @Override
    public Optional<InvalidInputFailure> error() {
        return Optional.ofNullable(left);
    }
}
