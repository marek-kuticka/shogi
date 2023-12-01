package cz.mkuticka.devel.shogi.model.game.controller

import cz.mkuticka.devel.shogi.model.game.Resolution
import cz.mkuticka.devel.shogi.model.game.state.GameMetaInfo
import cz.mkuticka.devel.shogi.model.piece.Set

fun GameMetaInfo.withResolution(resolution: Resolution, lastMoveBy: Set): GameMetaInfo =
    when (resolution) {
        cz.mkuticka.devel.shogi.model.game.Resolution.IN_PROGRESS -> this
        cz.mkuticka.devel.shogi.model.game.Resolution.CHECKMATE -> {
            val result = if (lastMoveBy == cz.mkuticka.devel.shogi.model.piece.Set.WHITE) "1-0" else "0-1"
            val winner = if (lastMoveBy == cz.mkuticka.devel.shogi.model.piece.Set.WHITE) white else black
            copy(
                tags = tags
                    .plus(GameMetaInfo.KEY_RESULT to result)
                    .plus(GameMetaInfo.KEY_TERMINATION to "$winner won by checkmate")
            )
        }
        cz.mkuticka.devel.shogi.model.game.Resolution.STALEMATE -> {
            copy(
                tags = tags
                    .plus(GameMetaInfo.KEY_RESULT to "½ - ½")
                    .plus(GameMetaInfo.KEY_TERMINATION to "Stalemate")
            )
        }
        cz.mkuticka.devel.shogi.model.game.Resolution.DRAW_BY_REPETITION -> {
            copy(
                tags = tags
                    .plus(GameMetaInfo.KEY_RESULT to "½ - ½")
                    .plus(GameMetaInfo.KEY_TERMINATION to "Draw by repetition")
            )
        }
        cz.mkuticka.devel.shogi.model.game.Resolution.INSUFFICIENT_MATERIAL -> {
            copy(
                tags = tags
                    .plus(GameMetaInfo.KEY_RESULT to "½ - ½")
                    .plus(GameMetaInfo.KEY_TERMINATION to "Draw by insufficient material")
            )
        }
    }
