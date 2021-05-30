package com.github.zsoltk.rf1.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.zsoltk.rf1.model.board.Position
import com.github.zsoltk.rf1.model.board.Square
import com.github.zsoltk.rf1.model.game.GameController
import com.github.zsoltk.rf1.model.game.state.UiState
import com.github.zsoltk.rf1.model.piece.Piece
import com.github.zsoltk.rf1.ui.Rf1Theme

@Composable
fun Board(
    fetchSquare: (Position) -> Square,
    highlightedPositions: List<Position>,
    clickablePositions: List<Position>,
    possibleMoves: List<Position>,
    possibleCaptures: List<Position>,
    onClick: (Position) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        val squareSize = this.maxWidth / 8

        EightByEight { position ->
            Square(
                position = position,
                isHighlighted = position in highlightedPositions,
                clickable = position in clickablePositions,
                isPossibleMove = position in possibleMoves,
                isPossibleCapture = position in possibleCaptures,
                onClick = { onClick(position) },
                isDark = fetchSquare(position).isDark
            )
        }

        EightByEight { position ->
            Piece(
                piece = fetchSquare(position).piece,
                modifier = Modifier.offset()
            )
        }
    }
}

@Composable
private fun EightByEight(
    content: @Composable (Position) -> Unit
) {
    Column {
        for (rank in 8 downTo 1) {
            Row(Modifier.weight(1f)) {
                for (file in 1..8) {
                    InSquare(Modifier.weight(1f)) {
                        content(Position.from(file, rank))
                    }
                }
            }
        }
    }
}

@Composable
private fun InSquare(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        content()
    }
}

@Composable
private fun Square(
    position: Position,
    isHighlighted: Boolean,
    clickable: Boolean,
    onClick: () -> Unit,
    isPossibleMove: Boolean,
    isPossibleCapture: Boolean,
    isDark: Boolean,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .clickable(
                enabled = clickable,
                onClick = onClick
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(if (isDark) Color.LightGray else Color.White)
        }

        if (isHighlighted) {
            HighlightSquare()
        }
        if (position.file == 1) {
            PositionLabel(position.rank.toString(), Alignment.TopStart)
        }
        if (position.rank == 1) {
            PositionLabel(
                position.fileAsLetter.toString(),
                Alignment.BottomEnd
            )
        }

        if (isPossibleMove) {
            PossibleMove(onClick)
        } else if (isPossibleCapture) {
            PossibleCapture(onClick)
        }
    }
}

@Composable
private fun HighlightSquare() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            color = Color.Yellow,
            size = size,
            alpha = 0.15f
        )
    }
}

@Composable
private fun PositionLabel(
    text: String,
    alignment: Alignment
) {
    Box(
        contentAlignment = alignment,
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 2.dp)
    ) {
        // TODO text colour = inverse of square colour
        Text(
            text = text,
            fontSize = 10.sp
        )
    }
}

@Composable
private fun Piece(piece: Piece?, modifier: Modifier = Modifier) {
    piece?.let {
        Text(
            text = it.symbol,
            modifier = modifier,
            fontSize = 40.sp
        )
    }
}

@Composable
private fun PossibleMove(
    onClick: () -> Unit
) {
    CircleDecoratedSquare(
        onClick = onClick,
        radius = { size.minDimension / 6f },
        drawStyle = { Fill }
    )
}

@Composable
private fun PossibleCapture(
    onClick: () -> Unit
) {
    CircleDecoratedSquare(
        onClick = onClick,
        radius = { size.minDimension / 3f },
        drawStyle = { Stroke(width = size.minDimension / 12f) }
    )
}

@Composable
private fun CircleDecoratedSquare(
    onClick: () -> Unit,
    radius: DrawScope.() -> Float,
    drawStyle: DrawScope.() -> DrawStyle
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
    ) {
        drawCircle(
            color = Color.DarkGray,
            radius = radius(this),
            alpha = 0.25f,
            style = drawStyle(this)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BoardPreview() {
    Rf1Theme {
        val game = com.github.zsoltk.rf1.model.game.Game()
        val uiState = UiState()
        val gameController = GameController(game, uiState).apply {
            applyMove(Position.e2, Position.e4)
            applyMove(Position.e7, Position.e5)
            applyMove(Position.b1, Position.c3)
            applyMove(Position.b8, Position.c6)
            applyMove(Position.f1, Position.b5)
            applyMove(Position.d7, Position.d5)
            applyMove(Position.e4, Position.d5)
            applyMove(Position.d8, Position.d5)
            applyMove(Position.d1, Position.f3)
            applyMove(Position.c8, Position.g4)
        }
        uiState.apply {
            selectedPosition = Position.f3
        }

        Board(
            fetchSquare = { gameController.square(it) },
            highlightedPositions = gameController.highlightedPositions(),
            clickablePositions = gameController.clickablePositions(),
            possibleMoves = gameController.possibleMovesWithoutCaptures(),
            possibleCaptures = gameController.possibleCaptures(),
            onClick = { gameController.onClick(it) }
        )
    }
}
