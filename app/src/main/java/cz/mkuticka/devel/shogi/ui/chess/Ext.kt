package cz.mkuticka.devel.shogi.ui.chess

import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import cz.mkuticka.devel.shogi.R
import cz.mkuticka.devel.shogi.model.board.Coordinate
import cz.mkuticka.devel.shogi.model.game.state.GameState

fun Coordinate.toOffset(squareSize: Dp): Offset =
    Offset(
        x = (x - 1) * squareSize.value,
        y = (y - 1) * squareSize.value
    )


fun Coordinate.toOffsetModifier(squareSize: Dp): Modifier =
    Modifier
        .offset(
            Dp((x - 1) * squareSize.value),
            Dp((y - 1) * squareSize.value)
        )

fun Offset.toModifier(): Modifier =
    Modifier.offset(Dp(x), Dp(y))

fun GameState.resolutionText(): Int =
    when (resolution) {
        cz.mkuticka.devel.shogi.model.game.Resolution.IN_PROGRESS -> when (toMove) {
            cz.mkuticka.devel.shogi.model.piece.Set.WHITE -> R.string.resolution_white_to_move
            cz.mkuticka.devel.shogi.model.piece.Set.BLACK -> R.string.resolution_black_to_move
        }
        cz.mkuticka.devel.shogi.model.game.Resolution.CHECKMATE -> when (toMove) {
            cz.mkuticka.devel.shogi.model.piece.Set.WHITE -> R.string.resolution_black_wins
            cz.mkuticka.devel.shogi.model.piece.Set.BLACK -> R.string.resolution_white_wins
        }
        cz.mkuticka.devel.shogi.model.game.Resolution.STALEMATE -> R.string.resolution_stalemate
        cz.mkuticka.devel.shogi.model.game.Resolution.DRAW_BY_REPETITION -> R.string.resolution_draw_by_repetition
        cz.mkuticka.devel.shogi.model.game.Resolution.INSUFFICIENT_MATERIAL -> R.string.resolution_insufficient_material
    }
