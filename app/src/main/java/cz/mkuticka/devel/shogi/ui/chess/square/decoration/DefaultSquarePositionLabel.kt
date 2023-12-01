package cz.mkuticka.devel.shogi.ui.chess.square.decoration

import cz.mkuticka.devel.shogi.model.board.Coordinate

object DefaultSquarePositionLabel : SquarePositionLabelSplit(
    displayFile = { coordinate -> coordinate.y == Coordinate.max.y },
    displayRank = { coordinate -> coordinate.x == Coordinate.min.x }
)
