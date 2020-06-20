package com.kalugin19.todoapp.data.pojo

import com.kalugin19.todoapp.R

enum class TaskType(val res: Int) {
    COMPLETED(R.string.type_completed),
    NOT_COMPLETED(R.string.type_not_completed),
    ALL(R.string.type_all)
}