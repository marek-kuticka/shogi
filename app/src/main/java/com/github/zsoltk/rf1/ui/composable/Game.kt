package com.github.zsoltk.rf1.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.zsoltk.rf1.model.board.Position.*
import com.github.zsoltk.rf1.model.game.state.GameState
import com.github.zsoltk.rf1.model.game.controller.GameController
import com.github.zsoltk.rf1.model.game.Resolution
import com.github.zsoltk.rf1.model.game.preset.Preset
import com.github.zsoltk.rf1.model.game.state.GamePlayState
import com.github.zsoltk.rf1.ui.Rf1Theme

@Composable
fun Game(state: GamePlayState = GamePlayState(), preset: Preset? = null) {
    var gamePlayState by rememberSaveable { mutableStateOf(state) }
    val gameController = remember { GameController(
        getGamePlayState = { gamePlayState },
        setGamePlayState = { gamePlayState = it },
        preset = preset
    ) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        ToMove(gamePlayState.gameState)
        Moves(gamePlayState.gameState, onClickMove = { gameController.goToMove(it) })
        CapturedPieces(gamePlayState.gameState)
        Board(
            gamePlayState = gamePlayState,
            gameController = gameController
        )
        TimeTravelButtons(
            gamePlayState = gamePlayState,
            onStepBack = { gameController.stepBackward() },
            onStepForward = { gameController.stepForward() }
        )
    }

    if (gamePlayState.uiState.showPromotionDialog) {
        PromotionDialog(gameController.toMove) {
            gameController.onPromotionPieceSelected(it)
        }
    }
}

@Composable
private fun ToMove(gameState: GameState) {
    val text = when (gameState.resolution) {
        Resolution.IN_PROGRESS -> "${gameState.toMove} to move"
        else -> gameState.resolution.toString().replace("_", " ")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
private fun TimeTravelButtons(
    gamePlayState: GamePlayState,
    onStepBack: () -> Unit,
    onStepForward: () -> Unit,
) {
    Row(
        Modifier.padding(24.dp)
    ) {
        Button(
            onClick = onStepBack,
            enabled = gamePlayState.gameState.hasPrevIndex
        ) {
            Text("<")
        }
        Button(
            onClick = onStepForward,
            enabled = gamePlayState.gameState.hasNextIndex
        ) {
            Text(">")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    Rf1Theme {
        var gamePlayState = GamePlayState()
        GameController({ gamePlayState }, { gamePlayState = it}).apply {
            applyMove(e2, e4)
            applyMove(e7, e5)
            applyMove(b1, c3)
            applyMove(b8, c6)
            applyMove(f1, b5)
            applyMove(d7, d5)
            applyMove(e4, d5)
            applyMove(d8, d5)
            applyMove(c3, d5)
            onClick(g8)
        }
        Game(
            state = gamePlayState,
        )
    }
}
