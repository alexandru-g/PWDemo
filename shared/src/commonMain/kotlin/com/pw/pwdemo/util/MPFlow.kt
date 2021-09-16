package com.pw.pwdemo.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

fun <T> Flow<T>.asMPFlow(): MPFlow<T> = MPFlow(this)

class MPFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {

    constructor() : this(emptyFlow())

    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }

    fun onValue(block: (T) -> Unit): Closeable {
        val job = Job()

        CoroutineScope(Dispatchers.Main + job).launch {
            block(first())
        }

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}

interface Closeable {
    fun close()
}