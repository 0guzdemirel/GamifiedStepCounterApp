package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.ActivityMainBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}