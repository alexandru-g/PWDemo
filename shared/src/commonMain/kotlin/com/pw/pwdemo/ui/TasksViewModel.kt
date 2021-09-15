package com.pw.pwdemo.ui

import com.pw.pwdemo.data.LocalRepository
import com.pw.pwdemo.data.Repository
import com.pw.pwdemo.model.Task
import com.pw.pwdemo.util.MPFlow
import com.pw.pwdemo.util.asMPFlow
import kotlinx.coroutines.*

interface ITasksViewModel {

    val coroutineScope: CoroutineScope

    fun getTasks(): MPFlow<List<Task>>

    fun addTask(task: Task)

    fun updateTask(task: Task)

    fun removeTask(id: Long)
}

class TasksViewModel(override val coroutineScope: CoroutineScope) : ITasksViewModel {

    constructor() : this(coroutineScope = GlobalScope + Dispatchers.Main)

    private val repository: Repository = LocalRepository()

    override fun getTasks(): MPFlow<List<Task>> {
        return repository.getTasks().asMPFlow()
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