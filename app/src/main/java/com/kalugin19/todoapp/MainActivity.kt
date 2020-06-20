package com.kalugin19.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kalugin19.todoapp.databinding.ActivityMainBinding
import com.kalugin19.todoapp.ui.FabState
import com.kalugin19.todoapp.ui.IFabClickListener

class MainActivity : AppCompatActivity(), IFabClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        setSupportActionBar(activityMainBinding.include.bottomAppBar)
        activityMainBinding.lifecycleOwner = this
    }


    override fun setFabClickListener(func: () -> Unit) {
        activityMainBinding.onClickFunc = func
    }

    override fun setFabState(state: FabState) {
        activityMainBinding.fabState = state
    }
}