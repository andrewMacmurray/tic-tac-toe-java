package tictactoe.gui.title;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tictactoe.gui.SlideUpTransition;

public class TitleText extends StackPane {

    private Text text;
    private SlideUpTransition transition;

    public TitleText() {
        this.getStyleClass().add("title-text-container");
        setup();
    }

    private void setup() {
        addText();
        setupAnimation();
    }

    private void addText() {
        Text text = new Text("Tic - Tac - Toe");
        text.getStyleClass().add("title-text");
        this.text = text;
        this.getChildren().add(text);
    }

    public void animateAndThen(Runnable finish) {
        transition.setOnEnd(finish);
        transition.play();
    }

    private void setupAnimation() {
        transition = new SlideUpTransition(text, 700).setFrom(500);
    }

}