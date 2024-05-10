package com.wazowski.forviachallenge.common

fun Int.formatNumber(): String {
    return when {
        this >= 1000000 -> "+${this / 1000000}M"
        this >= 10000 -> "+${this / 1000}k"
        this >= 1000 -> "+${this / 1000},${this % 1000}"
        else -> this.toString()
    }
}