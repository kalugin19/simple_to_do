package com.kalugin19.todoapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kalugin19.todoapp.data.db.TasksDao
import com.kalugin19.todoapp.data.pojo.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun taskDao(): TasksDao

}