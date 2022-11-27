package com.anurag.notekeeperdatabasedemo.database

import androidx.room.*
import kotlin.collections.List

@Dao
interface ListDao {

    @Query("SELECT * FROM list")
    fun getAll(): List<com.anurag.notekeeperdatabasedemo.database.List>

    @Transaction
    @Query("SELECT * FROM list")
    fun getListsWithListItems(): List<ListWithListItems>

    @Insert
    fun insertAll(vararg lists: com.anurag.notekeeperdatabasedemo.database.List)

    @Delete
    fun delete(list: com.anurag.notekeeperdatabasedemo.database.List)
}