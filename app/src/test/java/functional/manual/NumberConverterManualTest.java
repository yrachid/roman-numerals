package functional.manual;

import com.yrachid.roman.Application;

import java.util.Scanner;

public class NumberConverterManualTest {

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        System.out.println("Provide your input: ");
        String input = inputReader.nextLine();

        new Application(System.out::println).run(input.split("\\s"));
    }
}
