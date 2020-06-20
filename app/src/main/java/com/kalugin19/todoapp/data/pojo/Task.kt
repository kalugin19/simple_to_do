package com.kalugin19.todoapp.data.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var description: String = "",
    @ColumnInfo(name = "completed")
    var isCompleted: Boolean = false
)