package com.taemallah.kidnotes.mainScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taemallah.kidnotes.mainScreen.domain.modules.Note
import com.taemallah.kidnotes.mainScreen.domain.repository.NoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepo: NoteRepo
): ViewModel() {

    private val _query = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _notes = _query
        .flatMapLatest {q->
            if (q.isBlank()){
                noteRepo.getAllNotes()
            }else{
                noteRepo.searchNote(q)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _focussedNote = MutableStateFlow<Note?>(null)

    private val _state = MutableStateFlow(NoteState())

    val state = combine(_query,_notes,_focussedNote,_state){query, notes, focussedNote, state->
        state.copy(
            query = query,
            notes = notes,
            focussedNote = focussedNote
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000), NoteState())

    fun onEvent(event: NoteEvent){
        when(event){
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteRepo.delete(event.note)
                    _state.update { it.copy(isUpsertingNote = false, isDetailsDialogDisplayed = false) }
                }
            }
            is NoteEvent.EndUpsertOperation -> {
                viewModelScope.launch {
                    if (!event.isCanceled && _focussedNote.value!=null){
                        noteRepo.upsert(_focussedNote.value!!)
                    }
                    _focussedNote.update { null }
                    _state.update { it.copy(isUpsertingNote = false)}
                }
            }
            NoteEvent.HideDetailsDialog -> {
                _state.update { it.copy(isDetailsDialogDisplayed = false) }
                _focussedNote.update { null }
            }
            is NoteEvent.LaunchUpsertOperation -> {
                _focussedNote.update { event.note }
                _state.update { it.copy(isUpsertingNote = true, isDetailsDialogDisplayed = false) }
            }
            is NoteEvent.SetQuery -> {
                _query.update { event.query }
            }
            is NoteEvent.SetUpsertedNoteBody -> {
                _focussedNote.update {
                    it?.copy(body = event.body)
                }
            }
            is NoteEvent.ShowDetailsDialog -> {
                _state.update {
                    it.copy(
                        isUpsertingNote = false,
                        isDetailsDialogDisplayed = true
                    )
                }
                _focussedNote.update { event.note }
            }
        }
    }
}