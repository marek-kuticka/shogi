package com.github.zsoltk.chesso.model.dataviz.impl

import com.github.zsoltk.chesso.R
import com.github.zsoltk.chesso.model.board.Position
import com.github.zsoltk.chesso.model.board.Position.*
import com.github.zsoltk.chesso.model.dataviz.Datapoint
import com.github.zsoltk.chesso.model.dataviz.DatasetVisualisation
import com.github.zsoltk.chesso.model.game.state.GameSnapshotState
import com.github.zsoltk.chesso.ui.base.amaranth_red
import com.github.zsoltk.chesso.ui.base.silver_sand
import kotlinx.parcelize.Parcelize

/**
 * Based on the post of /u/atlas_scrubbed in /r/chess on Reddit:
 *
 * https://www.reddit.com/r/chess/comments/kp7qwe/i_looked_at_a_million_games_played_on_lichess_and
 */
@Parcelize
object CheckmateCount : DatasetVisualisation {

    override val name = R.string.viz_checkmate_count

    override val minValue: Int = 465

    override val maxValue: Int = 26745

    private val total: Int = Position.values().sumOf { valueAt(it) }

    override fun dataPointAt(
        position: Position,
        state: GameSnapshotState,
        cache: MutableMap<Any, Any>
    ): Datapoint {
        val value = valueAt(position)
        return Datapoint(
            value = value,
            label = "%.2f%%".format(100f * value / total),
            colorScale = silver_sand to amaranth_red,
        )
    }

    private fun valueAt(position: Position): Int =
        0
}

