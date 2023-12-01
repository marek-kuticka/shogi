package cz.mkuticka.devel.shogi.model.game.preset

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.game.controller.GameController
import cz.mkuticka.devel.shogi.ui.base.ChessoTheme
import cz.mkuticka.devel.shogi.ui.app.Preset

object EnPassantPreset : Preset {

    override fun apply(gameController: GameController) {
        gameController.apply {
            applyMove(Position.e2, Position.e4)
            applyMove(Position.b8, Position.c6)
            applyMove(Position.e4, Position.e5)
            applyMove(Position.d7, Position.d5)
            onClick(Position.e5)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnPassantPresetPreview() {
    ChessoTheme {
        Preset(EnPassantPreset)
    }
}

