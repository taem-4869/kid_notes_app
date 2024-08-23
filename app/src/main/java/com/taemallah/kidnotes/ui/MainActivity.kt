package com.taemallah.kidnotes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.taemallah.kidnotes.mainScreen.presentation.NoteViewModel
import com.taemallah.kidnotes.mainScreen.presentation.components.MainScreen
import com.taemallah.kidnotes.ui.theme.KIDNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KIDNotesTheme {
                MainScreen(state = viewModel.state.collectAsState().value, onEvent = viewModel::onEvent)
            }
        }
    }
}