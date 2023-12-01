package cz.mkuticka.devel.shogi.model.dataviz

import androidx.compose.runtime.compositionLocalOf
import cz.mkuticka.devel.shogi.model.dataviz.impl.ActivePieces
import cz.mkuticka.devel.shogi.model.dataviz.impl.BlackKingsEscape
import cz.mkuticka.devel.shogi.model.dataviz.impl.CheckmateCount
import cz.mkuticka.devel.shogi.model.dataviz.impl.Influence
import cz.mkuticka.devel.shogi.model.dataviz.impl.KnightsMoveCount
import cz.mkuticka.devel.shogi.model.dataviz.impl.BlockedPieces
import cz.mkuticka.devel.shogi.model.dataviz.impl.None
import cz.mkuticka.devel.shogi.model.dataviz.impl.WhiteKingsEscape

val datasetVisualisations = listOf(
    None,
    ActivePieces,
    BlockedPieces,
    Influence,
    BlackKingsEscape,
    WhiteKingsEscape,
    KnightsMoveCount,
    CheckmateCount
)

val ActiveDatasetVisualisation = compositionLocalOf<DatasetVisualisation> { None }
