package com.dosei.music.scoreconverter.player

import org.billthefarmer.mididriver.GeneralMidiConstants
import org.billthefarmer.mididriver.MidiConstants
import org.billthefarmer.mididriver.MidiDriver

class AudioPlayer(private val driver: MidiDriver) {

    fun start() {
        driver.start()
        val event = byteArrayOf(MidiConstants.PROGRAM_CHANGE, GeneralMidiConstants.ACOUSTIC_GUITAR_NYLON)
        driver.write(event)
    }

    fun playNote() {
        val event = ByteArray(3)
        event[0] = MidiConstants.NOTE_ON
        event[1] = 48
        event[2] = 63
        driver.write(event)
    }

    fun stop() = driver.stop()
}