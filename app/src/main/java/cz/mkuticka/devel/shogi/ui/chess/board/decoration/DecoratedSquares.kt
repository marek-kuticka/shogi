package cz.mkuticka.devel.shogi.ui.chess.board.decoration

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.input.pointer.pointerInput
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.ui.chess.toOffsetModifier
import cz.mkuticka.devel.shogi.ui.chess.square.SquareDecoration
import cz.mkuticka.devel.shogi.ui.chess.board.BoardRenderProperties
import cz.mkuticka.devel.shogi.ui.chess.square.SquareRenderProperties
import cz.mkuticka.devel.shogi.ui.chess.board.BoardDecoration
import java.util.UUID

class DecoratedSquares(
    private val decorations: List<SquareDecoration>,
) : BoardDecoration {

    @Composable
    override fun render(properties: BoardRenderProperties) {
        Position.values().forEach { position ->
            key(position) {
                val squareProperties = remember(properties) {
                    SquareRenderProperties(
                        position = position,
                        isHighlighted = position in properties.uiState.highlightedPositions,
                        clickable = position in properties.uiState.clickablePositions,
                        isPossibleMoveWithoutCapture = position in properties.uiState.possibleMovesWithoutCaptures,
                        isPossibleCapture = position in properties.uiState.possibleCaptures,
                        onClick = { properties.onClick(position) },
                        boardProperties = properties
                    )
                }
                Square(
                    properties = squareProperties,
                    decorations = decorations
                )
            }
        }
    }

    @Composable
    private fun Square(
        properties: SquareRenderProperties,
        decorations: List<SquareDecoration>,
    ) {
        Box(
            modifier = properties.coordinate
                .toOffsetModifier(properties.boardProperties.squareSize)
                .pointerInput(UUID.randomUUID()) {
                    detectTapGestures(
                        onPress = { if (properties.clickable) properties.onClick() },
                    )
                }
        ) {
            decorations.forEach {
                it.render(properties)
            }
        }
    }
}
