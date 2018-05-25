package tictactoe.gui;

import tictactoe.core.players.PlayerSymbol;

public class DragPlayer {

    public static String turn(PlayerSymbol playerSymbol) {
        return "Your turn " + name(playerSymbol);
    }

    public static String win(PlayerSymbol playerSymbol) {
        return name(playerSymbol) + " won!";
    }

    private static String name(PlayerSymbol playerSymbol) {
        if (playerSymbol == PlayerSymbol.X) {
            return "Ru";
        } else {
            return "Michelle";
        }
    }

}
