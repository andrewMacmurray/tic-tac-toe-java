package tictactoe.mocks;

import tictactoe.core.util.ThreadControl;

public class MockTime implements ThreadControl {

    public String timeLog = "";

    @Override
    public void pause(int ms) {
        timeLog = "some time has passed";
    }

}
