package com.pw.pwdemo.data

import com.pw.pwdemo.api.ApiClientFactory
import com.pw.pwdemo.api.model.AddTaskResponse
import com.pw.pwdemo.api.model.GetTasksResponse
import com.pw.pwdemo.model.Task
import io.ktor.client.request.*
import io.ktor.client.response.*
import io.ktor.client.statement.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.http.cio.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteRepository : Repository {

    private val httpClient by lazy { ApiClientFactory.create() }

    override fun getTasks(): Flow<List<Task>> {
        return flow {
            val response = httpClient.get<GetTasksResponse> {
                url("http://192.168.0.111:3001/tasks")
                contentType(ContentType.Application.Json)
            }
            emit(response.tasks)
        }
    }

    override suspend fun addTask(task: Task): Boolean {
        val response = httpClient.put<AddTaskResponse> {
            url("http://192.168.0.111:3001/tasks")
            contentType(ContentType.Application.Json)
            body = task
        }
        return response.result == "success"
    }

    override suspend fun updateTask(task: Task): Boolean {
        val response = httpClient.post<AddTaskResponse> {
            url("http://192.168.0.111:3001/tasks")
            contentType(ContentType.Application.Json)
            body = task
        }
        return response.result == "success"
    }

    override suspend fun removeTask(id: Long): Boolean {
        val response = httpClient.delete<HttpResponse> {
            url("http://192.168.0.111:3001/tasks")
            contentType(ContentType.Application.Json)
            parameter("id", id)
        }
        return response.status.isSuccess()
    }
}