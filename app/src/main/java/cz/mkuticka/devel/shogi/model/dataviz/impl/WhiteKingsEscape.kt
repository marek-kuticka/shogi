package cz.mkuticka.devel.shogi.model.dataviz.impl

import androidx.compose.ui.graphics.Color
import cz.mkuticka.devel.shogi.R
import cz.mkuticka.devel.shogi.model.piece.Set
import kotlinx.parcelize.Parcelize

@Parcelize
object WhiteKingsEscape : KingsEscapeSquares(
    set = Set.WHITE,
    colorScale = Color.Unspecified to Color(0xBB6666EE)
) {
    override val name = R.string.viz_white_kings_escape_squares
}
