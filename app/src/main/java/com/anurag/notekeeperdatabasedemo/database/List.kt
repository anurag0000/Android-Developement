package com.anurag.notekeeperdatabasedemo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anurag.notekeeperdatabasedemo.ListItem

@Entity
data class List(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    //@ColumnInfo(name = "elements") val elements: MutableList<ListItem>
)
