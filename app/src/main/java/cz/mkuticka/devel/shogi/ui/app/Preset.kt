package cz.mkuticka.devel.shogi.ui.app

import androidx.compose.runtime.Composable
import cz.mkuticka.devel.shogi.model.game.preset.Preset
import cz.mkuticka.devel.shogi.model.game.state.GamePlayState

@Composable
fun Preset(preset: Preset) {
    Game(
        state = GamePlayState(),
        preset = preset
    )
}
