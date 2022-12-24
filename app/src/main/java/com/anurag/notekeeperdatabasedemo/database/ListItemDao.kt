package com.anurag.notekeeperdatabasedemo.database

import androidx.room.*
import kotlin.collections.List

@Dao
interface ListItemDao {

    @Query("SELECT * FROM listitem")
    fun getAll(): kotlin.collections.List<ListItem>

    @Transaction
    @Query("SELECT * FROM listitem WHERE value LIKE :query")
    fun searchListItems(query: String): List<ListItem>

    /*@Transaction
    @Query("SELECT * FROM listitem")
    fun getListWithListItems(): kotlin.collections.List<ListWithListItems>*/

    @Insert
    fun insertAll(vararg listitems: com.anurag.notekeeperdatabasedemo.database.ListItem)

    @Delete
    fun delete(listitem: com.anurag.notekeeperdatabasedemo.database.ListItem)

    @Update
    fun update(listitem: ListItem)
}