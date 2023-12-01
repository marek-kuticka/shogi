package com.github.zsoltk.chesso.model.piece

enum class Set {
    WHITE, BLACK;

    fun opposite() =
        when (this) {
            WHITE -> BLACK
            BLACK -> WHITE
        }

    fun direction() =
        when(this) {
            WHITE -> 1
            BLACK -> -1
        }
}
