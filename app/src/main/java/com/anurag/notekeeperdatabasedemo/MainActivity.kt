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
import com.anurag.notekeeperdatabasedemo.database.migrations.MIGRATION_2_3
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var appDatabase: AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appDatabase = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"noekeeper_Database")
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
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


          /*  getLists().forEach { list-> list.ListItems.forEachIndexed{index, listItem ->
            listItem.order_number = index
            appDatabase.listItemDao().update(listItem)
        } }*/

        //getLists().forEach{list -> Log.e("Elements", list.ListItems.toString())}


    }
}







