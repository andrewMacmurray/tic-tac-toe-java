package tictactoe.core;

public enum GuessStatus {
    Valid,
    OutOfBounds,
    AlreadyTaken;

    private boolean isValid;

    static {
        Valid.isValid = true;
        OutOfBounds.isValid = false;
        AlreadyTaken.isValid = false;
    }

    public boolean isValid() {
        return isValid;
    }
}
