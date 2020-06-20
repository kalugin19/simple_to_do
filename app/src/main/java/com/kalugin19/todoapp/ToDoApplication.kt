package com.kalugin19.todoapp

import android.app.Application

class ToDoApplication : Application() {

    val tasksRepository by lazy {
        ServiceLocator.createTasksRepository(this)
    }

}