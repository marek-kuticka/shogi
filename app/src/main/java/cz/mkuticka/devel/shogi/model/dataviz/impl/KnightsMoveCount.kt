package cz.mkuticka.devel.shogi.model.dataviz.impl

import androidx.compose.ui.graphics.Color
import cz.mkuticka.devel.shogi.R
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.dataviz.Datapoint
import cz.mkuticka.devel.shogi.model.dataviz.DatasetVisualisation
import cz.mkuticka.devel.shogi.model.game.state.GameSnapshotState
import kotlinx.parcelize.Parcelize

/**
 * Based on the post of /u/IconicIsotope in /r/chess on Reddit:
 *
 * https://www.reddit.com/r/chess/comments/nij28s/knight_moves_a_simple_table_i_made_showing_the
 */
@Parcelize
object KnightsMoveCount : DatasetVisualisation {

    override val name = R.string.viz_knight_move_count

    override val minValue: Int = 2

    override val maxValue: Int = 8

    override fun dataPointAt(
        position: Position,
        state: GameSnapshotState,
        cache: MutableMap<Any, Any>
    ): Datapoint {
        val value = valueAt(position)
        return Datapoint(
            value = value,
            label = value.toString(),
            colorScale = Color.DarkGray to Color.Green
        )
    }

    private fun valueAt(position: Position): Int =
        0
}
