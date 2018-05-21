package tictactoe.gui.board;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tictactoe.core.Tile;

import java.util.function.Consumer;

public class PlayerTile extends StackPane {

    private Tile tile;

    public PlayerTile(Tile tile) {
        this.tile = tile;
        setupTile();
    }

    public void onClick(Consumer<Integer> sendMove) {
        this.setOnMouseClicked(e -> {
            if (tile.isEmpty()) {
                sendMove.accept(tile.getIndex());
            }
        });
    }

    private void setupTile() {
        addPlayerCssClasses();
        addCss("tile");
        this.getChildren().add(innerText());
    }

    private void addPlayerCssClasses() {
        switch (tile.toString()) {
            case "X":
                addCss("player-x");
                break;
            case "O":
                addCss("player-o");
                break;
            default:
                addCss("empty");
                break;
        }
    }

    private void addCss(String cssClass) {
        this.getStyleClass().add(cssClass);
    }

    private Text innerText() {
        return new Text(tile.toString(""));
    }

}
