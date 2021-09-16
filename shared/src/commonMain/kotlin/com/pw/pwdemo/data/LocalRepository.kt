package com.pw.pwdemo.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.pw.pwdemo.db.TaskDao
import com.pw.pwdemo.model.Task

class LocalRepository : Repository {

    private val taskDao by lazy { TaskDao() }

    override fun getTasks(): Flow<List<Task>> =
        taskDao.getTasks().map { data -> data.map { mapFromDb(it) } }

    override suspend fun addTask(task: Task): Boolean {
        taskDao.insertTask(mapToDb(task))
        return true
    }

    override suspend fun updateTask(task: Task): Boolean {
        taskDao.updateTask(mapToDb(task))
        return true
    }

    override suspend fun removeTask(id: Long): Boolean {
        taskDao.removeTask(id)
        return true
    }

    private fun mapFromDb(dbTask: me.user.db.Task) =
        with(dbTask) {
            Task(id, text, finished > 0L)
        }

    private fun mapToDb(task: Task) =
        with(task) {
            me.user.db.Task(id, text, if (finished) 1L else 0L)
        }
}