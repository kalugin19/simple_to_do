package com.kalugin19.todoapp.ui.tasks

import androidx.lifecycle.*
import com.kalugin19.todoapp.data.pojo.Task
import com.kalugin19.todoapp.data.pojo.TaskType
import com.kalugin19.todoapp.data.repository.TasksRepository
import com.kalugin19.todoapp.util.Event
import kotlinx.coroutines.launch

class TasksViewModel(
    private val tasksRepository: TasksRepository,
    handle: SavedStateHandle
) : ViewModel(), ITaskClickListener {
    private val _typeLiveData: MutableLiveData<TaskType> =  MutableLiveData(TaskType.ALL)

    val typeLiveData: LiveData<TaskType> = _typeLiveData

    private val _openTaskLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val openTaskLiveData: LiveData<Event<String>> by lazy {
        _openTaskLiveData.map {
            val event = Event<String>(it)
            event
        }
    }

    val tasksLiveData = Transformations.switchMap(typeLiveData) {
        tasksRepository.loadTasks(it)
    }

    val emptyLiveData: LiveData<Boolean> = Transformations.map(tasksLiveData) {
        it?.isEmpty() == true
    }

    fun loadTasks(type: TaskType) {
        _typeLiveData.postValue(type)
    }

    override fun openTask(id: String) {
        _openTaskLiveData.postValue(id)
    }

    override fun completeTask(task: Task, completed: Boolean) {
        viewModelScope.launch {
            task.isCompleted = completed
            tasksRepository.update(task)
        }
    }

}
