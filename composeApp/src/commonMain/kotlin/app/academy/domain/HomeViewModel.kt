package app.academy.domain

import app.academy.data.NotesRepository
import app.academy.utils.asNativeStateFlow
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel(
    coroutineScope: CoroutineScope?,
) : ScreenModel, KoinComponent {

    private val notesRepository: NotesRepository by inject()
    private val defaultDispatcher: CoroutineDispatcher by inject()
    /**
     * The current [HomeScreenUiState]
     */
    private val _uiState = MutableStateFlow(HomeScreenUiState(isLoadingSavedNotes = true))
    val uiState = _uiState.asNativeStateFlow()

}
