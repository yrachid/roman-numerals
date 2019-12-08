package functional;

import com.yrachid.roman.converters.RomanToArabicNumberConverter;
import com.yrachid.roman.input.RomanNumberParser;
import com.yrachid.roman.numerals.ArabicNumber;
import com.yrachid.roman.numerals.RomanNumber;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.fail;

public class AllRomanInputsTest {

    @Test
    public void spots_roman_numbers_that_were_incorrectly_converted_to_arabic() throws IOException, URISyntaxException {

        Stream<String> lines = getResourceLines("all-supported-roman-values");

        Optional<String> firstLine = lines.findFirst();

        if (!firstLine.isPresent()) {
            fail("Resource file not found or empty");
        }

        String firstLineContent = firstLine.get();

        String[] romanNumbers = firstLineContent.split("\\s");

        int differencesFound = 0;
        RomanNumber currentRoman = null;
        ArabicNumber currentNumber = null;

        for (int i = 0; i < romanNumbers.length; i++) {
            try {
                currentNumber = ArabicNumber.of(i + 1);

                currentRoman = RomanNumberParser.parse(romanNumbers[i]);

                ArabicNumber result = RomanToArabicNumberConverter.convert(currentRoman);

                if (!currentNumber.equals(result)) {
                    differencesFound++;
                    System.out.println("expected:\t" + currentNumber + "\tgot:\t" + result + "\tfrom:\t" + currentRoman);
                }
            } catch (IllegalArgumentException ex) {
                differencesFound++;
                System.out.println("Failed to convert " + currentNumber + " from " + currentRoman + ". Error: " + ex.getMessage());
            }
        }

        if (differencesFound > 0) {
            fail("Found a total of " + differencesFound + " incorrect conversions");
        } else {
            System.out.println("No differences found.");
        }
    }

    private Stream<String> getResourceLines(String name) throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource(name);

        return Files.lines(Paths.get(resource.toURI()));
    }
}
