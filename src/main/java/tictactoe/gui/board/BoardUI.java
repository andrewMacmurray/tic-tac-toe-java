package tictactoe.gui.board;

import javafx.scene.layout.VBox;
import tictactoe.core.Board;

import java.util.function.Consumer;

public class BoardUI extends VBox {

    private StatusText statusText = new StatusText();
    private BoardTiles boardTiles;
    private PlayAgainButton playAgainButtonButton;

    public BoardUI(Consumer<Integer> sendMove, Runnable triggerNewGame) {
        createBoardTiles(sendMove);
        createPlayAgainButton(triggerNewGame);
        addCss();
        assembleInitialSceneGraph();
    }

    public void setStatusText(String text) {
        statusText.setText(text);
    }

    public void renderBoard(Board board) {
        boardTiles.render(board);
    }

    public void playAgain() {
        this.getChildren().add(playAgainButtonButton);
    }

    private void createPlayAgainButton(Runnable triggerNewGame) {
        playAgainButtonButton = new PlayAgainButton(() -> {
            triggerNewGame.run();
            assembleInitialSceneGraph();
        });
    }

    private void createBoardTiles(Consumer<Integer> sendMove) {
        boardTiles = new BoardTiles(sendMove);
    }

    public void disableClicks() {
        boardTiles.disableClicks();
    }

    private void assembleInitialSceneGraph() {
        this.getChildren().clear();
        this.getChildren().addAll(
                boardTiles,
                statusText
        );
    }

    private void addCss() {
        this.getStyleClass().add("board-ui-container");
    }

}
