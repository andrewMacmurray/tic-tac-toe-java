package tictactoe.core.players;

import tictactoe.core.util.ThreadControl;

import java.util.ArrayList;
import java.util.Random;

public abstract class Computer {

    private ThreadControl time;

    public Computer(ThreadControl time) {
        this.time = time;
    }

    public void waitOneSecond() {
        time.pause(1000);
    }

    public int randomMove(ArrayList<Integer> moves) {
        Random random = new Random();
        int i = random.nextInt(moves.size());
        return moves.get(i);
    }

}

