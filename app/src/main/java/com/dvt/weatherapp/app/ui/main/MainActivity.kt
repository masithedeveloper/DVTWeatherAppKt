package com.dvt.weatherapp.app.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.dvt.weatherapp.app.R
import com.dvt.weatherapp.app.base.BaseActivity
import com.dvt.weatherapp.app.databinding.ActivityMainBinding
import com.dvt.weatherapp.app.utils.extensions.hide
import com.dvt.weatherapp.app.utils.extensions.show
import com.google.android.material.navigation.NavigationView
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java), HasSupportFragmentInjector, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setTransparentStatusBar()
        setupNavigation()
    }

    private fun setTransparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemSearch -> {
                findNavController(R.id.container_fragment).navigate(R.id.searchFragment)
                true
            }
            else -> false
        }
    }

    private fun setupNavigation() {
        val appBarConfig = AppBarConfiguration(
            setOf(R.id.dashboardFragment),
            binding.drawerLayout
        )

        val navController = findNavController(R.id.container_fragment)
        binding.toolbar.overflowIcon = getDrawable(R.drawable.ic_menu)
        binding.toolbar.navigationIcon?.setTint(Color.parseColor("#130e51"))
        setupWithNavController(binding.toolbar, navController, appBarConfig)
        setupWithNavController(binding.navigationView, navController)
        binding.navigationView.setNavigationItemSelectedListener(this)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    binding.toolbar.hide()
                }
                R.id.dashboardFragment -> {
                    binding.toolbar.show()
                    binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
                }
                R.id.searchFragment -> {
                    binding.toolbar.hide()
                }
                else -> {
                    binding.toolbar.setNavigationIcon(R.drawable.ic_back)
                    binding.toolbar.show()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(findNavController(R.id.container_fragment), binding.drawerLayout)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return item.onNavDestinationSelected(findNavController(R.id.container_fragment)) || super.onOptionsItemSelected(item)
    }
}
