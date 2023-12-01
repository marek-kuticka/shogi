package cz.mkuticka.devel.shogi.ui.chess.square.decoration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import cz.mkuticka.devel.shogi.model.board.Coordinate
import cz.mkuticka.devel.shogi.ui.chess.square.SquareRenderProperties
import cz.mkuticka.devel.shogi.ui.chess.square.SquareDecoration

open class SquarePositionLabelPacked(
    private val display: (Coordinate) -> Boolean,
) : SquareDecoration {

    @Composable
    override fun render(properties: SquareRenderProperties) {
        if (display(properties.coordinate)) {
            PositionLabel(
                text = properties.position.toString(),
                alignment = Alignment.TopStart,
                modifier = properties.sizeModifier
            )
        }
    }
}
