package com.dosei.music.scoreconverter.player

import org.billthefarmer.mididriver.MidiDriver

object PlayerDependencyInjection {

    fun createPlayer(): AudioPlayer = AudioPlayer(MidiDriver())
}