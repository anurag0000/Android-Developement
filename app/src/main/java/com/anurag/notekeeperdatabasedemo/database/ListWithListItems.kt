package com.anurag.notekeeperdatabasedemo.database

import androidx.room.Embedded
import androidx.room.Relation

data class ListWithListItems(
    @Embedded val list: List,

    @Relation(
        parentColumn = "uid",
        entityColumn = "uid"
    )


    val ListItems: kotlin.collections.List<ListItem>
)