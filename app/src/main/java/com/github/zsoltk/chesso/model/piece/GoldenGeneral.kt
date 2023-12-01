package com.github.zsoltk.chesso.model.piece

import com.github.zsoltk.chesso.R
import com.github.zsoltk.chesso.model.board.File.*
import com.github.zsoltk.chesso.model.game.state.GameSnapshotState
import com.github.zsoltk.chesso.model.move.BoardMove
import com.github.zsoltk.chesso.model.move.KingSideCastle
import com.github.zsoltk.chesso.model.move.Move
import com.github.zsoltk.chesso.model.move.QueenSideCastle
import com.github.zsoltk.chesso.model.piece.Set.BLACK
import com.github.zsoltk.chesso.model.piece.Set.WHITE
import kotlinx.parcelize.Parcelize

@Parcelize
class GoldenGeneral(override val set: Set) : Piece {

    override val value: Int = Int.MAX_VALUE

    override val asset: Int? =
      null

    override val symbol: String = "金"

    override val textSymbol: String = SYMBOL

    override fun pseudoLegalMoves(gameSnapshotState: GameSnapshotState, checkCheck: Boolean): List<BoardMove> {
        val moves = targets
            .map { it.first * set.direction() to it.second * set.direction() }
            .mapNotNull { singleCaptureMove(gameSnapshotState, it.first, it.second) }
            .toMutableList()

//        if (!checkCheck) {
//            castleKingSide(gameSnapshotState)?.let { moves += it }
//            castleQueenSide(gameSnapshotState)?.let { moves += it }
//        }

        return moves
    }



    companion object {
        const val SYMBOL = "K"
        val targets = listOf(
//            -1 to -1,
            -1 to 0,
            -1 to 1,
            0 to 1,
            0 to -1,
//            1 to -1,
            1 to 0,
            1 to 1,
        )
    }
}
