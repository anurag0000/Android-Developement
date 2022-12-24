package com.anurag.notekeeperdatabasedemo.ui.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anurag.notekeeperdatabasedemo.database.*
import com.anurag.notekeeperdatabasedemo.database.List


class NewlistViewModel : ViewModel() {

    private val dataRepo: ListRepository = ListRepository()
    private val listItemRepo: ListItemRepository = ListItemRepository()

    private val lastId = if(dataRepo.getAll().isNotEmpty()) dataRepo.getAll().last().list.uid else 0
    private var lastElementId = if(listItemRepo.getAll().isNotEmpty()) listItemRepo.getAll().last().uid else 0
    private  var indexInList = 0

    private var _listItem = MutableLiveData<ListWithListItems>().apply {
        value = ListWithListItems(List(lastId + 1, "Example title"), mutableListOf())
    }
    var listItem: LiveData<ListWithListItems> = _listItem
    fun setTitle(title:String) {
        _listItem.postValue(ListWithListItems(List(_listItem.value!!.list.uid, title), _listItem.value!!.ListItems))
    }
    fun addItem(item:String){
        val currentVal = _listItem.value ?: return
        val elements = currentVal.ListItems
        lastElementId += 1
        _listItem.postValue(ListWithListItems(List(currentVal.list.uid, currentVal.list.title), elements.plus(
            ListItem(
            lastElementId + 1, currentVal.list.uid, item,indexInList)
        )))
        Log.e("list uid", currentVal.list.uid.toString())
        Log.e("list element uid", (listItemRepo.getAll().size + 1).toString())
    }

    fun commit(){
        dataRepo.insert(listItem.value!!)
    }
}