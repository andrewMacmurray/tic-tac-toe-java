package tictactoe.core.util;

public class SimpleTime implements Time {

    @Override
    public void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
