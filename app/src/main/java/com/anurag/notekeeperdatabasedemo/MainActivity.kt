package com.anurag.notekeeperdatabasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Database
import androidx.room.Room
import com.anurag.notekeeperdatabasedemo.database.AppDatabase
import com.anurag.notekeeperdatabasedemo.database.ListItem
import com.anurag.notekeeperdatabasedemo.database.migrations.MIGRATION_1_2
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var appDatabase: AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appDatabase = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"noekeeper_Database")
            .addMigrations(MIGRATION_1_2)
            .allowMainThreadQueries()
            .build()

        // Setting ActionBar logo
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.ic_logo)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        setContentView(R.layout.activity_main)

        // Setup navigation
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_delete, R.id.navigation_listcontainer, R.id.navigation_newlist))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /*val listDao = appDatabase.listDao()
        val listitemDao = appDatabase.listItemDao()

        //val lists : List<com.anurag.notekeeperdatabasedemo.database.List> = listDao.getAll()
        //val listItem: List<ListItem> = listitemDao.getAll()

        Log.e("lists", listDao.getAll().toString())
        Log.e("listsItem", listitemDao.getAll().toString())

        listitemDao.insertAll(ListItem(uid = 4, listId = listDao.getAll()[0].uid, value = "Test Item!!"))

        Log.e("lists", listDao.getListsWithListItems()[0].ListItems.toString())
        Log.e("listsItem", listitemDao.getAll().toString())*/



        //listDao.insertAll(com.anurag.notekeeperdatabasedemo.database.List(uid = listDao.getAll().last().uid + 1, title = "Test Title"))
        //listDao.delete(lists[0])
        //Log.e("lists", listDao.getAll().toString())



    }
}







