/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pw.pwdemo.android.todo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pw.pwdemo.model.Task
import com.pw.pwdemo.ui.ITasksViewModel
import com.pw.pwdemo.ui.TasksViewModel
import kotlinx.coroutines.flow.map

class TodoViewModel : ViewModel(), ITasksViewModel by TasksViewModel() {

    var todoItems = getTasks()
        .map { domainList -> domainList.map { TodoItem(it.text, id = it.id) } }
        .map { items -> items.map { if (it.id == currentEditItem.value?.id) currentEditItem.value!! else it } }

    var currentEditItem = mutableStateOf<TodoItem?>(null)

    fun addItem(item: TodoItem) {

        addTask(Task(id = item.id, item.task, false))
    }

    fun removeItem(item: TodoItem) {
        removeTask(item.id)
        onEditDone() // don't keep the editor open when removing items
    }

    fun onEditItemSelected(item: TodoItem) {
        currentEditItem.value = item
    }

    fun onEditDone() {
        currentEditItem.value?.let { updateTask(Task(id = it.id, it.task, false)) }

        currentEditItem.value = null
    }

    fun onEditItemChange(item: TodoItem) {
        val currentItem = requireNotNull(currentEditItem.value)
        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
        }
        currentEditItem.value = item
    }
}
