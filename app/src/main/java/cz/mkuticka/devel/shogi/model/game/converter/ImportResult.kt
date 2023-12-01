package cz.mkuticka.devel.shogi.model.game.converter

import cz.mkuticka.devel.shogi.model.game.state.GameState

sealed class ImportResult {
    class ValidationError(val msg: String) : ImportResult()
    class ImportedGame(val gameState: GameState) : ImportResult()
}
