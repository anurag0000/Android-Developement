package com.anurag.notekeeperdatabasedemo.ui.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anurag.notekeeperdatabasedemo.database.List
import com.anurag.notekeeperdatabasedemo.database.ListItem
import com.anurag.notekeeperdatabasedemo.database.ListWithListItems
import com.anurag.notekeeperdatabasedemo.getListItems
import com.anurag.notekeeperdatabasedemo.getLists


class NewlistViewModel : ViewModel() {

    private val lastId = getLists().last().list.uid
    private val lastElementId = getListItems().last().uid

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
        _listItem.postValue(ListWithListItems(List(currentVal.list.uid, currentVal.list.title), elements.plus(
            ListItem(
            lastElementId + 1, currentVal.list.uid, item)
        )))
        Log.e("list uid", currentVal.list.uid.toString())
        Log.e("list element uid", (getListItems().size + 1).toString())
    }
}