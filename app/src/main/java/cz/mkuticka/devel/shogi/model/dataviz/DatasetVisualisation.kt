package cz.mkuticka.devel.shogi.model.dataviz

import android.os.Parcelable
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.game.state.GameSnapshotState

interface DatasetVisualisation : Parcelable {

    val name: Int

    val minValue: Int

    val maxValue: Int

    fun dataPointAt(position: Position, state: GameSnapshotState, cache: MutableMap<Any, Any>): Datapoint?
}

