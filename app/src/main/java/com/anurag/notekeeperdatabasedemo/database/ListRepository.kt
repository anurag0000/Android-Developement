package com.anurag.notekeeperdatabasedemo.database

import com.anurag.notekeeperdatabasedemo.appDatabase
import kotlin.collections.List

val ListsToCompare = mutableListOf<ListWithListItems>()

class ListRepository : IRepository<ListWithListItems> {

    override fun getAll(): List<ListWithListItems> {
        return appDatabase.listDao().getListsWithListItems()
    }

    override fun insert(list: ListWithListItems) {
        appDatabase.listDao().insertAll(list.list)
        appDatabase.listItemDao().insertAll(*list.ListItems.toTypedArray())
        //return appDatabase.listDao().insertAll(value as com.anurag.notekeeperdatabasedemo.database.List)
    }

    override fun remove(index: Int) {
        val list = getAll()[index].list
        val listItem = getAll()[index].ListItems
        listItem.forEach {
            appDatabase.listItemDao().delete(it)
        }
        return appDatabase.listDao().delete(list)
    }

    override fun query(query: String): List<ListWithListItems> {
        //return appDatabase.listDao().searchList(query)
        return appDatabase.listDao().searchList("%".plus(query).plus("%"))
    }
}





