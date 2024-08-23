package com.taemallah.kidnotes.mainScreen.presentation.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun ZonedDateTime.getFormattedDate(): String{
    return this.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mma"))
}