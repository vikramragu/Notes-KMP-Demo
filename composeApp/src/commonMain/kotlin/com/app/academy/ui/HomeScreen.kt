package com.app.academy.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.academy.model.dummyNotes
import cafe.adriel.voyager.core.screen.Screen
import com.app.academy.components.NoteItems

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        LazyColumn(Modifier.fillMaxSize()) {
            NoteItems(dummyNotes, onClick = {}) {
                //onDismissed
            }
        }
    }
}
