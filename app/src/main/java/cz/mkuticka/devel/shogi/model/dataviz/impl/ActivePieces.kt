package cz.mkuticka.devel.shogi.model.dataviz.impl

import androidx.compose.ui.graphics.Color
//import com.github.zsoltk.chesso.R
import cz.mkuticka.devel.shogi.R
import cz.mkuticka.devel.shogi.model.board.Position
import cz.mkuticka.devel.shogi.model.dataviz.Datapoint
import cz.mkuticka.devel.shogi.model.dataviz.DatasetVisualisation
import cz.mkuticka.devel.shogi.model.game.state.GameSnapshotState
import kotlinx.parcelize.Parcelize


/**
 * Calculates how many legal moves a piece can take based on the current game state.
 * Shows a proportionally stronger colour the larger this humber is.
 */
@Parcelize
object ActivePieces : DatasetVisualisation {

    override val name = R.string.viz_active_pieces

    override val minValue: Int = 2

    override val maxValue: Int = 10

    override fun dataPointAt(
        position: Position,
        state: GameSnapshotState,
        cache: MutableMap<Any, Any>
    ): Datapoint? =
        valueAt(position, state)?.let { value ->
            Datapoint(
                value = value,
                label = null,
                colorScale = Color.Green.copy(alpha = 0.025f) to Color.Green.copy(alpha = 0.85f)
            )
        }

    private fun valueAt(position: Position, state: GameSnapshotState): Int? =
        when {
            state.board[position].isEmpty -> null
            else -> state.legalMovesFrom(position).size
        }
}
