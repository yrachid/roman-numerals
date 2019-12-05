package com.zenvia.roman.input;

public class InvalidInputFailure {

    private final String message;

    private InvalidInputFailure(String message) {
        this.message = message;
    }

    static InvalidInputFailure withMessage(String message) {
        return new InvalidInputFailure(message);
    }

    @Override
    public String toString() {
        return "Invalid input: " + message;
    }

}
