package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
        val navController = findNavController(R.id.nav_host_fragment_container)
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

        sharedViewModel.loggedIn.observe(this, Observer {
                isLoggedIn -> if (isLoggedIn.isBlank()) {
                    navController.navigate(R.id.login_destination)
                } else {
                    navController.navigate(R.id.welcome_destination)
                }
        })

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        binding.toolbar.inflateMenu(R.menu.overflow_menu)
//        return super.onCreateOptionsMenu(menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Timber.i("menu item clicked")
//        if (item.itemId==R.id.menu_item_logout) {
//            sharedViewModel.onLoggedOut()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.nav_host_fragment_container)
//        return navController.navigateUp()
//    }
}
