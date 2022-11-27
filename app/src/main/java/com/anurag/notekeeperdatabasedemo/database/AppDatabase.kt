package com.anurag.notekeeperdatabasedemo.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(List::class, ListItem::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listDao(): ListDao
    abstract fun listItemDao(): ListItemDao
}