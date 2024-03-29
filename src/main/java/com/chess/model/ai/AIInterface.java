package com.chess.model.ai;

import com.chess.model.game.ChessGameInterface;
import com.chess.model.util.Tile;
import com.chess.model.util.Pair;

public interface AIInterface {
    /**
     * Returns the bot's best move as a pair of tiles (key is starting tile, value is ending tile).
     */
    Pair<Tile, Tile> move();

    /**
     * Sets the current game for the bot.
     */
    void setGame(ChessGameInterface game);
}
