package cz.mkuticka.devel.shogi.model.game.converter

import cz.mkuticka.devel.shogi.model.game.state.GameState

interface Converter {

    fun preValidate(text: String): Boolean

    fun import(text: String): ImportResult

    fun export(gameState: GameState): String
}

