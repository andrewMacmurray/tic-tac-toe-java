package tictactoe.mocks;

import tictactoe.core.players.Time;

public class MockTime implements Time {

    public String timeLog = "";

    @Override
    public void pauseAndThen(int ms, Runnable task) {
        timeLog = "some time has passed";
        task.run();
    }

}
