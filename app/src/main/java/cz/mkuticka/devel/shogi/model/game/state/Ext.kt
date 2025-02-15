package cz.mkuticka.devel.shogi.model.game.state

import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.board.isDarkSquare
import cz.mkuticka.devel.shogi.model.board.isLightSquare
import cz.mkuticka.devel.shogi.model.move.BoardMove
import cz.mkuticka.devel.shogi.model.move.BoardMove.Ambiguity.AMBIGUOUS_FILE
import cz.mkuticka.devel.shogi.model.move.BoardMove.Ambiguity.AMBIGUOUS_RANK
import cz.mkuticka.devel.shogi.model.piece.Bishop
import cz.mkuticka.devel.shogi.model.piece.King
import cz.mkuticka.devel.shogi.model.piece.Knight
import cz.mkuticka.devel.shogi.model.piece.Piece
import cz.mkuticka.devel.shogi.model.piece.Set
import java.util.EnumSet

fun List<GameSnapshotState>.hasThreefoldRepetition(): Boolean =
    map { it.toRepetitionRelevantState().hashCode() }
        .groupBy { it }
        .map { it.value.size }
        .any { it > 2 }

fun Map<Position, Piece>.hasInsufficientMaterial(): Boolean =
    when {
        size == 2 && hasWhiteKing() && hasBlackKing() -> true
        size == 3 && hasWhiteKing() && hasBlackKing() && hasBishop() -> true
        size == 3 && hasWhiteKing() && hasBlackKing() && hasKnight() -> true
        size == 4 && hasWhiteKing() && hasBlackKing() && hasBishopsOnSameColor() -> true
        else -> false
    }

private fun Map<Position, Piece>.hasWhiteKing(): Boolean =
    values.find { it.set == Set.WHITE && it is King } != null

private fun Map<Position, Piece>.hasBlackKing(): Boolean =
    values.find { it.set == Set.BLACK && it is King } != null

private fun Map<Position, Piece>.hasBishop(): Boolean =
    values.find { it is Bishop } != null

private fun Map<Position, Piece>.hasKnight(): Boolean =
    values.find { it is Knight } != null

private fun Map<Position, Piece>.hasBishopsOnSameColor(): Boolean {
    val bishops = filter { it.value is Bishop }

    return bishops.size > 1 && (bishops.all { it.key.isLightSquare() } || bishops.all { it.key.isDarkSquare() })
}

fun BoardMove.applyAmbiguity(gameSnapshotState: GameSnapshotState): BoardMove =
    gameSnapshotState.board
        .pieces(gameSnapshotState.toMove)
        .filter { (_, piece) -> piece.textSymbol == this.piece.textSymbol }
        .flatMap { (_, piece) -> piece.pseudoLegalMoves(gameSnapshotState, false) }
        .filter { it.to == this.to }
        .map { it.from }
        .distinct() // promotion moves have same `from`, but are different per target piece, we don't need all of those
        .let { similarPiecePositions ->
            val ambiguity = EnumSet.noneOf(BoardMove.Ambiguity::class.java)
            when (similarPiecePositions.size) {
                1 -> this
                else -> {
                    val onSameFile = similarPiecePositions.filter { it.file == from.file }
                    if (onSameFile.size == 1) {
                        ambiguity.add(AMBIGUOUS_FILE)
                    } else {
                        val onSameRank = similarPiecePositions.filter { it.rank == from.rank }
                        if (onSameRank.size == 1) {
                            ambiguity.add(AMBIGUOUS_RANK)
                        } else {
                            ambiguity.add(AMBIGUOUS_FILE)
                            ambiguity.add(AMBIGUOUS_RANK)
                        }
                    }

                    copy(ambiguity = ambiguity)
                }
            }
        }
