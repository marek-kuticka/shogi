package cz.mkuticka.devel.shogi.ui.chess.square

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import cz.mkuticka.devel.shogi.model.board.Coordinate
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.board.toCoordinate
import cz.mkuticka.devel.shogi.ui.chess.board.BoardRenderProperties

data class SquareRenderProperties(
    val position: Position,
    val isHighlighted: Boolean,
    val clickable: Boolean,
    val onClick: () -> Unit,
    val isPossibleMoveWithoutCapture: Boolean,
    val isPossibleCapture: Boolean,
    val boardProperties: BoardRenderProperties
) {
    val coordinate: Coordinate =
        position.toCoordinate(boardProperties.isFlipped)

    val sizeModifier: Modifier
        get() = Modifier.size(boardProperties.squareSize)
}
