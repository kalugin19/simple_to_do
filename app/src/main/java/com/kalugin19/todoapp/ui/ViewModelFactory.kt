package com.kalugin19.todoapp.ui

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.kalugin19.todoapp.data.repository.TasksRepository
import com.kalugin19.todoapp.ui.new_task.NewTaskViewModel
import com.kalugin19.todoapp.ui.tasks.TasksViewModel
import com.kalugin19.todoapp.ui.view_task.ViewTaskViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val tasksRepository: TasksRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(TasksViewModel::class.java) ->
                TasksViewModel(tasksRepository, handle)
            isAssignableFrom(NewTaskViewModel::class.java)->
                NewTaskViewModel(tasksRepository)
            isAssignableFrom(ViewTaskViewModel::class.java)->
                ViewTaskViewModel(tasksRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
