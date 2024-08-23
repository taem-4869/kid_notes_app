package com.taemallah.kidnotes.mainScreen.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taemallah.kidnotes.mainScreen.domain.modules.Note
import com.taemallah.kidnotes.mainScreen.presentation.NoteEvent
import com.taemallah.kidnotes.mainScreen.presentation.utils.getFormattedDate

@Composable
fun NoteItem(modifier: Modifier = Modifier, note: Note, onEvent: (NoteEvent)->Unit) {
    Card(
        modifier = modifier
            .clickable { onEvent(NoteEvent.ShowDetailsDialog(note)) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Text(
            text = note.body,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        Text(
            text = note.createdDate,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
    }
}

@Preview
@Composable
private fun NoteItemPreview() {
    NoteItem(note = Note(body = "test the kid notes app")) {

    }
}