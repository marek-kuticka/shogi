package cz.mkuticka.devel.shogi.model.game.state

import android.os.Parcelable
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.piece.Piece
import kotlinx.parcelize.Parcelize

sealed class PromotionState : Parcelable {
    @Parcelize
    object None : PromotionState()

    @Parcelize
    data class Await(val position: Position) : PromotionState()

    @Parcelize
    data class ContinueWith(val piece: Piece) : PromotionState()
}
