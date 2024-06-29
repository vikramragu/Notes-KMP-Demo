package com.app.academy.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.app.academy.data.local.DatabaseDriverConstants
import com.example.notes.database.NotesDatabase


/**
 * Written by Vikram Ragu on 27/06/24.
 */
actual class NotesDatabaseDriverFactory(private val context : Context) {

    actual fun createDriver() : SqlDriver = AndroidSqliteDriver(
        schema = NotesDatabase.Schema,
        context = context,
        name = DatabaseDriverConstants.DATABASE_NAME
    )

}