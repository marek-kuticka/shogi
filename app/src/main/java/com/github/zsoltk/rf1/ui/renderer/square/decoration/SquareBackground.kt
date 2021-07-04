package com.github.zsoltk.rf1.ui.renderer.square.decoration

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.github.zsoltk.rf1.model.board.isDarkSquare
import com.github.zsoltk.rf1.ui.renderer.square.SquareRenderProperties
import com.github.zsoltk.rf1.ui.renderer.square.SquareDecoration

open class SquareBackground(
    private val lightSquareColor: Color,
    private val darkSquareColor: Color,
) : SquareDecoration {

    @Composable
    override fun render(properties: SquareRenderProperties) {
        Canvas(properties.sizeModifier) {
            drawRect(color = if (properties.position.isDarkSquare()) darkSquareColor else lightSquareColor)
        }
    }
}

