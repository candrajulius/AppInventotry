package com.candra.projectinventory.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.candra.projectinventory.R
import com.candra.projectinventory.databinding.ActivitySplashScreenBinding
import com.candra.projectinventory.ui.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private lateinit var splashScreenBinding: ActivitySplashScreenBinding
    private val splashScreenViewModel by viewModels<ProductViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)
        supportActionBar?.hide()
        splashScreenBinding.textTitleSplashscreen.text = getString(
            R.string.title_splash_screen
        ).uppercase()
        checkUserLoginOrNot()
    }

    private fun checkUserLoginOrNot(){
        Handler(mainLooper).postDelayed({
            splashScreenViewModel.getUserLogin(this@SplashScreen).observe(this){ it ->
                val targetActivity = if (it.isLogin == true) MenuActivity::class.java else MainActivity::class.java
                startActivity(Intent(this@SplashScreen,targetActivity))
                finish()
            }
        },3000L)
    }
}