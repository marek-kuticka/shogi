package cz.mkuticka.devel.shogi.ui.chess.square.decoration

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.mkuticka.devel.shogi.ui.chess.square.SquareRenderProperties
import cz.mkuticka.devel.shogi.ui.chess.square.SquareDecoration

open class HighlightSquare(
    private val color: Color,
    private val alpha: Float,
) : SquareDecoration {

    @Composable
    override fun render(properties: SquareRenderProperties) {
        if (properties.isHighlighted) {
            Canvas(properties.sizeModifier) {
                drawRect(
                    color = color,
                    size = size,
                    alpha = alpha
                )
            }
        }
    }
}

