package com.kalugin19.todoapp.ui.new_task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalugin19.todoapp.data.pojo.Task
import com.kalugin19.todoapp.data.repository.TasksRepository
import kotlinx.coroutines.launch

class NewTaskViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    val nameLiveData = MutableLiveData<String>()
    val descriptionLiveData = MutableLiveData<String>()

    private var task = Task()

    fun add() {
        viewModelScope.launch {
            val name = nameLiveData.value
            val description = descriptionLiveData.value
            if (name != null && description != null) {
                task.apply {
                    this.name = name
                    this.description = description
                }
                tasksRepository.add(task)
            }
        }
    }

    fun loadTask(taskId: String?) {
        task = taskId?.let {
            tasksRepository.loadTask(it).value
                ?: Task()
        } ?: Task()

        nameLiveData.postValue(task.name)
        descriptionLiveData.postValue(task.description)
    }

}