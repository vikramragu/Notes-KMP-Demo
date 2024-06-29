package app.academy.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.academy.components.NoteItems
import app.academy.model.dummyNotes
import cafe.adriel.voyager.core.screen.Screen

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        LazyColumn(Modifier.fillMaxSize()) {
            NoteItems(emptyList(), onClick = {}) {
                //onDismissed
            }
        }
    }
}
