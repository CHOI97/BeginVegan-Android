package com.example.presentation.view.main

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.example.presentation.R
import com.example.presentation.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        binding.lottieAnimationView.playAnimation()

        if (isVersionSOrHigher()) {
//            splashScreen.setKeepOnScreenCondition { true }
        } else {
            delayForSplashScreen { }
        }
    }
    private fun isVersionSOrHigher() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    private fun delayForSplashScreen(action: () -> Unit) {
        handler.postDelayed({
            run(action)
        }, 1000)
    }
}
