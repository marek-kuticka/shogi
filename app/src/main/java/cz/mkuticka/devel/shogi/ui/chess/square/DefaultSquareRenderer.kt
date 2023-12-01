package cz.mkuticka.devel.shogi.ui.chess.square

import cz.mkuticka.devel.shogi.ui.chess.square.decoration.DatasetVisualiser
import cz.mkuticka.devel.shogi.ui.chess.square.decoration.DefaultHighlightSquare
import cz.mkuticka.devel.shogi.ui.chess.square.decoration.DefaultSquareBackground
import cz.mkuticka.devel.shogi.ui.chess.square.decoration.DefaultSquarePositionLabel
import cz.mkuticka.devel.shogi.ui.chess.square.decoration.TargetMarks

object DefaultSquareRenderer : SquareRenderer {

    override val decorations: List<SquareDecoration> =
        listOf(
            DefaultSquareBackground,
            DefaultHighlightSquare,
            DefaultSquarePositionLabel,
            DatasetVisualiser,
            TargetMarks
        )
}
