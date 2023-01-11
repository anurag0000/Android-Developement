package com.jwhh.notekeeper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemActivityViewModelFactory (val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return ItemActivityViewModel(context) as T
    }
}