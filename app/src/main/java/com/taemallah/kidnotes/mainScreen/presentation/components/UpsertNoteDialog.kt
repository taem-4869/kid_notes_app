package com.taemallah.kidnotes.mainScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.taemallah.kidnotes.R
import com.taemallah.kidnotes.constants.Const
import com.taemallah.kidnotes.mainScreen.presentation.NoteEvent
import com.taemallah.kidnotes.mainScreen.presentation.NoteState

@Composable
fun UpsertNoteDialog(modifier: Modifier = Modifier, state: NoteState, onEvent: (NoteEvent)->Unit) {
    Dialog(
        onDismissRequest = { onEvent(NoteEvent.EndUpsertOperation(true)) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(5))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Const.MAIN_SCREEN_SPACED_BY)
        ) {
            item {
                Text(
                    text = if (state.focussedNote?.id==null) stringResource(id = R.string.new_note) else stringResource(R.string.update_note),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            item{

                OutlinedTextField(
                    value = state.focussedNote?.body?:"",
                    onValueChange = {onEvent(NoteEvent.SetUpsertedNoteBody(it))},
                    shape = RoundedCornerShape(30.dp),
                    label = { Text(text = stringResource(id = R.string.write_something))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                )
            }
            item{

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Const.MAIN_SCREEN_SPACED_BY)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(.9f),
                        onClick = { onEvent(NoteEvent.EndUpsertOperation(false)) }
                    ) {
                        Text(text = stringResource(R.string.save))
                    }
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(.9f),
                        onClick = { onEvent(NoteEvent.EndUpsertOperation(true)) }
                    ) {
                        Text(text = stringResource(R.string.cancel))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UpsertNoteDialogPreview() {
    UpsertNoteDialog(state = NoteState()) {

    }
}

