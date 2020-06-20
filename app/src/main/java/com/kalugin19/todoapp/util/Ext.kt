package com.kalugin19.todoapp.util

import android.content.Context
import android.view.LayoutInflater

fun Context.inflater(): LayoutInflater{
    return LayoutInflater.from(this)
}