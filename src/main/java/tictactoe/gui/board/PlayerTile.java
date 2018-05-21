package tictactoe.gui.board;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tictactoe.core.Mediator;
import tictactoe.core.Tile;

import java.util.function.Consumer;

public class PlayerTile extends StackPane {

    private Tile tile;

    public PlayerTile(Tile tile) {
        this.tile = tile;
        setupTile();
    }

    public void onClick(Consumer<Integer> moveReceiver) {
        this.setOnMouseClicked(e -> {
            if (tile.isEmpty()) {
                moveReceiver.accept(tile.getIndex());
            }
        });
    }

    private void setupTile() {
        addPlayerCssClasses();
        addCssClass("tile");
        this.getChildren().add(innerText());
    }

    private void addPlayerCssClasses() {
        switch (tile.toString()) {
            case "X":
                addCssClass("player-x");
                break;
            case "O":
                addCssClass("player-o");
                break;
            default:
                addCssClass("empty");
                break;
        }
    }

    private void addCssClass(String cssClass) {
        this.getStyleClass().add(cssClass);
    }

    private Text innerText() {
        return new Text(tile.toString(""));
    }

}
