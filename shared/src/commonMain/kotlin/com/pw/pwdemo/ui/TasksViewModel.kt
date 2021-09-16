package com.pw.pwdemo.ui

import com.pw.pwdemo.data.LocalRepository
import com.pw.pwdemo.data.Repository
import com.pw.pwdemo.model.Task
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

interface ITasksViewModel {

    val coroutineScope: CoroutineScope

    fun getTasks(): Flow<List<Task>>

    fun addTask(task: Task)

    fun updateTask(task: Task)

    fun removeTask(id: Long)
}

class TasksViewModel(override val coroutineScope: CoroutineScope) : ITasksViewModel {

    constructor() : this(coroutineScope = GlobalScope + Dispatchers.Main)

    private val repository: Repository = LocalRepository()

    override fun getTasks(): Flow<List<Task>> {
        return repository.getTasks()
    }

    override fun addTask(task: Task) {
        coroutineScope.launch {
            repository.addTask(task)
        }
    }

    override fun updateTask(task: Task) {
        coroutineScope.launch {
            repository.updateTask(task)
        }
    }

    override fun removeTask(id: Long) {
        coroutineScope.launch {
            repository.removeTask(id)
        }
    }
}