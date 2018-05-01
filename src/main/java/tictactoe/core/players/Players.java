package tictactoe.core.players;

import tictactoe.core.Board;

public class Players {

    private Player currentPlayer;
    private final Player player1;
    private final Player player2;

    public Players(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public Integer chooseNextMove(Board board) {
        return currentPlayer.chooseNextMove(board);
    }

    public PlayerSymbol currentPlayerSymbol() {
        return currentPlayer.getSymbol();
    }

    public void switchPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = this.player2;
        } else {
            currentPlayer = this.player1;
        }
    }
}
