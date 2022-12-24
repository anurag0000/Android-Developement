package com.anurag.notekeeperdatabasedemo.ui.listcontainer

import androidx.lifecycle.ViewModel
import com.anurag.notekeeperdatabasedemo.database.ListRepository

class ListContainerFragmentViewModel : ViewModel() {

    val dataRepo = ListRepository()
}