package com.pw.pwdemo.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Long,
    val text: String,
    val finished: Boolean
)
