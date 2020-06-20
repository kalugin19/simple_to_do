package com.kalugin19.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.kalugin19.todoapp.data.db.TasksDao
import com.kalugin19.todoapp.data.pojo.Task
import com.kalugin19.todoapp.data.pojo.TaskType

class TasksRepositoryImpl(private val tasksDao: TasksDao) : TasksRepository {

    override fun loadTasks(type: TaskType): LiveData<List<Task>> {
        return when (type) {
            TaskType.COMPLETED -> tasksDao.getCompletedTasks()
            TaskType.NOT_COMPLETED -> tasksDao.getNonCompletedTasks()
            else -> tasksDao.getTasks()
        }
    }

    override fun loadTask(id: String): LiveData<Task> {
        return tasksDao.getTask(id)
    }

    override suspend fun add(task: Task) {
        tasksDao.insert(task)
    }

    override suspend fun delete(taskId: String) {
        tasksDao.delete(taskId)
    }

    override suspend fun update(task: Task) {
        tasksDao.update(task)
    }
}