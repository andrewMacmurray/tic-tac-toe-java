package tictactoe.core;

import tictactoe.core.players.PlayerSymbol;

import java.util.Optional;

public class Tile {
    private Optional<PlayerSymbol> playerSymbol;

    Tile() {
        this.playerSymbol = Optional.empty();
    }

    Tile(PlayerSymbol playerSymbol) {
        this.playerSymbol = Optional.of(playerSymbol);
    }

    public Tile setPlayerSymbol(PlayerSymbol playerSymbol) {
        this.playerSymbol = Optional.of(playerSymbol);
        return this;
    }

    boolean isTakenBy(PlayerSymbol p) {
        return playerSymbol.map(s -> s == p).orElse(false);
    }

    boolean isEmpty() {
        return !playerSymbol.isPresent();
    }

    public String toString(int index) {
        return playerSymbol
                .map(PlayerSymbol::toString)
                .orElse(Integer.toString(index));
    }
}
