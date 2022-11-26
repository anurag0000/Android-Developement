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
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"noekeeper_Database")
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

        val listDao = db.listDao()
        val lists : List<com.anurag.notekeeperdatabasedemo.database.List> = listDao.getAll()

        Log.e("lists", lists.toString())
        //listDao.insertAll(com.anurag.notekeeperdatabasedemo.database.List(uid = lists.last().uid + 1, title = "Test Title"))
        listDao.delete(lists[0])
        Log.e("lists", listDao.getAll().toString())



    }
}