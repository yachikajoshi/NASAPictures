package com.aks.nasapictures.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.aks.nasapictures.R
import com.aks.nasapictures.databinding.ActivityNasapicturesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NASAPicturesActivity : AppCompatActivity() {
    private lateinit var nasaPicturesBinding: ActivityNasapicturesBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nasaPicturesBinding = ActivityNasapicturesBinding.inflate(layoutInflater)
        setContentView(nasaPicturesBinding.root)

        setSupportActionBar(nasaPicturesBinding.toolbar)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(
            appBarConfiguration
        )
    }
}