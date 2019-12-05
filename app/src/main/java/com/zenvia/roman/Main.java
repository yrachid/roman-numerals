package com.zenvia.roman;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("No input");
            System.exit(1);
        }

        String argument = args[0];

        if (argument == null || argument.isEmpty()) {
            System.err.println("No input");
            System.exit(1);
        }

        if (!argument.matches("\\d+")) {
            System.err.println(String.format("Bad input '%s'. Provide an integer.", argument));
            System.exit(1);
        }

        int input = Integer.parseInt(argument);

        System.out.println(ArabicToRomanNumeralConverter.convert(input));
    }
}
