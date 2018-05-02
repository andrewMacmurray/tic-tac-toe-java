package tictactoe.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;

public class IO {

    private final PrintStream out;
    private final Scanner scanner;

    IO(InputStream in, PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    void println(String string) {
        out.println(string);
    }

    void clearScreen() {
        final String clearSequence = "\033[H\033[2J";
        out.print(clearSequence);
        out.flush();
    }

    int readIntInRange(Integer lower, Integer upper, String errorMessage) {
        int i = readIntWithRetry(errorMessage);
        if (i >= lower && i <= upper) {
            return i;
        } else {
            println(errorMessage);
            return readIntInRange(lower, upper, errorMessage);
        }
    }

    int readIntWithRetry(String errorMessage) {
        try {
            return parseNextInt();
        } catch (NumberFormatException e) {
            println(errorMessage);
            return readIntWithRetry(errorMessage);
        }
    }

    boolean readYesNoWithRetry(String errorMessage) {
        String input = scanner.next();
        if (!isYesNo(input)) {
            println(errorMessage);
            return readYesNoWithRetry(errorMessage);
        } else {
            return startsWith("Y").test(input);
        }
    }

    private Predicate<String> startsWith(String letter) {
        return input -> input.toUpperCase().startsWith(letter);
    }

    private boolean isYesNo(String input) {
        return startsWith("Y").test(input) || startsWith("N").test(input);
    }

    private int parseNextInt() throws NumberFormatException {
        String input = scanner.next();
        return Integer.parseInt(input);
    }
}
