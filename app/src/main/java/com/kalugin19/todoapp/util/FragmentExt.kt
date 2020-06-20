package com.kalugin19.todoapp.util

import androidx.fragment.app.Fragment
import com.kalugin19.todoapp.ToDoApplication
import com.kalugin19.todoapp.ui.ViewModelFactory


fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as ToDoApplication).tasksRepository
    return ViewModelFactory(repository, this)
}
