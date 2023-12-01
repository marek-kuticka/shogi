package cz.mkuticka.devel.shogi.model.game.preset

import cz.mkuticka.devel.shogi.model.game.controller.GameController

interface Preset {

    fun apply(gameController: GameController)
}
