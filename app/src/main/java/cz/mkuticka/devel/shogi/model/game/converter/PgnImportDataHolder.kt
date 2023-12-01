package cz.mkuticka.devel.shogi.model.game.converter

import cz.mkuticka.devel.shogi.model.game.state.GameMetaInfo

data class PgnImportDataHolder(
    val metaInfo: GameMetaInfo,
    val moves: List<String>
)
