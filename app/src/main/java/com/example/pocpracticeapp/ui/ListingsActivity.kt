package com.example.pocpracticeapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.imageloader.ImageLoader
import com.example.imageloader.cache.DoubleCache
import com.example.pocpracticeapp.R
import com.example.pocpracticeapp.databinding.ActivityMainRicknmortyBinding
import com.example.pocpracticeapp.ui.datalist.DataListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ListingsActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader : ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainRicknmortyBinding =
            ActivityMainRicknmortyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        imageLoader.setCache(DoubleCache(applicationContext))
    }

    override fun onDestroy() {
        super.onDestroy()
        imageLoader.clearCache()
    }
}
