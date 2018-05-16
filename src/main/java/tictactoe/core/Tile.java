package tictactoe.core;

import tictactoe.core.players.PlayerSymbol;

import java.util.Optional;
import java.util.function.Function;

public class Tile {

    private Optional<PlayerSymbol> playerSymbol;
    private int index;

    Tile(int index) {
        this.index = index;
        this.playerSymbol = Optional.empty();
    }

    Tile(int index, PlayerSymbol playerSymbol) {
        this.index = index;
        this.playerSymbol = Optional.of(playerSymbol);
    }

    boolean isTakenBy(PlayerSymbol p) {
        return playerSymbol.map(s -> s == p).orElse(false);
    }

    boolean isEmpty() {
        return !playerSymbol.isPresent();
    }

    public int getIndex() {
        return index;
    }

    public String toString() {
        return playerSymbol
                .map(PlayerSymbol::toString)
                .orElse(Integer.toString(index));
    }

}
