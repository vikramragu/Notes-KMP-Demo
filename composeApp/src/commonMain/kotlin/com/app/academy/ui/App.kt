package com.app.academy.ui

import androidx.compose.runtime.Composable
import com.app.academy.di.AppModule
import com.app.academy.theme.NotesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    module: AppModule
) {
    NotesAppTheme {
        NotesAppNavigation(
            module = module
        )
    }
}
