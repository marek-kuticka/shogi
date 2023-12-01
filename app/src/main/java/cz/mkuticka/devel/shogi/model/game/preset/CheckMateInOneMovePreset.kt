package cz.mkuticka.devel.shogi.model.game.preset

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.game.controller.GameController
import cz.mkuticka.devel.shogi.ui.base.ChessoTheme
import cz.mkuticka.devel.shogi.ui.app.Preset

object CheckMateInOneMovePreset : Preset {

    override fun apply(gameController: GameController) {
        gameController.apply {
            applyMove(Position.e2, Position.e4)
            applyMove(Position.e7, Position.e5)
            applyMove(Position.d1, Position.h5)
            applyMove(Position.b8, Position.c6)
            applyMove(Position.f1, Position.c4)
            applyMove(Position.f8, Position.c5)
            onClick(Position.h5)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckMateInOneMovePreview() {
    ChessoTheme {
        Preset(CheckMateInOneMovePreset)
    }
}

