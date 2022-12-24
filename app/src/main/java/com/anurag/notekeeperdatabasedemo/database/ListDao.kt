package com.anurag.notekeeperdatabasedemo.database

import androidx.room.*
import kotlin.collections.List

@Dao
interface ListDao {

    @Query("SELECT * FROM list")
    fun getAll(): List<com.anurag.notekeeperdatabasedemo.database.List>

    @Query("SELECT * FROM list WHERE title LIKE '%Favorite%' ")
    fun getAllFavorites(): List<ListWithListItems>

    @Query("SELECT * FROM list WHERE title LIKE :query")
    fun searchList(query: String): List<ListWithListItems>

    @Transaction
    @Query("SELECT * FROM list")
    fun getListsWithListItems(): List<ListWithListItems>

    @Insert
    fun insertAll(vararg lists: com.anurag.notekeeperdatabasedemo.database.List)

    @Delete
    fun delete(list: com.anurag.notekeeperdatabasedemo.database.List)
}