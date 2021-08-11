package com.align.fortnightly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.align.core.viewbinding.viewBinding
import com.align.fortnightly.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findRootNavController()

        val rootGraph = navController.navInflater.inflate(R.navigation.root_graph)
        navController.graph = rootGraph

        setupActionbar(binding.toolbar, navController)
    }

    private fun setupActionbar(toolbar: Toolbar, navController: NavController) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        toolbar.setupWithNavController(navController)
    }

    private fun findRootNavController(): NavController = findNavController(R.id.nav_host_fragment)
}