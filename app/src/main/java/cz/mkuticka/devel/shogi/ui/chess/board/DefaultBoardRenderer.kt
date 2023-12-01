package cz.mkuticka.devel.shogi.ui.chess.board

import cz.mkuticka.devel.shogi.ui.chess.board.decoration.DecoratedSquares
import cz.mkuticka.devel.shogi.ui.chess.pieces.Pieces
import cz.mkuticka.devel.shogi.ui.chess.square.DefaultSquareRenderer

object DefaultBoardRenderer : BoardRenderer {

    override val decorations: List<BoardDecoration> =
        listOf(
            DecoratedSquares(DefaultSquareRenderer.decorations),
            Pieces
        )
}
