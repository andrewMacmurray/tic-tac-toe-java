package tictactoe.core;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class WinStatesTest {

    @Test
    public void threeByThreeStates() {
        List<List<Integer>> expectedStates = Arrays.asList(
                Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9),
                Arrays.asList(1, 4, 7), Arrays.asList(2, 5, 8), Arrays.asList(3, 6, 9),
                Arrays.asList(1, 5, 9), Arrays.asList(3, 5, 7)
        );

        List<List<Integer>> actualStates = new WinStates(3).generate();

        for (int i = 0; i < expectedStates.size(); i++) {
            assertTrue(
                    "should contain all winning states for a 3x3 board",
                    expectedStates.get(i).equals(actualStates.get(i))
            );
        }
    }

    @Test
    public void fourByFourStates() {
        List<List<Integer>> expectedStates = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12),
                Arrays.asList(13, 14, 15, 16),
                Arrays.asList(1, 5, 9, 13),
                Arrays.asList(2, 6, 10, 14),
                Arrays.asList(3, 7, 11, 15),
                Arrays.asList(4, 8, 12, 16),
                Arrays.asList(1, 6, 11, 16),
                Arrays.asList(4, 7, 10, 13)
        );

        List<List<Integer>> actualStates = new WinStates(4).generate();

        for (int i = 0; i < expectedStates.size(); i++) {
            assertTrue(
                    "should contain all winning states for a 4x4 board",
                    expectedStates.get(i).equals(actualStates.get(i))
            );
        }
    }
}

