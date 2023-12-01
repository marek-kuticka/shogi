package cz.mkuticka.devel.shogi.model.game.preset

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.mkuticka.devel.shogi.model.board.Board
import cz.mkuticka.devel.shogi.model.board.Position.*
import cz.mkuticka.devel.shogi.model.game.controller.GameController
import cz.mkuticka.devel.shogi.model.game.state.GameSnapshotState
import cz.mkuticka.devel.shogi.model.piece.King
import cz.mkuticka.devel.shogi.model.piece.Queen
import cz.mkuticka.devel.shogi.model.piece.Rook
import cz.mkuticka.devel.shogi.model.piece.Set
import cz.mkuticka.devel.shogi.ui.base.ChessoTheme
import cz.mkuticka.devel.shogi.ui.app.Preset

object ThreefoldRepetitionPreset : Preset {

    override fun apply(gameController: GameController) {
        gameController.apply {
            val board = Board(
                pieces = mapOf(
                    a7 to King(Set.BLACK),
                    e8 to Rook(Set.WHITE),
                    d5 to Queen(Set.WHITE),
                    g2 to King(Set.WHITE),
                )
            )
            reset(
                GameSnapshotState(
                    board = board,
                    toMove = Set.WHITE
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThreefoldRepetitionPresetPreview() {
    ChessoTheme {
        Preset(ThreefoldRepetitionPreset)
    }
}

