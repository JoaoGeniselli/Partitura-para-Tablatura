package com.dosei.music.scoreconverter.ui.tutorial

import android.app.Activity
import android.view.View
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence

class Tutorial {

    private var onFinishListener: () -> Unit = {}
    private var onCancelListener: () -> Unit = {}
    private val steps = mutableListOf<Step>()

    fun addStep(target: View, description: CharSequence) {
        steps.add(Step(target, description))
    }

    fun onFinish(listener: () -> Unit) {
        onFinishListener = listener
    }

    fun onCancel(listener: () -> Unit) {
        onCancelListener = listener
    }

    fun start(activity: Activity) {
        if (steps.isEmpty()) return
        TapTargetSequence(activity).apply {
            targets(steps.map { it.toTarget() })
            listener(makeListener())
            start()
        }
    }

    private fun Step.toTarget(): TapTarget = TapTarget
        .forView(target, description)
        .tintTarget(false)
        .cancelable(true)

    private fun makeListener() = object : TapTargetSequence.Listener {
        override fun onSequenceCanceled(lastTarget: TapTarget?) = onCancelListener()
        override fun onSequenceFinish() = onFinishListener()
        override fun onSequenceStep(lastTarget: TapTarget?, targetClicked: Boolean) = Unit
    }

    private data class Step(
        val target: View,
        val description: CharSequence
    )

    companion object {

        fun create(def: Tutorial.() -> Unit): Tutorial = Tutorial().apply(def)

        fun start(activity: Activity, def: Tutorial.() -> Unit) = create(def).start(activity)
    }
}

