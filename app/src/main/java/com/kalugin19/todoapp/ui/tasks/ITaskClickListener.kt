package com.kalugin19.todoapp.ui.tasks

import com.kalugin19.todoapp.data.pojo.Task

interface ITaskClickListener {
    fun openTask(id: String)

    fun completeTask(task: Task, completed: Boolean)
}