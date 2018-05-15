package tictactoe.mocks;

import tictactoe.core.util.Time;

public class MockTime implements Time {

    public String timeLog = "";

    @Override
    public void pause(int ms) {
        timeLog = "some time has passed";
    }

}
