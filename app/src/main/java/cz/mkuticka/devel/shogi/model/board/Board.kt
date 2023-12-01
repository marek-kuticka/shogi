package cz.mkuticka.devel.shogi.model.board

import android.os.Parcelable
import cz.mkuticka.devel.shogi.model.board.Position.*
import cz.mkuticka.devel.shogi.model.move.PieceEffect
import cz.mkuticka.devel.shogi.model.piece.*
import cz.mkuticka.devel.shogi.model.piece.Set
import cz.mkuticka.devel.shogi.model.piece.Set.BLACK
import cz.mkuticka.devel.shogi.model.piece.Set.WHITE
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.lang.IllegalArgumentException

@Parcelize
data class Board(
    val pieces: Map<Position, Piece>
) : Parcelable {
    constructor() : this(
        pieces = initialPieces
    )

    @IgnoredOnParcel
    val squares = Position.values().map { position ->
        position to Square(position, pieces[position])
    }.toMap()

    operator fun get(position: Position): Square =
        squares[position]!!

    operator fun get(file: File, rank: Int): Square? =
        get(file.ordinal + 1, rank)

    operator fun get(file: Int, rank: Int): Square? {
        return try {
            val position = Position.from(file, rank)
            squares[position]
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    fun find(piece: Piece): Square? =
        squares.values.firstOrNull { it.piece == piece }

    inline fun <reified T : Piece> find(set: Set): List<Square> =
        squares.values.filter {
            it.piece != null &&
                it.piece::class == T::class &&
                it.piece.set == set
        }

    fun pieces(set: Set): Map<Position, Piece> =
        pieces.filter { (_, piece) -> piece.set == set }

    fun apply(effect: PieceEffect?): Board =
        effect?.applyOn(this) ?: this
}

private val initialPieces = mapOf(
//    a8 to Rook(BLACK),
//    b8 to Knight(BLACK),
//    c8 to Bishop(BLACK),
//    d8 to Queen(BLACK),
//    e8 to King(BLACK),
//    f8 to Bishop(BLACK),
//    g8 to Knight(BLACK),
//    h8 to Rook(BLACK),
//
    a7 to Pawn(BLACK),
    b7 to Pawn(BLACK),
    c7 to Pawn(BLACK),
    d7 to Pawn(BLACK),
    e7 to Pawn(BLACK),
    f7 to Pawn(BLACK),
    g7 to Pawn(BLACK),
    h7 to Pawn(BLACK),

    a3 to Pawn(WHITE),
    e5 to GoldenGeneral(WHITE),
    f1 to GoldenGeneral(WHITE),
    b3 to Pawn(WHITE),
    c3 to Pawn(WHITE),
    d3 to Pawn(WHITE),
    e3 to Pawn(WHITE),
    f3 to Pawn(WHITE),
    g3 to Pawn(WHITE),
    h3 to Pawn(WHITE),

    c5 to SilverGeneral(WHITE)
//
//    a1 to Rook(WHITE),
//    b1 to Knight(WHITE),
//    c1 to Bishop(WHITE),
//    d1 to Queen(WHITE),
//    e1 to King(WHITE),
//    f1 to Bishop(WHITE),
//    g1 to Knight(WHITE),
//    h1 to Rook(WHITE),
)
