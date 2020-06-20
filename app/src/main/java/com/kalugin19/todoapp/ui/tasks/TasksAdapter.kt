package com.kalugin19.todoapp.ui.tasks

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kalugin19.todoapp.data.pojo.Task
import com.kalugin19.todoapp.databinding.ItemTaskBinding
import com.kalugin19.todoapp.util.inflater

class TasksAdapter(private val taskClickListener: ITaskClickListener) :
    RecyclerView.Adapter<Holder<*>>() {

    var tasks: List<Task> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<*> {
        return parent.context.inflater()
            .let { layoutInflater ->
                ItemTaskBinding.inflate(layoutInflater, parent, false)
            }
            .let {
                Holder(it)
            }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: Holder<*>, position: Int) {
        holder.bind(tasks[position])
    }

    private fun Holder<*>.bind(task: Task) {
        if (binding is ItemTaskBinding) {
            binding.apply {
                this.task = task
                this.taskClickListener = this@TasksAdapter.taskClickListener
                executePendingBindings()
            }
        }
    }
}

class Holder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)
