package com.kalugin19.todoapp.ui.tasks

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kalugin19.todoapp.R
import com.kalugin19.todoapp.databinding.FragmentTasksBinding
import com.kalugin19.todoapp.ui.BaseFragment
import com.kalugin19.todoapp.ui.FabState
import com.kalugin19.todoapp.util.EventObserver
import com.kalugin19.todoapp.util.getViewModelFactory

class TasksFragment : BaseFragment() {

    private lateinit var binding: FragmentTasksBinding
    private val viewModel by viewModels<TasksViewModel> { getViewModelFactory() }
    override val fabState: FabState
        get() = FabState.ADD

    override val getOnFabExecute: () -> Unit
        get() = {
            TasksFragmentDirections.actionNavTasksToNewTaskFragment().let {
                findNavController().navigate(it)
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupNavigation()
        return binding.root
    }

    private fun setupNavigation() {
        viewModel.openTaskLiveData.observe(
            viewLifecycleOwner,
            EventObserver {
                TasksFragmentDirections.actionNavTasksToViewTaskFragment(it).let { directions ->
                    findNavController().navigate(directions)
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tasks_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> showBottomFilterMenu()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun showBottomFilterMenu(){
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_tasks_types)
        bottomSheetDialog.show()
    }
}