package com.example.tryengapp.ui.extensions


import android.app.Activity
import android.content.Context

import android.util.DisplayMetrics
import java.util.*

fun Activity.getDisplayWidth(): Int {
    val metrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

fun Activity.getDisplayHeight(): Int {
    val metrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}

fun Activity.getDisplayDensity(): Float {
    val metrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.density
}

fun Activity.getDisplayDpi(): Float {
    return this.getDisplayDensity() * 160f
}

fun Context.pxToDp(px: Float): Float {
    return px / this.resources.displayMetrics.density
}

fun Int.getCorrectTime(): String {
    return if (this < 10) {
        "0$this"
    } else {
        "$this"
    }
}

fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean?.orTrue(): Boolean = this ?: true

fun TextToSpeech.speak(word: String) {
    this.speak(word, TextToSpeech.QUEUE_FLUSH, null, UUID.randomUUID().toString())
}