package cz.mkuticka.devel.shogi.ui.app

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import cz.mkuticka.devel.shogi.ui.base.alice_blue

@Composable
fun LoadingSpinner() {
    MaterialTheme {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            CircularProgressIndicator(
                color = alice_blue
            )
        }
    }
}
