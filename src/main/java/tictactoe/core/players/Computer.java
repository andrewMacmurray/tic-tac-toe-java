package tictactoe.core.players;

import tictactoe.core.util.Time;

import java.util.ArrayList;
import java.util.Random;

public abstract class Computer {

    private Time time;

    public Computer(Time time) {
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

