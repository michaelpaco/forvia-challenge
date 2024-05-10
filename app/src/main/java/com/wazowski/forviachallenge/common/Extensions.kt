package com.wazowski.forviachallenge.common

import kotlin.math.*

fun Int.formatNumber(): String {
    return when {
        this >= 1000000 -> "+${this / 1000000}M"
        this >= 10000 -> "+${this / 1000}k"
        this >= 1000 -> "+${this / 1000},${this % 1000}"
        else -> this.toString()
    }
}

fun Long.formatFileSize(): String {
    if (this <= 0) return "0 B"
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (log10(this.toDouble()) / log10(1024.0)).toInt()
    return String.format("%.1f %s", this / 1024.0.pow(digitGroups.toDouble()), units[digitGroups])
}