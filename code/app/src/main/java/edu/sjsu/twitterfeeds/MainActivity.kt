package edu.sjsu.twitterfeeds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var appbarConfig: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navigationController = findNavController(R.id.content_main)
        appbarConfig = AppBarConfiguration(navigationController.graph)
        setupActionBarWithNavController(navigationController, appbarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.content_main)
        return navController.navigateUp(appbarConfig) || super.onSupportNavigateUp()
    }
}