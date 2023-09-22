package com.dosei.music.scoreconverter.feature.chords.dictionary.data

import com.dosei.music.arpeggio.Barre
import com.dosei.music.arpeggio.Component
import com.dosei.music.arpeggio.Finger
import com.dosei.music.arpeggio.OpenString
import com.dosei.music.arpeggio.Position
import org.junit.Assert.*
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
        val pattern = "C#m,5.4.i,4.6.r,3.6.p,2.5.m,1.4.i"

        with(parser.parse(pattern)) {
            assertEquals("C#m", name.toString())
            assertEquals(5, components.size)
            components[0].assertBarre(7, 1..5, Finger.Index)
            components[1].assertPosition(8, 2, Finger.Middle)
            components[2].assertPosition(9, 4, Finger.Ring)
            components[3].assertPosition(9, 3, Finger.Pinky)
            components[4].assertOpenString(6)
        }
    }

//    @Test
//    fun `parse valid chord with empty finger slots`() {
//        val pattern = "Am,1-6-5,,5-7,4-7"
//
//        with(parser.parse(pattern)) {
//            assertEquals("Am", name.toString())
//            assertEquals(3, components.size)
//            components[0].assertBarre(5, 1..6, Finger.Index)
//            components[1].assertPosition(7, 5, Finger.Ring)
//            components[2].assertPosition(7, 4, Finger.Pinky)
//        }
//    }
//
//    @Test
//    fun `parse valid chord with multiple open chords`() {
//        val pattern = "C,2-1,4-2,5-3,,1,3"
//
//        with(parser.parse(pattern)) {
//            assertEquals("C", name.toString())
//            assertEquals(5, components.size)
//            components[0].assertPosition(1, 2, Finger.Index)
//            components[1].assertPosition(2, 4, Finger.Middle)
//            components[2].assertPosition(3, 5, Finger.Ring)
//            components[3].assertOpenString(1)
//            components[4].assertOpenString(3)
//        }
//    }
//
//    @Test
//    fun `parse chord with all strings`() {
//        val pattern = "Em,,5-2,4-2,,1,2,3,6"
//
//        with(parser.parse(pattern)) {
//            assertEquals("Em", name.toString())
//            assertEquals(6, components.size)
//            components[0].assertPosition(2, 5, Finger.Middle)
//            components[1].assertPosition(2, 4, Finger.Ring)
//            components[2].assertOpenString(1)
//            components[3].assertOpenString(2)
//            components[4].assertOpenString(3)
//            components[5].assertOpenString(6)
//        }
//    }

    private fun Component.assertBarre(fret: Int, strings: IntRange, finger: Finger?) {
        val barre = this as Barre
        assertEquals(fret, barre.fret)
        assertEquals(strings, barre.strings)
        assertEquals(finger, barre.finger)
    }

    private fun Component.assertPosition(fret: Int, string: Int, finger: Finger?) {
        val position = this as Position
        assertEquals(fret, position.fret)
        assertEquals(string, position.string)
        assertEquals(finger, position.finger)
    }

    private fun Component.assertOpenString(string: Int) {
        val position = this as OpenString
        assertEquals(string, position.string)
    }

}