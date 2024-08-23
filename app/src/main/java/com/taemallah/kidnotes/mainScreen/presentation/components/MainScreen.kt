package com.taemallah.kidnotes.mainScreen.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taemallah.kidnotes.R
import com.taemallah.kidnotes.constants.Const
import com.taemallah.kidnotes.mainScreen.domain.modules.Note
import com.taemallah.kidnotes.mainScreen.presentation.NoteEvent
import com.taemallah.kidnotes.mainScreen.presentation.NoteState

@Composable
fun MainScreen(modifier: Modifier = Modifier, state: NoteState, onEvent: (NoteEvent)->Unit) {

    val gridState = rememberLazyGridState()
    var gridNotesLastHeadNoteIndex = 0
    val isVisibleFab by remember {
        derivedStateOf {
            if(gridState.firstVisibleItemIndex>gridNotesLastHeadNoteIndex){
                gridNotesLastHeadNoteIndex = gridState.firstVisibleItemIndex
                return@derivedStateOf false
            }else{
                gridNotesLastHeadNoteIndex = gridState.firstVisibleItemIndex
                return@derivedStateOf true
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            AnimatedVisibility(visible = isVisibleFab) {
                FloatingActionButton(onClick = { onEvent(NoteEvent.LaunchUpsertOperation(Note(body = ""))) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.new_note))
                }
            }
        }
    ) {padding ->
        AnimatedVisibility(visible = state.isDetailsDialogDisplayed) {
            NoteDetailsDialog(state = state, onEvent = onEvent)
        }
        AnimatedVisibility(visible = state.isUpsertingNote) {
            UpsertNoteDialog(state = state, onEvent = onEvent)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Const.MAIN_SCREEN_SPACED_BY)
        ) {
            val focusManager = LocalFocusManager.current
            OutlinedTextField(
                value = state.query,
                onValueChange = { onEvent(NoteEvent.SetQuery(it)) },
                shape = RoundedCornerShape(30),
                textStyle = MaterialTheme.typography.titleSmall,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                    }
                ),
                leadingIcon = { Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search),
                    modifier = Modifier.clickable { focusManager.clearFocus() }
                )},
                label = { Text(text = stringResource(id = R.string.search_note))},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.clear),
                    modifier = Modifier.clickable {
                        if (state.query.isBlank()){
                            focusManager.clearFocus()
                        }else{
                            onEvent(NoteEvent.SetQuery(""))
                        }
                    }
                )}
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = gridState,
                horizontalArrangement = Arrangement.spacedBy(Const.MAIN_SCREEN_SPACED_BY),
                verticalArrangement = Arrangement.spacedBy(Const.MAIN_SCREEN_SPACED_BY),
                modifier = Modifier.animateContentSize()
            ) {
                items(state.notes){
                    NoteItem(note = it, onEvent = onEvent)
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(state = NoteState(), onEvent = {})
}