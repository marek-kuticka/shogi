package cz.mkuticka.devel.shogi.model.dataviz

import androidx.compose.ui.graphics.Color

data class Datapoint(
    val value: Int?,
    val label: String?,
    val colorScale: Pair<Color, Color>,
)
