package com.github.zsoltk.chesso.model.piece

import com.github.zsoltk.chesso.R
import com.github.zsoltk.chesso.model.board.Board
import com.github.zsoltk.chesso.model.board.Square
import com.github.zsoltk.chesso.model.game.state.GameSnapshotState
import com.github.zsoltk.chesso.model.move.BoardMove
import com.github.zsoltk.chesso.model.move.Capture
import com.github.zsoltk.chesso.model.move.Move
import com.github.zsoltk.chesso.model.move.Promotion
import com.github.zsoltk.chesso.model.piece.Set.BLACK
import com.github.zsoltk.chesso.model.piece.Set.WHITE
import kotlinx.parcelize.Parcelize

@Parcelize
class Pawn(override val set: Set) : Piece {

    override val value: Int = 1

    override val asset: Int? =
//        when (set) {
//            WHITE -> R.drawable.pawn_light
//            BLACK -> R.drawable.pawn_dark
//        }
        null

    override val symbol: String = "歩"

    override val textSymbol: String = ""

    override fun pseudoLegalMoves(gameSnapshotState: GameSnapshotState, checkCheck: Boolean): List<BoardMove> {
        val board = gameSnapshotState.board
        val square = board.find(this) ?: return emptyList()
        val moves = mutableListOf<BoardMove>()

        advanceSingle(board, square)?.let { moves += it }

        return moves.flatMap {
            it.checkForPromotion()
        }
    }

    private fun advanceSingle(
        board: Board,
        square: Square
    ): BoardMove? {
        val deltaRank = set.direction()
        val target = board[square.file, square.rank + deltaRank]
        return if (target?.isEmpty == true) BoardMove(
            move = Move(this, square.position, target.position)
        ) else if (target?.hasPiece(set.opposite()) == true) BoardMove(
            move = Move(this, square.position, target.position),
            preMove = Capture(target.piece!!, target.position)
        ) else null
    }

}

private fun BoardMove.checkForPromotion(): List<BoardMove> =
    if (move.to.rank == if (piece.set == WHITE) 8 else 1) {
        listOf(
            copy(consequence = Promotion(move.to, Queen(piece.set))),
            copy(consequence = Promotion(move.to, Rook(piece.set))),
            copy(consequence = Promotion(move.to, Bishop(piece.set))),
            copy(consequence = Promotion(move.to, Knight(piece.set))),
        )
    } else listOf(this)
