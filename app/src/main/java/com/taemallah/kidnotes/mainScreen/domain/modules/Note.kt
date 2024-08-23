package com.taemallah.kidnotes.mainScreen.domain.modules

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.taemallah.kidnotes.mainScreen.presentation.utils.getFormattedDate
import java.time.ZonedDateTime

@Entity
data class Note(
    @PrimaryKey
    val id : Int? = null,
    val body : String = "",
    val createdDate : String = ZonedDateTime.now().getFormattedDate()
)