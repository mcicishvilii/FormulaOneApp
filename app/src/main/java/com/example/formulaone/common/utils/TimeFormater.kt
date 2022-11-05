package com.example.formulaone.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)

interface TimeFormater {
    fun formatCurrentTime(): LocalDate
}

@RequiresApi(Build.VERSION_CODES.O)
class TimeFormaterIMPL : TimeFormater {
    override fun formatCurrentTime(): LocalDate {
        val time = Calendar.getInstance().time
        val formatterCurrentTime = SimpleDateFormat("yyyy-MM-dd")
        val formatterNow = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentTime = formatterCurrentTime.format(time)
        return LocalDate.parse(currentTime, formatterNow)
    }
}




