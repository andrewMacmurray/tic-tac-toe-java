package tictactoe.gui.title;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class LogoPiece extends StackPane {

    private String titleText;

    public LogoPiece(String text) {
        this.titleText = text;
        addText();
        addCss();
    }

    private void addText() {
        Text text = new Text(titleText);
        this.getChildren().add(text);
    }

    private void addCss() {
        this.getStyleClass().addAll(
                "logo-piece",
                titleText.toLowerCase()
        );
    }

}