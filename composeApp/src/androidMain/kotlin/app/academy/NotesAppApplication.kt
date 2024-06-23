package app.academy

import android.app.Application
import app.academy.di.AppModule

class NotesAppApplication : Application() {
    val appModule by lazy { AppModule(this) }

    override fun onCreate() {
        super.onCreate()
    }
}
