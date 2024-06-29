package com.app.academy.di

import com.app.academy.data.NotesRepository


/**
 * Written by Vikram Ragu on 27/06/24.
 */
expect class AppModule {

    fun provideNotesRepository(): NotesRepository

    fun providersDispatchersProvider() : DispatchersProviderList
}