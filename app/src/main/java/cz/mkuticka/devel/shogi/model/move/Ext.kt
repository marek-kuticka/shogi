package cz.mkuticka.devel.shogi.model.move

import cz.mkuticka.devel.shogi.model.board.Position

fun List<BoardMove>.targetPositions(): List<Position> =
    map { it.to }
