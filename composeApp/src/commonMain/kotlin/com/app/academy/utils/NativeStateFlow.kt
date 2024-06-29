package com.app.academy.utils

import kotlinx.coroutines.flow.StateFlow

/**
 * A cross platform implementation of [StateFlow].
 */
expect class NativeStateFlow<T>(value: StateFlow<T>) : StateFlow<T>

/**
 * Used to convert a [StateFlow] to a [NativeStateFlow]
 */
fun <T> StateFlow<T>.asNativeStateFlow() = NativeStateFlow(this)
