package cz.mkuticka.devel.shogi.model.move

import cz.mkuticka.devel.shogi.model.board.Position

data class MoveIntention(
    val from: Position,
    val to: Position
)
