package com.app.academy.di

import android.content.Context
import com.app.academy.data.DefaultNotesRepository
import com.app.academy.data.NotesRepository
import com.app.academy.data.local.NotesDatabaseDriverFactory
import com.app.academy.data.local.datasource.DefaultLocalNotesDataSource
import com.example.notes.database.NotesDatabase
import kotlinx.coroutines.Dispatchers


/**
 * Written by Vikram Ragu on 28/06/24.
 */
actual class AppModule(context: Context) {

    private val database by lazy {
        val driver = NotesDatabaseDriverFactory(context).createDriver()
        NotesDatabase(driver)
    }

    /**
     * Provides an implementation of [NotesRepository]
     */
    actual fun provideNotesRepository(): NotesRepository {
        val localNotesDataSource = DefaultLocalNotesDataSource(
            database = database,
            ioDispatcher = Dispatchers.IO
        )
        return DefaultNotesRepository(localNotesDataSource = localNotesDataSource)
    }


    /**
     * Used to provide an instance of [DispatchersProvider]
     */
    actual fun providersDispatchersProvider(): DispatchersProviderList {
        return DispatchersProviderList(
            ioDispatcher = Dispatchers.IO,
            defaultDispatcher = Dispatchers.Default,
            mainDispatcher = Dispatchers.Main
        )
    }

}