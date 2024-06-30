package app.academy.domain

import app.academy.data.NotesRepository
import app.academy.di.AppModule
import app.academy.model.Note
import app.academy.utils.asNativeStateFlow
import cafe.adriel.voyager.core.model.ScreenModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    coroutineScope: CoroutineScope?,
    appModule: AppModule
) : ScreenModel {

    private val viewModelScope = coroutineScope ?: MainScope()
    private val notesRepository: NotesRepository = appModule.provideNotesRepository()
    private val defaultDispatcher: CoroutineDispatcher =
        appModule.provideDispatchersProvider().defaultDispatcher

    /**
     * The current [HomeScreenUiState]
     */
    private val _uiState = MutableStateFlow(HomeScreenUiState(isLoadingSavedNotes = true))
    val uiState = _uiState.asNativeStateFlow()

    private val currentSearchText = MutableStateFlow("")
    private var recentlyDeletedNote: Note? = null

    init {
        notesRepository.savedNotesStream.onEach { savedNotesList ->
            Logger.d("NotesCRUD") { "Update: $savedNotesList" }
            _uiState.update { it.copy(isLoadingSavedNotes = false, savedNotes = savedNotesList) }
        }.launchIn(viewModelScope)

        combine(
            uiState,
            currentSearchText.debounce(200)
        ) { updatedUiState, searchText ->
            val savedNotes = updatedUiState.savedNotes
            // filtering notes with titles containing the search text
            _uiState.update { it.copy(isLoadingSearchResults = true) }
            val notesWithTitleContainingSearchText = savedNotes.filter {
                it.title.contains(searchText, ignoreCase = true)
            }
            _uiState.update { it.copy(searchResults = notesWithTitleContainingSearchText) }

            // filtering notes with the content containing the search text.
            // Since this is slower than the previous filtering operation above,
            // update ui state independently
            val notesWithContentContainingSearchText = savedNotes.filter {
                it.content.contains(searchText, ignoreCase = true)
            }
            _uiState.update {
                it.copy(searchResults = (it.searchResults + notesWithContentContainingSearchText).distinct())
            }
            _uiState.update {
                it.copy(isLoadingSearchResults = false)
            }

        }.flowOn(defaultDispatcher).launchIn(viewModelScope)
    }

    /**
     * Searches for notes with titles or contents containing the given [searchText].
     */
    fun search(searchText: String) {
        currentSearchText.value = searchText
    }

    fun fetchNotes() {
        notesRepository.savedNotesStream.onEach { savedNotesList ->
            Logger.d("NotesCRUD") { "Update: $savedNotesList" }
            _uiState.update { it.copy(isLoadingSavedNotes = false, savedNotes = savedNotesList) }
        }.launchIn(viewModelScope)
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesRepository.deleteNote(note)
            recentlyDeletedNote = note
        }
    }

    fun restoreRecentlyDeletedNote() {
        viewModelScope.launch { recentlyDeletedNote?.let { notesRepository.saveNote(it) } }
    }

}
