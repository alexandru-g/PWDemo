package com.pw.pwdemo.api.model

import kotlinx.serialization.Serializable

@Serializable
data class AddTaskResponse(
    val result: String,
    val message: String? = null
)
