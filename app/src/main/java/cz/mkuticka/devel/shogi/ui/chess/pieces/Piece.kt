package cz.mkuticka.devel.shogi.ui.chess.pieces

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import cz.mkuticka.devel.shogi.model.piece.Piece
import cz.mkuticka.devel.shogi.model.piece.Set

@Composable
fun Piece(
    piece: Piece,
    squareSize: Dp,
    modifier: Modifier = Modifier
) {
    key(piece) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.size(squareSize, squareSize)
        ) {
            piece.asset?.let {
                Icon(
                    painter = painterResource(id = it),
                    tint = Color.Unspecified,
                    contentDescription = "${piece.set} ${piece.javaClass.simpleName}"
                )
            } ?: run {
                Text(
                    text = piece.symbol,
                    color = Color.Black,
                    fontSize = with(LocalDensity.current) {
                        (squareSize / 5 * 4).toSp()
                    },
                    modifier = Modifier.rotate(degrees =  if (piece.set == Set.WHITE) 0f else 180f)

                )
            }
        }
    }
}
