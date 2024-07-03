package app.academy.data.local.datasource

import app.academy.notes.database.SavedNoteEntity
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.app.academy.notes.database.NotesDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DefaultLocalNotesDataSource(
    database: NotesDatabase,
    private val ioDispatcher: CoroutineDispatcher
) : LocalNotesDataSource {
    private val queries = database.notesDatabaseQueries

    override val savedNotesStream: Flow<List<SavedNoteEntity>> =
        queries.getAllSavedNotes()
            .asFlow()
            .mapToList(ioDispatcher)

    override suspend fun saveNote(noteEntity: SavedNoteEntity) = withContext(ioDispatcher) {

        co.touchlab.kermit.Logger.d("Save Note Verify = "){
            noteEntity.toString()
        }

        queries.insertNote(
            id = noteEntity.id,
            title = noteEntity.title,
            content = noteEntity.content,
            createdAtTimestamp = noteEntity.createdAtTimestamp,
            isDeleted = noteEntity.isDeleted
        )
    }

    override suspend fun permanentlyDeleteNoteWithId(id: String) = withContext(ioDispatcher) {
        queries.deleteNote(id)
    }

    override suspend fun markNoteAsDeleted(id: String) = withContext(ioDispatcher) {
        queries.markNoteAsDeleted(id)
    }

    override suspend fun markNoteAsNotDeleted(id: String) = withContext(ioDispatcher) {
        queries.markNoteAsNotDeleted(id)
    }

    override suspend fun deleteAllNotesMarkedAsDeleted() = withContext(ioDispatcher) {
        queries.deleteAllNotesMarkedAsDeleted()
    }
}
