package cz.mkuticka.devel.shogi.model.dataviz.impl

import cz.mkuticka.devel.shogi.R
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.dataviz.Datapoint
import cz.mkuticka.devel.shogi.model.dataviz.DatasetVisualisation
import cz.mkuticka.devel.shogi.model.game.state.GameSnapshotState
import kotlinx.parcelize.Parcelize

@Parcelize
object None : DatasetVisualisation {

    override val name = R.string.viz_none

    override val minValue: Int = Int.MIN_VALUE

    override val maxValue: Int = Int.MAX_VALUE

    override fun dataPointAt(
        position: Position,
        state: GameSnapshotState,
        cache: MutableMap<Any, Any>
    ): Datapoint? =
        null
}
