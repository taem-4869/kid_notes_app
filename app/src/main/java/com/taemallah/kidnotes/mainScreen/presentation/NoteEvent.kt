package com.taemallah.kidnotes.mainScreen.presentation

import com.taemallah.kidnotes.mainScreen.domain.modules.Note

sealed class NoteEvent {
    data class DeleteNote(val note: Note): NoteEvent()
    data class SetQuery(val query: String): NoteEvent()
    data class LaunchUpsertOperation(val note: Note): NoteEvent()
    data class EndUpsertOperation(val isCanceled: Boolean): NoteEvent()
    data class SetUpsertedNoteBody(val body: String): NoteEvent()
    data class ShowDetailsDialog(val note: Note): NoteEvent()
    data object HideDetailsDialog: NoteEvent()
}