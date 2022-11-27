package com.anurag.notekeeperdatabasedemo.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object :Migration(1,2){

    override fun migrate(database: SupportSQLiteDatabase) {

        database.execSQL("CREATE TABLE 'listitems' ('uid' INTEGER NOT NULL, 'value' TEXT NOT NULL, 'listid' INTEGER NOT NULL, PRIMARY KEY('uid'))")
    }

}