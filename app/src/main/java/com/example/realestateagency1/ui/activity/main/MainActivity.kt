package com.example.realestateagency1.ui.activity.main

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.realestateagency1.R
import com.example.realestateagency1.databinding.ActivityMainBinding
import com.example.realestateagency1.ui.fragment.registration.OnRegistrationListener
import com.example.realestateagency1.ui.util.Pref

class MainActivity : AppCompatActivity(), OnRegistrationListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_about_us,
                R.id.navigation_add
            )
        )


        if (!Pref(applicationContext).isBoardingShowed()){
            navController.navigate(R.id.loginFragment)
        } else{
            navController.navigateUp()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.radisFragment) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onRegistrationStatusChanged(isAdmin: Boolean) {
        binding.navView.menu.findItem(R.id.navigation_add)?.isVisible = isAdmin
    }
}