package tictactoe.core;

public enum Player {

    X, O, Empty;

    private Player alternate;

    static {
        X.alternate = O;
        O.alternate = X;
        Empty.alternate = Empty;
    }

    public Player getAlternate() {
        return alternate;
    }

    public boolean isEmpty() {
        return this == Empty;
    }
}
