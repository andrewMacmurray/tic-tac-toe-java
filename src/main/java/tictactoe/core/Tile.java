package tictactoe.core;

import tictactoe.core.players.Player;
import tictactoe.core.players.PlayerSymbol;

import java.util.Optional;
import java.util.function.Function;

public class Tile {
    private Optional<PlayerSymbol> playerSymbol;

    Tile() {
        this.playerSymbol = Optional.empty();
    }

    Tile(PlayerSymbol playerSymbol) {
        this.playerSymbol = Optional.of(playerSymbol);
    }

    boolean isTakenBy(PlayerSymbol p) {
        return playerSymbol.map(s -> s == p).orElse(false);
    }

    boolean isEmpty() {
        return !playerSymbol.isPresent();
    }

    public String toString(int index) {
        return toStringWith(PlayerSymbol::toString, index);
    }

    public String toStringWith(Function<PlayerSymbol, String> toStringF, int index) {
        return playerSymbol
                .map(toStringF)
                .orElse(Integer.toString(index));
    }
}
