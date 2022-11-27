package com.anurag.notekeeperdatabasedemo

import com.anurag.notekeeperdatabasedemo.database.ListItem
import com.anurag.notekeeperdatabasedemo.database.ListWithListItems

/*
data class ListItem(
    val id: Long,
    var title: String,
    val elements: MutableList<String>
)

val Lists = mutableListOf(
    ListItem(0, "List 1", elements = mutableListOf("Element 1", "Element 2")),
    ListItem(1, "List 2", elements = mutableListOf("Element 1", "Element 2")),
    ListItem(2, "List 3", elements = mutableListOf("Element 1", "Element 2"))

)*/


val ListsToCompare = mutableListOf<ListWithListItems>()

fun getLists() : List<ListWithListItems> {
    return appDatabase.listDao().getListsWithListItems()
}

fun getListItems(): List<ListItem> {
    return appDatabase.listItemDao().getAll()
}

fun addList(list: ListWithListItems) {
    appDatabase.listDao().insertAll(list.list)
    appDatabase.listItemDao().insertAll(*list.ListItems.toTypedArray())
}

fun deleteList(list: ListWithListItems) {
    appDatabase.listDao().delete(list.list)
}

fun getSubItems(item: ListWithListItems):String {
    val inListForm = item.ListItems.mapIndexed {idx, value -> (idx+1).toString().plus(". ").plus(value.value)}
    return inListForm.joinToString("\n")
}