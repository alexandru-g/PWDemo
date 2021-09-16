package com.pw.pwdemo.data

import kotlinx.coroutines.flow.Flow
import com.pw.pwdemo.model.Task

interface Repository {
    fun getTasks(): Flow<List<Task>>
    suspend fun addTask(task: Task): Boolean
    suspend fun updateTask(task: Task): Boolean
    suspend fun removeTask(id: Long): Boolean
}