package cz.mkuticka.devel.shogi.model.game.state

import cz.mkuticka.devel.shogi.model.move.AppliedMove

data class GameStateTransition(
    val fromSnapshotState: GameSnapshotState,
    val toSnapshotState: GameSnapshotState,
    val move: AppliedMove
)
