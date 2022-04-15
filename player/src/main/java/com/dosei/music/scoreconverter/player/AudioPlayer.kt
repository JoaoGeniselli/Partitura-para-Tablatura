package com.dosei.music.scoreconverter.player

interface AudioPlayer {
    fun start()
    fun playNote(note: Int, duration: Int = MidiDriverAudioPlayer.DEFAULT_DURATION)
    fun stop()
}