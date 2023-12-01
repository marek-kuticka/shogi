package cz.mkuticka.devel.shogi.model.game.state

import android.os.Parcelable
import cz.mkuticka.devel.shogi.model.board.Board
import cz.mkuticka.devel.shogi.model.piece.Set
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepetitionRelevantState(
    val board: Board,
    val toMove: Set,
    val castlingInfo: CastlingInfo,
) : Parcelable
