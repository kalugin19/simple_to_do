package com.kalugin19.todoapp.ui.new_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kalugin19.todoapp.databinding.FragmentNewTaskBinding
import com.kalugin19.todoapp.ui.BaseFragment
import com.kalugin19.todoapp.ui.FabState
import com.kalugin19.todoapp.util.getViewModelFactory

class NewTaskFragment : BaseFragment() {

    private val viewModel by viewModels<NewTaskViewModel> { getViewModelFactory() }
    private val args by navArgs<NewTaskFragmentArgs>()

    private val func: () -> Unit = {
        viewModel.add()
        findNavController().popBackStack()
    }
    override val fabState: FabState
        get() = FabState.COMPLETE

    override val getOnFabExecute: () -> Unit
        get() = func

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentNewTaskBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@NewTaskFragment
                viewModel = this@NewTaskFragment.viewModel
                this@NewTaskFragment.viewModel.loadTask(args.taskId)
            }.root
    }
}