package cz.mkuticka.devel.shogi.ui.app

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import cz.mkuticka.devel.shogi.model.game.controller.GameController
import cz.mkuticka.devel.shogi.model.game.converter.PgnConverter
import cz.mkuticka.devel.shogi.model.game.state.GamePlayState
import cz.mkuticka.devel.shogi.model.game.state.GameState
import cz.mkuticka.devel.shogi.ui.dialogs.GameDialog
import cz.mkuticka.devel.shogi.ui.dialogs.ImportDialog
import cz.mkuticka.devel.shogi.ui.dialogs.PickActiveVisualisationDialog
import cz.mkuticka.devel.shogi.ui.dialogs.PromotionDialog

@Composable
fun GameDialogs(
    gamePlayState: MutableState<GamePlayState>,
    gameController: GameController,
    showVizDialog: MutableState<Boolean>,
    showGameDialog: MutableState<Boolean>,
    showImportDialog: MutableState<Boolean>,
    pgnToImport: MutableState<String?>,
) {
    ManagedPromotionDialog(
        showPromotionDialog = gamePlayState.value.uiState.showPromotionDialog,
        gameController = gameController
    )
    ManagedVizDialog(
        showVizDialog = showVizDialog,
        gameController = gameController
    )

    ManagedGameDialog(
        showGameDialog = showGameDialog,
        showImportDialog = showImportDialog,
        gameState = gamePlayState.value.gameState,
        gameController = gameController,
    )

    ManagedImportDialog(
        showImportDialog = showImportDialog,
        pgnToImport = pgnToImport
    )
}

@Composable
fun ManagedPromotionDialog(
    showPromotionDialog: Boolean,
    gameController: GameController,
) {
    if (showPromotionDialog) {
        PromotionDialog(gameController.toMove) {
            gameController.onPromotionPieceSelected(it)
        }
    }
}

@Composable
fun ManagedVizDialog(
    showVizDialog: MutableState<Boolean>,
    gameController: GameController,
) {
    if (showVizDialog.value) {
        PickActiveVisualisationDialog(
            onDismiss = {
                showVizDialog.value = false
            },
            onItemSelected = {
                showVizDialog.value = false
                gameController.setVisualisation(it)
            }
        )
    }
}

@Composable
fun ManagedGameDialog(
    showGameDialog: MutableState<Boolean>,
    showImportDialog: MutableState<Boolean>,
    gameState: GameState,
    gameController: GameController,
) {
    if (showGameDialog.value) {
        val context = LocalContext.current

        GameDialog(
            onDismiss = {
                showGameDialog.value = false
            },
            onNewGame = {
                showGameDialog.value = false
                gameController.reset()
            },
            onImportGame = {
                showGameDialog.value = false
                showImportDialog.value = true
            },
            onExportGame = {
                showGameDialog.value = false
                val pgn = PgnConverter.export(gameState)
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, pgn)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                ContextCompat.startActivity(context, shareIntent, Bundle())
            }
        )
    }
}

@Composable
fun ManagedImportDialog(
    showImportDialog: MutableState<Boolean>,
    pgnToImport: MutableState<String?>,
) {
    if (showImportDialog.value) {
        ImportDialog(
            onDismiss = {
                showImportDialog.value = false
            },
            onImport = { pgn ->
                showImportDialog.value = false
                pgnToImport.value = pgn
            }
        )
    }
}

