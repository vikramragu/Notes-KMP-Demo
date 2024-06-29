package com.app.academy.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


/**
 * Written by Vikram Ragu on 27/06/24.
 */
expect object DispatchersProvider {

    val io : CoroutineDispatcher

}

data class DispatchersProviderList(
    val ioDispatcher: CoroutineDispatcher = DispatchersProvider.io,
    val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
)
