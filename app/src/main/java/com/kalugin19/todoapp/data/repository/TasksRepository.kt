package com.kalugin19.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.kalugin19.todoapp.data.pojo.Task
import com.kalugin19.todoapp.data.pojo.TaskType

interface TasksRepository {

    fun loadTasks(type: TaskType): LiveData<List<Task>>

    fun loadTask(id: String): LiveData<Task>

    suspend fun add(task: Task)

    suspend fun delete(taskId: String)

    suspend fun update(task: Task)
}