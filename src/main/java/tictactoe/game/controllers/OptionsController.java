package tictactoe.game.controllers;

import tictactoe.core.GameType;

import java.io.InputStream;
import java.io.OutputStream;

public class OptionsController {

    private InputStream in;
    private OutputStream out;
    private GameType gameType;

    public OptionsController(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }
}
