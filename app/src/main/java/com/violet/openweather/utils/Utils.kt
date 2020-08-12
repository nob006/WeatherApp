package com.violet.openweather.utils

import java.time.Instant
import java.time.ZoneId

class Utils {
    fun getDateTime(s: Long): String {
        val dt = Instant.ofEpochSecond(s)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        return "${dt.dayOfMonth}/${dt.monthValue}/${dt.year} ${dt.hour}:${dt.minute}:${dt.second}"
    }
}