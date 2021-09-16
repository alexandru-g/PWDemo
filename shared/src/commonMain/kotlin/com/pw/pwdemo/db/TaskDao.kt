package com.pw.pwdemo.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import me.user.db.Task

class TaskDao {
    private val taskDbQueries by lazy { Database.taskDb.taskDbQueries }

    fun getTasks(): Flow<List<Task>> = taskDbQueries.getTasks().asFlow().mapToList()

    fun insertTask(task: Task) = taskDbQueries.insertTask(task)

    fun updateTask(task: Task) = taskDbQueries.updateTask(task.text, task.id)

    fun removeTask(id: Long) = taskDbQueries.deleteTask(id)
}