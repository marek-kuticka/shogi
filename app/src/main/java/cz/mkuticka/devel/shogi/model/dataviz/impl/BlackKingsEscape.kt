package cz.mkuticka.devel.shogi.model.dataviz.impl

import androidx.compose.ui.graphics.Color
//import com.github.zsoltk.chesso.R
import cz.mkuticka.devel.shogi.R
import cz.mkuticka.devel.shogi.model.piece.Set
import kotlinx.parcelize.Parcelize

@Parcelize
object BlackKingsEscape : KingsEscapeSquares(
    set = Set.BLACK,
    colorScale = Color.Unspecified to Color(0xBBEE6666)
) {
    override val name = R.string.viz_black_kings_escape_squares
}
