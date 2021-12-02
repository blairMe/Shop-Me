package com.blair.shopme.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.blair.shopme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    private lateinit var navController: NavController
    //private var navController2 = findNavController(R.id.navigation_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Displaying main activity
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)






        /* navController2.addOnDestinationChangedListener { _, destination, _ ->
             when (destination.id) {
                 //R.id.loginFragment -> hideBottomNavigation()
                 R.id.signupFragment -> hideBottomNavigation()
                 else -> showBottomNavigation()
             }*/
            /*if (destination.id == R.id.signupFragment) {
                visibility = View.GONE
            } else {
                mBinding.navigationView.visibility = View.VISIBLE
            }*/

        //}
    }


    //actionbar back button
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
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


}