package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.ActivityCreatePostBinding

class CreatePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setListeners()
        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.backRL.setOnClickListener {
            //TODO
        }

        binding.addPhotoRL.setOnClickListener {
            //TODO
        }

        binding.shareRL.setOnClickListener {
            //TODO
        }
    }
}