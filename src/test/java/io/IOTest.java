package io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpioneer.jupiter.TempDirectory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TempDirectory.class)
public class IOTest {

    @Test
    void should_read_write_file_from_file_stream(@TempDirectory.TempDir Path dir) throws Exception {
        final String message = "Hello world!" + System.lineSeparator();
        Path filePath = dir.resolve("sample.txt");

        writeAllText(message, filePath, StandardCharsets.UTF_8);
        assertEquals(message, readAllText(filePath, StandardCharsets.UTF_8));
    }

    private static void writeAllText(String message, Path filePath, Charset charset) throws IOException {
        // TODO: please implement the method to writer text to file using `PrintWriter`.
        // Use try-with-resources Statement

        try (PrintWriter printWriter = new PrintWriter(filePath.toString(), charset.name())) {
            printWriter.print(message);
        }
    }

    private static String readAllText(Path path, Charset charset) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> list = Files.lines(path, charset)) {
            list.forEach(string -> builder.append(string).append(System.lineSeparator()));
        }
        return builder.toString();
    }

    @Test
    void should_be_able_to_write_and_read_binary_data_to_file(@TempDirectory.TempDir Path dir) throws Exception {
        Path filePath = dir.resolve("sample.bin");

        final int firstValue = 2018;
        final double pi = 3.14;

        // TODO: please write `firstValue` and `pi` to `filePath`
        //Use try-with-resources Statement
        try (PrintWriter printWriter = new PrintWriter(filePath.toString())) {
            printWriter.println(firstValue);
            printWriter.println(pi);
        }

        int actualFirstValue = 0;
        double actualPi = 0;

        // TODO: please read `actualFirstValue` and `actualPi` from `filePath`
        //Use try-with-resources Statement
        try (InputStream inputStream = new FileInputStream(String.valueOf(filePath)); Scanner scanner = new Scanner(inputStream);) {
            actualFirstValue = Integer.parseInt(scanner.nextLine());
            actualPi = Double.parseDouble(scanner.nextLine());
        }

        assertEquals(firstValue, actualFirstValue);
        assertEquals(pi, actualPi);
    }

}
