package com.app.academy.utils

import kotlinx.coroutines.flow.StateFlow


/**
 * Written by Vikram Ragu on 28/06/24.
 */
actual class NativeStateFlow<T> actual constructor(value : StateFlow<T>) : StateFlow<T> by value