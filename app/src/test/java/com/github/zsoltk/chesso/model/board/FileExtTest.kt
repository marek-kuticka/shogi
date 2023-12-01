package com.github.zsoltk.chesso.model.board

import cz.mkuticka.devel.shogi.model.board.File.h
import cz.mkuticka.devel.shogi.model.board.Rank.r4
import cz.mkuticka.devel.shogi.model.board.Position.h4
import cz.mkuticka.devel.shogi.model.board.get
import org.junit.Assert.assertEquals
import org.junit.Test

class FileExtTest {

    @Test
    fun operator_overloading_int_results_in_correct_algebraic_notation() {
        assertEquals(h4, h[4])
    }

    @Test
    fun operator_overloading_rank_results_in_correct_algebraic_notation() {
        assertEquals(h4, h[r4])
    }
}
