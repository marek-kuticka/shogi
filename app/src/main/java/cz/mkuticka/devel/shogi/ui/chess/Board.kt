package cz.mkuticka.devel.shogi.ui.chess

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.board.Position.*
import cz.mkuticka.devel.shogi.model.game.controller.GameController
import cz.mkuticka.devel.shogi.model.game.state.GamePlayState
import cz.mkuticka.devel.shogi.model.game.state.GameSnapshotState
import cz.mkuticka.devel.shogi.model.game.state.UiState
import cz.mkuticka.devel.shogi.ui.base.ChessoTheme
import cz.mkuticka.devel.shogi.ui.chess.board.BoardRenderProperties
import cz.mkuticka.devel.shogi.ui.chess.board.DefaultBoardRenderer

@Composable
fun Board(
    gamePlayState: GamePlayState,
    gameController: GameController,
    isFlipped: Boolean = false,
) {
    Board(
        fromState = gamePlayState.gameState.lastActiveState,
        toState = gamePlayState.gameState.currentSnapshotState,
        uiState = gamePlayState.uiState,
        isFlipped = isFlipped,
        onClick = { position -> gameController.onClick(position) }
    )
}

@Composable
fun Board(
    fromState: GameSnapshotState,
    toState: GameSnapshotState,
    uiState: UiState,
    isFlipped: Boolean = false,
    onClick: (Position) -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(all = 4.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        val boardProperties =
            BoardRenderProperties(
                fromState = fromState,
                toState = toState,
                uiState = uiState,
                squareSize = maxWidth / 9,
                isFlipped = isFlipped,
                onClick = onClick
            )

        DefaultBoardRenderer.decorations.forEach {
            it.render(properties = boardProperties)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardPreview() {
    ChessoTheme {
        var gamePlayState = GamePlayState()
        val gameController = GameController({ gamePlayState }, { gamePlayState = it }).apply {
            applyMove(e2, e4)
            applyMove(e7, e5)
            applyMove(b1, c3)
            applyMove(b8, c6)
            applyMove(f1, b5)
            applyMove(d7, d5)
            applyMove(e4, d5)
            applyMove(d8, d5)
            applyMove(d1, f3)
            applyMove(c8, g4)
            onClick(f3)
        }

        Board(
            gameController = gameController,
            gamePlayState = gamePlayState
        )
    }
}
