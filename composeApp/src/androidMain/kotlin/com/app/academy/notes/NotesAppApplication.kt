package com.app.academy.notes

import android.app.Application
import com.app.academy.di.AppModule

class NotesAppApplication : Application() {

    val appModule by lazy { AppModule(this) }

    override fun onCreate() {
        super.onCreate()
    }
}
