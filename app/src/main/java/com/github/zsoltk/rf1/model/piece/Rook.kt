package com.github.zsoltk.rf1.model.piece

import com.github.zsoltk.rf1.model.game.state.GameSnapshotState
import com.github.zsoltk.rf1.model.move.BoardMove
import com.github.zsoltk.rf1.model.piece.Set.BLACK
import com.github.zsoltk.rf1.model.piece.Set.WHITE

class Rook(override val set: Set) : Piece {

    override val value: Int = 5

    override val symbol: String = when (set) {
        WHITE -> "♖"
        BLACK -> "♜"
    }

    override val textSymbol: String = "R"

    override fun pseudoLegalMoves(gameSnapshotState: GameSnapshotState, checkCheck: Boolean): List<BoardMove> =
        lineMoves(gameSnapshotState, directions)

    companion object {
        val directions = listOf(
            0 to -1,
            0 to 1,
            -1 to 0,
            1 to 0,
        )
    }
}
