package tictactoe.game;

import tictactoe.core.Board;
import tictactoe.core.Player;

public class Model {

    private Board board;
    private Player currentPlayer;

    public Model(int boardSize, Player currentPlayer) {
        this.board = new Board(boardSize);
        this.currentPlayer = currentPlayer;
    }

    public Model makeMove(int move) {
        return new Model(move, this.currentPlayer, this.board);
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int getBoardSize() {
        return this.board.getBoardSize();
    }

    public Player[] getTiles() {
       return this.board.getTiles();
    }

    private Model(int move, Player currentPlayer, Board board) {
        this.board = board.makeMove(move, currentPlayer);
        this.currentPlayer = currentPlayer.getAlternate();
    }
}
