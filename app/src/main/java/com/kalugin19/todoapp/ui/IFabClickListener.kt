package com.kalugin19.todoapp.ui

interface IFabClickListener {

    fun setFabClickListener(func: () -> Unit)

    fun setFabState(state: FabState)
}