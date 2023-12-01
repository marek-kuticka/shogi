package cz.mkuticka.devel.shogi.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import cz.mkuticka.devel.shogi.model.piece.Bishop
import cz.mkuticka.devel.shogi.model.piece.Knight
import cz.mkuticka.devel.shogi.model.piece.Piece
import cz.mkuticka.devel.shogi.model.piece.Queen
import cz.mkuticka.devel.shogi.model.piece.Rook
import cz.mkuticka.devel.shogi.model.piece.Set
import cz.mkuticka.devel.shogi.model.piece.Set.WHITE
import cz.mkuticka.devel.shogi.ui.chess.pieces.Piece

@Composable
fun PromotionDialog(
    set: Set = WHITE,
    onPieceSelected: (Piece) -> Unit,
) {
    MaterialTheme {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            PromotionDialogContent(set) {
                onPieceSelected(it)
            }
        }
    }
}

@Preview
@Composable
private fun PromotionDialogContent(
    set: Set = WHITE,
    onClick: (Piece) -> Unit = {}
) {
    val promotionPieces = listOf(
        Queen(set),
        Rook(set),
        Bishop(set),
        Knight(set)
    )

    Column(
        modifier = Modifier.background(
            color = MaterialTheme.colors.surface,
            shape = MaterialTheme.shapes.medium
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        promotionPieces.forEach { piece ->
            Piece(
                piece = piece,
                squareSize = 48.dp,
                modifier = Modifier.clickable(onClick = { onClick(piece) })
            )
        }
    }
}
