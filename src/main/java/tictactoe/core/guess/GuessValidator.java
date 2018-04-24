package tictactoe.core.guess;

public class GuessValidator {

    private int value;
    private GuessStatus guessStatus = GuessStatus.Unrecognized;
    private int lowerBound;
    private int upperBound;

    public GuessValidator(int lowerBound, int upperBound) {
       this.lowerBound = lowerBound;
       this.upperBound = upperBound;
    }

    public GuessValidator validate(String input) {
        parseGuess(input);
        return this;
    }

    public int getValue () {
        return this.value;
    }

    public GuessStatus getStatus() {
       return this.guessStatus;
    }

    public boolean isValid() {
        return this.guessStatus.isValid();
    }

    private void parseGuess(String input) {
        try {
            int parsed = Integer.parseInt(input);
            handleParsedGuess(parsed);
        } catch (NumberFormatException err) {
            this.guessStatus = GuessStatus.Unrecognized;
        }
    }

    private void handleParsedGuess(int parsedGuess) {
        if (parsedGuess >= lowerBound && parsedGuess <= upperBound) {
            this.value = parsedGuess;
            this.guessStatus = GuessStatus.Valid;
        } else {
            this.guessStatus = GuessStatus.OutOfBounds;
        }
    }
}
