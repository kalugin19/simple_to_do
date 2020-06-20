package com.kalugin19.todoapp.ui.view_task

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kalugin19.todoapp.R
import com.kalugin19.todoapp.databinding.FragmentTaskViewBinding
import com.kalugin19.todoapp.ui.BaseFragment
import com.kalugin19.todoapp.ui.FabState
import com.kalugin19.todoapp.util.getViewModelFactory

class ViewTaskFragment : BaseFragment() {

    private val viewModel by viewModels<ViewTaskViewModel> { getViewModelFactory() }
    private val args: ViewTaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return FragmentTaskViewBinding.inflate(inflater, container, false)
            .apply {
                this.lifecycleOwner = this@ViewTaskFragment
                this.viewModel = this@ViewTaskFragment.viewModel
            }.root
    }

    override val fabState: FabState
        get() = FabState.EDIT

    override val getOnFabExecute: () -> Unit
        get() = {
            ViewTaskFragmentDirections.actionViewTaskFragmentToNewTaskFragment(args.taskId).apply {
                findNavController().navigate(this)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabListener.setFabState(FabState.EDIT)
        viewModel.loadTask(args.taskId)
        viewModel.backLiveData.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.view_screen_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> viewModel.delete()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}