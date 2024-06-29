package com.app.academy.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


/**
 * Written by Vikram Ragu on 28/06/24.
 */
actual object DispatchersProvider {

    actual val io : CoroutineDispatcher = Dispatchers.IO

}