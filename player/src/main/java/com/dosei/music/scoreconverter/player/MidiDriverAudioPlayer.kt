package com.dosei.music.scoreconverter.player

import org.billthefarmer.mididriver.GeneralMidiConstants
import org.billthefarmer.mididriver.MidiConstants
import org.billthefarmer.mididriver.MidiDriver

internal class MidiDriverAudioPlayer(private val driver: MidiDriver) : AudioPlayer {

    override fun start() {
        driver.start()
        setInstrumentToGuitar()
    }

    private fun setInstrumentToGuitar() {
        val event =
            byteArrayOf(MidiConstants.PROGRAM_CHANGE, GeneralMidiConstants.ACOUSTIC_GUITAR_NYLON)
        driver.write(event)
    }

    override fun playNote(note: Int, duration: Int) {
        val event = ByteArray(3)
        event[POSITION_ACTION] = MidiConstants.NOTE_ON
        event[POSITION_NOTE] = note.toByte()
        event[POSITION_DURATION] = duration.toByte()
        driver.write(event)
    }

    override fun stop() = driver.stop()

    companion object {
        private const val POSITION_ACTION = 0
        private const val POSITION_NOTE = 1
        private const val POSITION_DURATION = 2
        const val DEFAULT_DURATION = 63
    }
}