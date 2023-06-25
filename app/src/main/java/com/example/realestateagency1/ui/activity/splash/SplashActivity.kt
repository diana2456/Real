package com.example.realestateagency1.ui.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.realestateagency1.databinding.ActivitySpalshBinding
import com.example.realestateagency1.ui.activity.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySpalshBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpalshBinding.inflate(layoutInflater)
        start()
        setContentView(binding.root)
        supportActionBar?.hide()
    }


    private fun start() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000)
    }
}