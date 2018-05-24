package tictactoe.gui.board;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class PlayAgainButton extends StackPane {

    private Button playAgainButton;
    private Runnable clickHandler;

    public PlayAgainButton(Runnable clickHandler) {
        playAgainButton = new Button("Play Again?");
        this.clickHandler = clickHandler;
        setup();
    }

    private void setup() {
        this.getChildren().add(playAgainButton);
        addCss();
        addListener();
    }

    private void addCss() {
        playAgainButton.getStyleClass().addAll(
                "play-again",
                "option-button"
        );
    }

    private void addListener() {
        playAgainButton.setOnMouseClicked(e -> clickHandler.run());
    }

}
