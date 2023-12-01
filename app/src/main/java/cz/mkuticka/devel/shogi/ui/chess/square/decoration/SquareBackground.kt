package cz.mkuticka.devel.shogi.ui.chess.square.decoration

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import cz.mkuticka.devel.shogi.ui.chess.square.SquareRenderProperties
import cz.mkuticka.devel.shogi.ui.chess.square.SquareDecoration

open class SquareBackground(
    private val lightSquareColor: Color,
    private val darkSquareColor: Color,
) : SquareDecoration {

    @Composable
    override fun render(properties: SquareRenderProperties) {
        Canvas(properties.sizeModifier) {

            drawRect(
//                color =
//                if (properties.position.isDarkSquare())
//                    darkSquareColor
//                else
//                    lightSquareColor,
                color = Color.Black,
                style = Stroke(width = 1.0f)

            )

        }
    }
}

