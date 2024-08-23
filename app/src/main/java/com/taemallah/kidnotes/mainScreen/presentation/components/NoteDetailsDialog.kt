package com.taemallah.kidnotes.mainScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.taemallah.kidnotes.R
import com.taemallah.kidnotes.constants.Const
import com.taemallah.kidnotes.mainScreen.presentation.NoteEvent
import com.taemallah.kidnotes.mainScreen.presentation.NoteState

@Composable
fun NoteDetailsDialog(modifier: Modifier = Modifier, state: NoteState, onEvent: (NoteEvent)->Unit) {
    Dialog(
        onDismissRequest = { onEvent(NoteEvent.HideDetailsDialog) }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(5))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Const.MAIN_SCREEN_SPACED_BY)
        ) {
            item {
                Text(
                    text = stringResource(R.string.note_details),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
            item{
                Text(
                    text = state.focussedNote?.body?:"",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp))
                Text(
                    text = state.focussedNote?.createdDate?:"",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp))
            }
            item{
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Const.MAIN_SCREEN_SPACED_BY)
                ) {

                    Button(
                        modifier = Modifier.fillMaxWidth(.9f),
                        onClick = { onEvent(NoteEvent.HideDetailsDialog) }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.cancel))
                        Text(text = stringResource(R.string.go_back))
                    }
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(.9f),
                        onClick = {
                            if (state.focussedNote!=null)
                                onEvent(NoteEvent.LaunchUpsertOperation(note = state.focussedNote!!))
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = stringResource(R.string.edit))
                        Text(text = stringResource(R.string.edit))
                    }
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(.9f),
                        onClick = {
                            if (state.focussedNote!=null)
                                onEvent(NoteEvent.DeleteNote(note = state.focussedNote!!))
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(R.string.delete))
                        Text(text = stringResource(R.string.delete))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UpsertNoteDialogPreview() {
    NoteDetailsDialog(state = NoteState()) {

    }
}
