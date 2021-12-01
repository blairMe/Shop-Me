package com.blair.shopme.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.blair.shopme.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    lateinit var mBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Displaying activity
        mBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)

        //implementing the splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}

//#900A0A
//#FFCECE