package com.kalugin19.todoapp.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected lateinit var fabListener: IFabClickListener

    abstract val fabState: FabState
    abstract val getOnFabExecute: () -> Unit

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fabListener = context as IFabClickListener
        } catch (e: ClassCastException) {
            throw RuntimeException("Activity must to implement ${fabListener.javaClass}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabListener.setFabState(fabState)
        fabListener.setFabClickListener(getOnFabExecute)
    }
}