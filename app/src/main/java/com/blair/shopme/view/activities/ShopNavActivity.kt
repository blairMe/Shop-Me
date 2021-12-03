package com.blair.shopme.view.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.blair.shopme.R
import com.blair.shopme.databinding.ActivityShopNavBinding

class ShopNavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShopNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_shop_nav)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.productsFragment, R.id.ordersFragment, R.id.soldProductsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}

//hiding bottom navigation
/*fun hideBottomNavigation() {
    //mBinding.navigationView.clearAnimation()
    //mBinding.navigationView.animate().translationY(mBinding.navigationView.height.toFloat()).duration = 300
    mBinding.navigationView.visibility = View.GONE
}*/

/*fun showBottomNavigation() {
    //mBinding.navigationView.clearAnimation()
    //mBinding.navigationView.animate().translationY(0f).duration = 300
    mBinding.navigationView.visibility = View.VISIBLE
}*/