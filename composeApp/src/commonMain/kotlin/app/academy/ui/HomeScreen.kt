package app.academy.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.academy.components.AnimatedSearchBar
import app.academy.components.NoteItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object HomeScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var currentSearchQuery by remember { mutableStateOf("") }
        var isSearchBarActive by remember { mutableStateOf(false) }
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                // Do not add status bars padding to AnimatedSearchBar.
                // If the padding is added, then the search bar wouldn't
                // fill the entire screen when it is expanded.
                AnimatedSearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    query = currentSearchQuery,
                    isSearchBarActive = isSearchBarActive,
                    onQueryChange = {
                        currentSearchQuery = it
                        // TODO call search
                    },
                    onBackButtonClick = { isSearchBarActive = false },
                    onActiveChange = { isSearchBarActive = it },
                    onClearSearchQueryButtonClick = {
                        currentSearchQuery = ""
                        // TODO call search
                    },
                    suggestionsForQuery = emptyList(),// TODO fetch via search ui state
                    onNoteDismissed = {
                        //TODO note dismiss
                    },
                    onNoteItemClick = {
                        //TODO on note click
                    }
                )
            }
            NoteItems(emptyList(), onClick = {}) {
                //onDismissed
            }
        }
    }
}
