package tictactoe.gui.board;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class StatusText extends StackPane {

    private Text statusText;

    public StatusText() {
        statusText = new Text();
        setup();
    }

    private void setup() {
        appendText();
        addCss();
    }

    private void appendText() {
        this.getChildren().add(statusText);
    }

    private void addCss() {
        this.getStyleClass().add("status-text-container");
        statusText.getStyleClass().add("status-text");
    }

    public void setText(String text) {
        statusText.setText(text);
    }

}