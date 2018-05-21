package tictactoe.gui.options;

import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class BoardSizeOptions extends VBox {

    private OptionButton threeByThree;
    private OptionButton fourByFour;

    public BoardSizeOptions(Consumer<Integer> sendBoardSize) {
        threeByThree = setupButton("3x3", 3, sendBoardSize);
        fourByFour = setupButton("4x4", 4, sendBoardSize);
        setup();
    }

    private void setup() {
        this.getChildren().addAll(
                threeByThree,
                fourByFour
        );
    }

    private OptionButton setupButton(String name, int option, Consumer<Integer> sendBoardSize) {
        OptionButton optionButton = new OptionButton(name, option);
        optionButton.onClick(sendBoardSize);
        return optionButton;
    }

}
