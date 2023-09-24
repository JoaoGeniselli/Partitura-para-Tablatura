package com.dosei.music.scoreconverter.feature.chords.dictionary.data

import com.dosei.music.arpeggio.Barre
import com.dosei.music.arpeggio.Finger
import com.dosei.music.arpeggio.OpenString
import com.dosei.music.arpeggio.Position
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ChordParserTest {

    private lateinit var parser: ChordParser

    @Before
    fun before() {
        parser = ChordParser()
    }

    @Test
    fun `parse valid chord with barre`() {
        val pattern = "C#m,5.4.i,4.6.r,3.6.p,2.5.m,1.4.i,6.0"

        with(parser.parse(pattern)) {
            assertEquals("C#m", name.toString())
            assertEquals(5, components.size)

            assert(Barre(4, 1..5, Finger.Index) in components)
            assert(Position(6, 4, Finger.Ring) in components)
            assert(Position(6, 3, Finger.Pinky) in components)
            assert(Position(5, 2, Finger.Middle) in components)
            assert(OpenString(6) in components)
        }
    }

    @Test
    fun `parse valid chord with empty finger slots`() {
        val pattern = "Am,6.5.i,5.7.r,4.7.p,3.5.i,2.5.i,1.5.i"

        with(parser.parse(pattern)) {
            assertEquals("Am", name.toString())
            assertEquals(3, components.size)
            assert(Barre(5, 1..6, Finger.Index) in components)
            assert(Position(7, 5, Finger.Ring) in components)
            assert(Position(7, 4, Finger.Pinky) in components)
        }
    }

    @Test
    fun `parse valid chord with multiple open chords`() {
        val pattern = "C,5.3.r,4.2.m,3.0,2.1.i,1.0"

        with(parser.parse(pattern)) {
            assertEquals("C", name.toString())
            assertEquals(5, components.size)
            assert(Position(3, 5, Finger.Ring) in components)
            assert(Position(2, 4, Finger.Middle) in components)
            assert(Position(1, 2, Finger.Index) in components)
            assert(OpenString(3) in components)
            assert(OpenString(1) in components)
        }
    }

    @Test
    fun `parse chord with all strings`() {
        val pattern = "Em,6.0,5.2.m,4.2.r,3.0,2.0,1.0"

        with(parser.parse(pattern)) {
            assertEquals("Em", name.toString())
            assertEquals(6, components.size)
            assert(OpenString(6) in components)
            assert(OpenString(3) in components)
            assert(OpenString(2) in components)
            assert(OpenString(1) in components)
            assert(Position(2, 4, Finger.Ring) in components)
            assert(Position(2, 5, Finger.Middle) in components)
        }
    }

}