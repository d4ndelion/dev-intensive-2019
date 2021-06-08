package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: String): Date{
    var time = this.time
    time += when(units){
        "second", "seconds" -> value * SECOND
        "minute", "minutes" -> value * MINUTE
        "hour", "hours" -> value * HOUR
        "day", "days" -> value * DAY
        else -> throw IllegalStateException("Invalid unit.")
    }
    this.time = time
    return this
}

fun Date?.shortFormat(): String? {
    val pattern = if (this.isSameDay(Date()) == true) "HH:mm" else "dd.MM.yy"
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date?.isSameDay(date: Date?): Boolean? {
    val day1 = this?.time?.div(DAY)
    val day2 = date?.time?.div(DAY)
    return day1 == day2
}
