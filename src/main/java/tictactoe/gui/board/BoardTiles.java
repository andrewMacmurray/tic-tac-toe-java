package tictactoe.gui.board;

import javafx.scene.layout.TilePane;
import tictactoe.core.Board;
import tictactoe.core.Tile;

import java.util.function.Consumer;

public class BoardTiles extends TilePane {

    private Consumer<Integer> sendMove;

    public BoardTiles(Consumer<Integer> sendMove) {
        this.sendMove = sendMove;
        addCss();
    }

    private void addCss() {
        this.getStyleClass().add("board-container");
    }

    public void render(Board board) {
        clear();
        board.tilesStream().forEach(tile -> setupTile(tile, board.getBoardSize()));
    }

    private void setupTile(Tile tile, int boardSize) {
        PlayerTile playerTile = new PlayerTile(tile, boardSize).onClick(sendMove);
        this.getChildren().add(playerTile);
    }

    public void disableClicks() {
        this.getChildren().forEach(tile -> tile.setOnMouseClicked(null));
    }

    private void clear() {
        this.getChildren().clear();
    }

}
