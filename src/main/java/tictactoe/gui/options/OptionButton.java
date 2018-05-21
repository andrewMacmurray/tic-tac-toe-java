package tictactoe.gui.options;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.function.Consumer;

public class OptionButton extends StackPane {

    private Button button;
    private int option;

    public OptionButton(String name, int option) {
        this.button = new Button(name);
        this.option = option;
        setup();
    }

    private void setup() {
        this.getChildren().add(button);
        containerCss();
        buttonCss();
    }

    private void containerCss() {
        this.getStyleClass().add("option");
    }

    private void buttonCss() {
        button.getStyleClass().addAll("option-button", "option-button-" + option);
    }

    public void onClick(Consumer<Integer> sendOption) {
        button.setOnMouseClicked(e -> {
            sendOption.accept(option);
        });
    }

}
