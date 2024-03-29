package com.chess.model.game.pieces;

import com.chess.model.game.ChessGame;
import com.chess.model.util.Colors;
import com.chess.model.game.MoveCommand;
import com.chess.model.util.Tile;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
    public Queen(ChessGame game, Tile tile, Colors color) {
        super(game, tile, color, 9);
    }

    @Override
    public MoveCommand createMoveCommand(Tile tile) {
        return createStandardMoveCommand(tile);
    }

    @Override
    public Set<Tile> getPotentiallyAvailableTiles() {
        Set<Tile> potentiallyAvailableTiles = new HashSet<>();
        addPotentiallyAvailableRectangularTiles(potentiallyAvailableTiles);
        addPotentiallyAvailableDiagonalTiles(potentiallyAvailableTiles);
        return potentiallyAvailableTiles;
    }
}
