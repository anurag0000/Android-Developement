package com.anurag.notekeeperdatabasedemo.database

import androidx.room.*

@Dao
interface ListItemDao {

    @Query("SELECT * FROM listitem")
    fun getAll(): kotlin.collections.List<ListItem>

    /*@Transaction
    @Query("SELECT * FROM listitem")
    fun getListWithListItems(): kotlin.collections.List<ListWithListItems>*/

    @Insert
    fun insertAll(vararg listitems: com.anurag.notekeeperdatabasedemo.database.ListItem)

    @Delete
    fun delete(listitem: com.anurag.notekeeperdatabasedemo.database.ListItem)
}