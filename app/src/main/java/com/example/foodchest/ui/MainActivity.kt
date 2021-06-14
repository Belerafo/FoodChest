package com.example.foodchest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodchest.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_FoodChest)
        setContentView(R.layout.activity_main)

        applyStatusBarColor(R.color.statusBarColor)

        val navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.recipesFragment,
                R.id.favoriteRecipesFragment,
                R.id.foodJokeFragment
            )
        )

        bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }



    private fun applyStatusBarColor(color: Int) {
        window.statusBarColor =
            ContextCompat.getColor(applicationContext, color)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}