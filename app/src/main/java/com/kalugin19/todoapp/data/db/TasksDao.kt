package com.kalugin19.todoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kalugin19.todoapp.data.pojo.Task

@Dao
interface TasksDao {

    @Query("SELECT * FROM TASKS")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM TASKS WHERE id = :id")
    fun getTask(id: String): LiveData<Task>

    @Query("SELECT * FROM TASKS WHERE completed = 1")
    fun getCompletedTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM TASKS WHERE completed = 0")
    fun getNonCompletedTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("DELETE FROM TASKS WHERE id = :taskId")
    suspend fun delete(taskId: String)
}