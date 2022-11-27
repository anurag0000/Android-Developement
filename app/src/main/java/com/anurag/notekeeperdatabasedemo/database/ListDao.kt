package com.anurag.notekeeperdatabasedemo.database

import androidx.room.*
import kotlin.collections.List

@Dao
interface ListDao {

    @Query("SELECT * FROM list")
    fun getAll(): List<com.anurag.notekeeperdatabasedemo.database.List>

    @Transaction
    @Query("SELECT * FROM list")
    fun getListWithListItems(): List<ListWithListItems>

    @Insert
    fun insertAll(vararg list: com.anurag.notekeeperdatabasedemo.database.List)

    @Delete
    fun delete(list: com.anurag.notekeeperdatabasedemo.database.List)
}