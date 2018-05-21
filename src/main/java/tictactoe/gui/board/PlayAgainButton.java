package tictactoe.gui.board;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class PlayAgainButton extends StackPane {

    private Button playAgainButton;

    public PlayAgainButton(Runnable task) {
        playAgainButton = new Button("Play Again?");
        setup(task);
    }

    private void setup(Runnable task) {
        this.getChildren().add(playAgainButton);
        addCss();
        addListener(task);
    }

    private void addCss() {
        playAgainButton.getStyleClass().addAll(
                "play-again",
                "option-button"
        );
    }

    private void addListener(Runnable task) {
        playAgainButton.setOnMouseClicked(e -> task.run());
    }

}
