package cz.mkuticka.devel.shogi.model.game.state

import android.os.Parcelable
import cz.mkuticka.devel.shogi.model.board.Board
import cz.mkuticka.devel.shogi.model.board.Position.*
import cz.mkuticka.devel.shogi.model.move.BoardMove
import cz.mkuticka.devel.shogi.model.piece.King
import cz.mkuticka.devel.shogi.model.piece.Rook
import cz.mkuticka.devel.shogi.model.piece.Set
import cz.mkuticka.devel.shogi.model.piece.Set.BLACK
import cz.mkuticka.devel.shogi.model.piece.Set.WHITE
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastlingInfo(
    val holders: Map<Set, Holder> = mapOf(
        WHITE to Holder(),
        BLACK to Holder()
    )
) : Parcelable {

    @Parcelize
    data class Holder(
        val kingHasMoved: Boolean = false,
        val kingSideRookHasMoved: Boolean = false,
        val queenSideRookHasMoved: Boolean = false,
    ) : Parcelable {

        val canCastleKingSide: Boolean
            get() = !kingHasMoved && !kingSideRookHasMoved

        val canCastleQueenSide: Boolean
            get() = !kingHasMoved && !queenSideRookHasMoved
    }

    operator fun get(set: Set) = holders[set]!!

    fun apply(boardMove: BoardMove): CastlingInfo {
        val move = boardMove.move
        val piece = boardMove.piece
        val set = piece.set
        val holder = holders[set]!!

        val kingSideRookInitialPosition = if (set == WHITE) h1 else h8
        val queenSideRookInitialPosition = if (set == WHITE) a1 else a8

        val updatedHolder = holder.copy(
            kingHasMoved = holder.kingHasMoved || piece is King,
            kingSideRookHasMoved = holder.kingSideRookHasMoved || piece is Rook && move.from == kingSideRookInitialPosition,
            queenSideRookHasMoved = holder.queenSideRookHasMoved || piece is Rook && move.from == queenSideRookInitialPosition,
        )

        return copy(
            holders = holders
                .minus(set)
                .plus(set to updatedHolder)
        )
    }

    companion object {
        fun from(board: Board): CastlingInfo {
            val whitePieces = board.pieces(WHITE)
            val whiteHolder = Holder(
                kingHasMoved = whitePieces[e1] !is King,
                kingSideRookHasMoved = whitePieces[h1] !is Rook,
                queenSideRookHasMoved = whitePieces[a1] !is Rook,
            )
            val blackPieces = board.pieces(BLACK)
            val blackHolder = Holder(
                kingHasMoved = blackPieces[e8] !is King,
                kingSideRookHasMoved = blackPieces[h8] !is Rook,
                queenSideRookHasMoved = blackPieces[a8] !is Rook,
            )

            return CastlingInfo(
                mapOf(
                    WHITE to whiteHolder,
                    BLACK to blackHolder
                )
            )
        }
    }
}
