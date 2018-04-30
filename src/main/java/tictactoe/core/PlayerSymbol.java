package tictactoe.core;

public enum PlayerSymbol {
    X,
    O;

    private PlayerSymbol alternate;

    static {
        X.alternate = O;
        O.alternate = X;
    }

    public PlayerSymbol getAlternate() {
        return alternate;
    }
}
