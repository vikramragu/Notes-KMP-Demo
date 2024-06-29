package com.app.academy.data

import com.app.academy.data.local.datasource.LocalNotesDataSource
import com.app.academy.model.Note
import com.app.academy.model.toNote
import com.app.academy.model.toSavedNoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultNotesRepository(
    private val localNotesDataSource: LocalNotesDataSource
) : NotesRepository {

    override val savedNotesStream: Flow<List<Note>> =
        localNotesDataSource.savedNotesStream.map { savedNoteEntities ->
            savedNoteEntities.map { savedNoteEntity -> savedNoteEntity.toNote() }
        }

    override suspend fun saveNote(note: Note) {
        localNotesDataSource.saveNote(note.toSavedNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        localNotesDataSource.markNoteAsDeleted(note.id)
    }

    override suspend fun deleteAllNotesMarkedAsDeleted() {
        localNotesDataSource.deleteAllNotesMarkedAsDeleted()
    }
}
