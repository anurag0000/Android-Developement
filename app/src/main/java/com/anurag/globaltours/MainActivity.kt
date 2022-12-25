package com.anurag.globaltours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.anurag.globaltours.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar        : MaterialToolbar
    private lateinit var navController  : NavController
    //private lateinit var navigationView : NavigationView
    //private lateinit var drawerLayout: DrawerLayout

    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //navigationView = findViewById(R.id.nav_view)
        //drawerLayout = findViewById(R.id.drawer_layout)

        bottomNavView = findViewById(R.id.bottom_nav_view)

        // Initialize Views
        toolbar = findViewById(R.id.activity_main_toolbar)

        // Get NavHostFragment and NavController
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController   = navHostFrag.navController

        //Define AppBarConfiguration : COnnect Drawer Layout with Navigation component
        //val appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)

        //Define AppBarConfiguration
        val topLevelDesination = setOf(R.id.fragmentCityList,R.id.fragmentFavoriteList)
        //val appBarConfiguration = AppBarConfiguration(navController.graph)
        val appBarConfiguration = AppBarConfiguration(topLevelDesination)

        //Connect toolbar with Navigation Controller
        toolbar.setupWithNavController(navController,appBarConfiguration)

        //Connect navigationView with navController
        //navigationView.setupWithNavController(navController)

        //Connect BottomNavigationView with navController
        bottomNavView.setupWithNavController(navController)
    }

    /*override fun onBackPressed() {
        if(drawerLayout.isOpen()){
            drawerLayout.close()
        }
        else {
            super.onBackPressed()
        }
    }*/
}