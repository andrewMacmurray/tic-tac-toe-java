package tictactoe.core.players;

import tictactoe.core.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MiniMax {

    private final PlayerSymbol player;
    private final PlayerSymbol opponent;
    private final int maxDepth = 7;

    public MiniMax(PlayerSymbol player) {
        this.player = player;
        this.opponent = player.getAlternate();
    }

    public int execute(Board board) {
        return scoresMap(board)
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    private Map<Integer, Integer> scoresMap(Board board) {
        Map<Integer, Integer> scoresMap = new HashMap<>();
        for (int move : board.allAvailableMoves()) {
            Board nextBoard = board.makeMove(move, player);
            scoresMap.put(move, miniMax(nextBoard));
        }
        return scoresMap;
    }

    private int miniMax(Board board) {
        return minimize(board, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private int minimize(Board board, int depth, int alpha, int beta) {
        if (board.isTerminal() || depth == 1) {
            return heuristicValue(board, depth);
        } else {
            int newBeta = beta;
            for (int move : board.allAvailableMoves()) {
                Board nextBoard = board.makeMove(move, opponent);
                newBeta = Math.min(beta, maximize(nextBoard, depth - 1, alpha, newBeta));
                if (alpha >= newBeta) {
                    return alpha;
                }
            }
            return newBeta;
        }
    }

    private int maximize(Board board, int depth, int alpha, int beta) {
        if (board.isTerminal() || depth == 1) {
            return heuristicValue(board, depth);
        } else {
            int newAlpha = alpha;
            for (int move : board.allAvailableMoves()) {
                Board nextBoard = board.makeMove(move, player);
                newAlpha = Math.max(newAlpha, minimize(nextBoard, depth -1, newAlpha, beta));
                if (newAlpha >= beta) {
                    return beta;
                }
            }
            return newAlpha;
        }
    }

    private int heuristicValue(Board board, int depth) {
        if (board.hasWon(opponent)) {
            return -depth;
        } else if (board.hasWon(player)) {
            return depth;
        } else {
            return 0;
        }
    }
}