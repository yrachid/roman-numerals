package com.yrachid.roman.input;

public class InvalidParameterFailure {

    private final String message;

    private InvalidParameterFailure(String message) {
        this.message = message;
    }

    static InvalidParameterFailure withMessage(String message) {
        return new InvalidParameterFailure(message);
    }

    @Override
    public String toString() {
        return "Invalid input: " + message;
    }

}
