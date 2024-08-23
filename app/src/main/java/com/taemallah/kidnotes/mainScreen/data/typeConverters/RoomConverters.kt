package com.taemallah.kidnotes.mainScreen.data.typeConverters

import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class RoomConverters {

    @TypeConverter
    fun dateToText(date: ZonedDateTime): String {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mma"))
    }

    @TypeConverter
    fun textTooDate(date: String): ZonedDateTime {
        return ZonedDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mma"))
    }
}