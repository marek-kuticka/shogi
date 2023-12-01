package cz.mkuticka.devel.shogi.model.game.preset

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.mkuticka.devel.shogi.model.board.Board
import cz.mkuticka.devel.shogi.model.board.Position.*
import cz.mkuticka.devel.shogi.model.game.controller.GameController
import cz.mkuticka.devel.shogi.model.game.state.GameSnapshotState
import cz.mkuticka.devel.shogi.model.piece.King
import cz.mkuticka.devel.shogi.model.piece.Knight
import cz.mkuticka.devel.shogi.model.piece.Pawn
import cz.mkuticka.devel.shogi.model.piece.Queen
import cz.mkuticka.devel.shogi.model.piece.Rook
import cz.mkuticka.devel.shogi.model.piece.Set.BLACK
import cz.mkuticka.devel.shogi.model.piece.Set.WHITE
import cz.mkuticka.devel.shogi.ui.base.ChessoTheme
import cz.mkuticka.devel.shogi.ui.app.Preset

object AmbiguityCheckPreset : Preset {

    override fun apply(gameController: GameController) {
        gameController.apply {
            val board = Board(
                pieces = mapOf(
                    e2 to King(BLACK),
                    c3 to Pawn(BLACK),
                    h1 to King(WHITE),
                    e4 to Knight(WHITE),
                    a4 to Knight(WHITE),
                    a2 to Knight(WHITE),
                    b1 to Knight(WHITE),
                    d1 to Knight(WHITE),
                    b7 to Rook(WHITE),
                    c8 to Rook(WHITE),
                    d7 to Rook(WHITE),
                    g8 to Queen(WHITE),
                    h8 to Queen(WHITE),
                    h7 to Queen(WHITE),
                )
            )
            reset(
                GameSnapshotState(
                    board = board,
                    toMove = WHITE
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmbiguityCheckPresetPreview() {
    ChessoTheme {
        Preset(AmbiguityCheckPreset)
    }
}

