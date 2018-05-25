package tictactoe.gui.board;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tictactoe.core.Tile;

import java.util.function.Consumer;

public class PlayerTile extends StackPane {

    private Tile tile;
    private int boardSize;

    public PlayerTile(Tile tile, int boardSize) {
        this.tile = tile;
        this.boardSize = boardSize;
        setupTile();
    }

    public PlayerTile onClick(Consumer<Integer> sendMove) {
        this.setOnMouseClicked(e -> {
            if (tile.isEmpty()) {
                sendMove.accept(tile.getIndex());
            }
        });
        return this;
    }

    private void setupTile() {
        addCss();
        this.getChildren().add(innerImage());
    }

    private void addCss() {
        this.getStyleClass().addAll(
                tileSizeCss(),
                playerCss(),
                "tile"
        );
    }

    private String tileSizeCss() {
        if (boardSize == 3) {
            return "tile-3x3";
        } else {
            return "tile-4x4";
        }
    }

    private String playerCss() {
        switch (tile.toString()) {
            case "X":
                return "player-x";
            case "O":
                return "player-o";
            default:
                return "empty";
        }
    }

    private ImageView innerImage() {
        switch (tile.toString()) {
            case "X":
                return new ImageTile("/rupaul.jpg", boardSize);
            case "O":
                return new ImageTile("/michelle.jpg", boardSize);
            default:
                return new ImageView();
        }
    }

}
