package cz.mkuticka.devel.shogi.model.piece

import cz.mkuticka.devel.shogi.model.board.Board
import cz.mkuticka.devel.shogi.model.board.Square
import cz.mkuticka.devel.shogi.model.game.state.GameSnapshotState
import cz.mkuticka.devel.shogi.model.move.BoardMove
import cz.mkuticka.devel.shogi.model.move.Capture
import cz.mkuticka.devel.shogi.model.move.Move
import cz.mkuticka.devel.shogi.model.move.Promotion
import cz.mkuticka.devel.shogi.model.piece.Set.WHITE
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
