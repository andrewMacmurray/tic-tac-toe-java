package tictactoe.core.ui;

import tictactoe.core.Board;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayerSymbol;

public interface UIRequest {

    int requestMove(Board board, PlayerSymbol playerSymbol);

    Players requestPlayers();

}

