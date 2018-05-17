package tictactoe.gui.board;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.Tile;

import java.util.function.Consumer;

public class BoardUI {

    private VBox rootNode = new VBox();
    private Text statusText = new Text();
    private TilePane boardTiles = new TilePane();
    private Mediator mediator;

    public BoardUI(Mediator mediator) {
        this.mediator = mediator;
        assembleSceneGraph();
        addCssClasses();
    }

    public VBox getRootNode() {
        return rootNode;
    }

    public void setStatusText(String text) {
        statusText.setText(text);
    }

    public void renderBoard(Board board) {
        clearTiles();
        board.tilesStream().forEach(this::setupTile);
    }

    private void setupTile(Tile tile) {
        PlayerTile playerTile = new PlayerTile(tile);
        playerTile.makeMoveOnClick(mediator::receiveMove);
        boardTiles.getChildren().add(playerTile);
    }

    public void disableClicks() {
        boardTiles.getChildren().forEach(node -> node.setOnMouseClicked(null));
    }

    private void clearTiles() {
        boardTiles.getChildren().clear();
    }

    private void assembleSceneGraph() {
        rootNode.getChildren().addAll(
                boardTiles,
                statusText
        );
    }

    private void addCssClasses() {
        addCssClass(statusText, "status-text");
        addCssClass(boardTiles, "board-container");
        addCssClass(rootNode, "main-container");
    }

    private void addCssClass(Node node, String cssClass) {
        node.getStyleClass().add(cssClass);
    }

}
