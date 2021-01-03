package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        binding.fabAddNewShoe.setOnClickListener { sharedViewModel.onFabClicked() }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        setSupportActionBar(binding.toolbar)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.login_destination,
            R.id.welcome_destination, R.id.shoe_list_destination))

        navController.addOnDestinationChangedListener {
                controller, destination, arguments ->
            Timber.i("Destination: ${destination.id} ${destination.label}")
            if (destination.id == R.id.shoe_list_destination) {
                binding.fabAddNewShoe.show()
            } else {
                binding.fabAddNewShoe.hide()
            }
        }
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

    }
}
