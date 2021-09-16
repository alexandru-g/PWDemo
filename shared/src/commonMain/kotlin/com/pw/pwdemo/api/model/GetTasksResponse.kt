package com.pw.pwdemo.api.model

import com.pw.pwdemo.model.Task
import kotlinx.serialization.Serializable

@Serializable
data class GetTasksResponse(
    val tasks: List<Task>
)
