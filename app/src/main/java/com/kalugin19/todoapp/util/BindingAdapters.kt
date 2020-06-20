package com.kalugin19.todoapp.util

import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kalugin19.todoapp.R
import com.kalugin19.todoapp.data.pojo.Task
import com.kalugin19.todoapp.ui.FabState
import com.kalugin19.todoapp.ui.tasks.ITaskClickListener
import com.kalugin19.todoapp.ui.tasks.TasksAdapter

@BindingAdapter(value = ["app:items", "app:viewModel"], requireAll = true)
fun RecyclerView.setItems(items: List<Task>?, taskClickListener: ITaskClickListener) {
    if (adapter == null) {
        adapter = TasksAdapter(taskClickListener)
    }
    if (items != null) {
        (adapter as TasksAdapter).tasks = items
    }
}

@BindingAdapter("app:hasFixedSize")
fun RecyclerView.hasFixedSize(value: Boolean) {
    setHasFixedSize(value)
}

@BindingAdapter("app:visibility")
fun View.setVisibility(flag: Boolean) {
    visibility = if (flag) View.VISIBLE else View.GONE
}

@BindingAdapter("app:fabState")
fun FloatingActionButton.changeState(state: FabState) {
    val layoutRes = when (state) {
        FabState.ADD -> {
            R.drawable.ic_add_24px
        }
        FabState.COMPLETE -> {
            R.drawable.ic_done_24px
        }
        FabState.EDIT -> {
            R.drawable.ic_create_24px
        }
    }
    layoutRes.let { res ->
        ContextCompat.getDrawable(context, res)
            ?.let {
                setImageDrawable(it)
            }
    }
}

@BindingAdapter("app:completedTask")
fun setStyle(textView: TextView, enabled: Boolean) {
    if (enabled) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("app:resText")
fun TextView.setTextRes(res: Int){
    setText(res)
}
