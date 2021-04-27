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

    fun playNote(note: Int, duration: Int = DEFAULT_DURATION) {
        val event = ByteArray(3)
        event[POSITION_ACTION] = MidiConstants.NOTE_ON
        event[POSITION_NOTE] = note.toByte()
        event[POSITION_DURATION] = duration.toByte()
        driver.write(event)
    }

    fun stop() = driver.stop()

    companion object {
        private const val POSITION_ACTION = 0
        private const val POSITION_NOTE = 1
        private const val POSITION_DURATION = 2
        const val DEFAULT_DURATION = 63
    }
}