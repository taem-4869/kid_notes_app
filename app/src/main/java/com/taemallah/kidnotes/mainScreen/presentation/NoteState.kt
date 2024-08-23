package com.taemallah.kidnotes.mainScreen.presentation

import com.taemallah.kidnotes.mainScreen.domain.modules.Note

data class NoteState(
    val notes: List<Note> = emptyList(),
    val query: String = "",
    val focussedNote: Note? = null,
    val isUpsertingNote: Boolean = false,
    val isDetailsDialogDisplayed: Boolean = false,
)
