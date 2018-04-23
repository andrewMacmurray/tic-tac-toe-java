package tictactoe.game;

import tictactoe.core.Board;
import tictactoe.core.Player;
import tictactoe.core.Status;

public class Model {

    private final Board board;
    private final Player currentPlayer;
    private final Status status;

    public Model(int boardSize, Player currentPlayer) {
        this.board = new Board(boardSize);
        this.currentPlayer = currentPlayer;
        this.status = Status.NonTerminal;
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

    public Player getWinner() {
        return this.board.winner();
    }

    public Status getStatus() {
        return this.status;
    }

    private Model(int move, Player currentPlayer, Board board) {
        Board nextBoard = board.makeMove(move, currentPlayer);
        this.board = nextBoard;
        this.currentPlayer = currentPlayer.getAlternate();
        this.status = evaluateStatus(nextBoard);
    }

    private Status evaluateStatus(Board board) {
        if (!board.winner().isEmpty()) {
            return Status.Win;
        } else if (board.isFull()) {
            return Status.Draw;
        } else {
            return Status.NonTerminal;
        }
    }
}
