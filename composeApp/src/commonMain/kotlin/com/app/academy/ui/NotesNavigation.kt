package com.app.academy.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.app.academy.di.AppModule

@Composable
fun NotesAppNavigation(
    module: AppModule
) {
    Navigator(screen = HomeScreen) {
        SlideTransition(navigator = it)
    }
}
