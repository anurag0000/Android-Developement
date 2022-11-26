package com.anurag.notekeeperdatabasedemo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlin.collections.List

@Dao
interface ListDao {

    @Query("SELECT * FROM list")
    fun getAll(): List<com.anurag.notekeeperdatabasedemo.database.List>

    @Insert
    fun insertAll(vararg list: com.anurag.notekeeperdatabasedemo.database.List)

    @Delete
    fun delete(list: com.anurag.notekeeperdatabasedemo.database.List)
}