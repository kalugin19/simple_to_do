package com.kalugin19.todoapp

import android.content.Context
import androidx.room.Room
import com.kalugin19.todoapp.data.repository.TasksRepository
import com.kalugin19.todoapp.data.repository.TasksRepositoryImpl

object ServiceLocator {

    private lateinit var db: ToDoDatabase

    private fun createDb(context: Context) {
        db = Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java,
            "tasks.db"
        ).build()
    }

    fun createTasksRepository(context: Context): TasksRepository{
        createDb(context)
        return TasksRepositoryImpl(db.taskDao())
    }

//    val tasksRepository: TasksRepository by lazy {
//        object : TasksRepository {
//
//            private val tasks = mutableListOf<Task>(
//                Task(
//                    name = "Поход к стоматологу",
//                    description = "21го в 16:00",
//                    isCompleted = false
//                ),
//                Task(
//                    name = "Купить хлеба",
//                    description = "Желательно бородинского 2 шт",
//                    isCompleted = false
//                )
//            )
//
//            private val liveData = MutableLiveData<List<Task>>()
//
//            override fun loadTasks(type: TaskType): LiveData<List<Task>> {
//                tasks
//                    .filter { task ->
//                        when (type) {
//                            TaskType.COMPLETED -> task.isCompleted
//                            TaskType.NOT_COMPLETED -> !task.isCompleted
//                            else -> true
//                        }
//                    }
//                    .let { list ->
//                        liveData.value = list
//                    }
//                return liveData
//            }
//
//            override fun loadTask(id: String): LiveData<Task> {
//                val task = liveData
//                    .value
//                    ?.find {
//                        it.id == id
//                    }
//
//                return MutableLiveData<Task>(task!!)
//            }
//
//            override suspend fun add(task: Task) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    tasks.removeIf {
//                        it.id == task.id
//                    }
//                }
//                tasks.add(task)
//                liveData.postValue(tasks)
//            }
//
//            override suspend fun delete(taskId: String) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    tasks.removeIf {
//                        it.id == taskId
//                    }
//                    liveData.postValue(tasks)
//                }
//            }
//
//            override suspend fun update(task: Task) {
//                val index = tasks.indexOfFirst {
//                    it.id == task.id
//                }
//                tasks.set(index, task)
//                liveData.postValue(tasks)
//            }
//        }
//    }
}