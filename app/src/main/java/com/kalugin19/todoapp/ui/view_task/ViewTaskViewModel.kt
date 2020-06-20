package com.kalugin19.todoapp.ui.view_task

import androidx.lifecycle.*
import com.kalugin19.todoapp.data.repository.TasksRepository
import kotlinx.coroutines.launch

class ViewTaskViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    private val _taskId: MutableLiveData<String> = MutableLiveData<String>()
    private val _backLiveData = MutableLiveData<Unit>()
    val backLiveData: LiveData<Unit> = _backLiveData

    val task = _taskId.switchMap {
        tasksRepository.loadTask(it)
    }

    fun loadTask(id: String) {
        _taskId.value = id
    }

    fun delete() {
        viewModelScope.launch {
            _taskId.value?.let { tasksRepository.delete(it) }
            _backLiveData.postValue(null)
        }
    }
}