package ttt.game;

import ttt.core.Board;
import ttt.core.Player;

public class Model {

    private Board board;
    private Player currentPlayer;
    private MoveStatus moveStatus;

    public Model(int boardScale, Player firstPlayer) {
        this.currentPlayer = firstPlayer;
        this.moveStatus = MoveStatus.Valid;
        this.board = new Board(boardScale);
    }

    public void update(int move) {
        if (tileOccupied(move)) {
            this.moveStatus = MoveStatus.TileTaken;
        } else {
            this.board.takeTile(move, this.currentPlayer);
            switchCurrentPlayer();
        }
    }

    public Player winner() {
        return this.board.winner();
    }

    public boolean tileOccupied(int move) {
        return this.board.tileTaken(move);
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int getBoardScale() {
        return this.board.getBoardScale();
    }

    public Player[] getTiles() {
        return this.board.getTiles();
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    private void switchCurrentPlayer() {
        this.currentPlayer = alternatePlayer(this.currentPlayer);
    }

    private Player alternatePlayer(Player player) {
        switch (player) {
            case X:  return Player.O;
            case O:  return Player.X;
            default: return Player.Empty;
        }
    }
}
