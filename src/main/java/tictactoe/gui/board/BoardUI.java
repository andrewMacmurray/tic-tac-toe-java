package tictactoe.gui.board;

import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.Tile;

import java.util.function.Consumer;

public class BoardUI extends VBox {

    private StatusText statusText = new StatusText();
    private TilePane boardTiles = new TilePane();
    private Consumer<Integer> sendMove;

    public BoardUI(Consumer<Integer> sendMove) {
        this.sendMove = sendMove;
        assembleSceneGraph();
        addCssClasses();
    }

    public void setStatusText(String text) {
        statusText.setText(text);
    }

    public void renderBoard(Board board) {
        clearTiles();
        board.tilesStream().forEach(tile -> setupTile(tile, board.getBoardSize()));
    }

    private void setupTile(Tile tile, int boardSize) {
        PlayerTile playerTile = new PlayerTile(tile, boardSize);
        playerTile.onClick(sendMove);
        boardTiles.getChildren().add(playerTile);
    }

    public void disableClicks() {
        boardTiles.getChildren().forEach(node -> node.setOnMouseClicked(null));
    }

    private void clearTiles() {
        boardTiles.getChildren().clear();
    }

    private void assembleSceneGraph() {
        this.getChildren().addAll(
                boardTiles,
                statusText
        );
    }

    private void addCssClasses() {
        addCssClass(boardTiles, "board-container");
        addCssClass(this, "board-ui-container");
    }

    private void addCssClass(Node node, String cssClass) {
        node.getStyleClass().add(cssClass);
    }

}
