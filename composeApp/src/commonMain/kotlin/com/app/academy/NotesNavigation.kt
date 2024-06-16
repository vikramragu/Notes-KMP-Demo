package com.app.academy

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition

@Composable
fun NotesAppNavigation() {
    Navigator(screen = HomeScreen) {
        SlideTransition(navigator = it)
    }
}
