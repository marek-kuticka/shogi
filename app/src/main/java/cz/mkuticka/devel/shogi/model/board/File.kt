package cz.mkuticka.devel.shogi.model.board

enum class File {
    a, b, c, d, e, f, g, h, i
}

operator fun File.get(rank: Int): Position =
    Position.values()[this.ordinal * 9 + (rank - 1)]


operator fun File.get(rank: Rank): Position =
    Position.values()[this.ordinal * 9 + rank.ordinal]
