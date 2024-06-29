package com.app.academy.notes

import com.app.academy.ui.App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = this.application as NotesAppApplication
        val appModule = application.appModule
        setContent {
            App(module = appModule)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
}
