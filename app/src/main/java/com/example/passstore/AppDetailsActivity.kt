package com.example.passstore

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passstore.databinding.ActivityAppDetailsBinding

class AppDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppDetailsBinding

    companion object {
        val EXTRA_APP = "The app"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // extract our Hero object from the intent
        val app = intent.getParcelableExtra<App>(EXTRA_APP)
        //put each field of the Hero object into the respective widgets
        binding.imageViewAppDetailsIcon.setImageDrawable(
            getDrawable(resources.getIdentifier(app?.icon, "drawable", packageName))
        )
        binding.textVIewAppDetailsName.text = app?.name ?: "App Name"
        binding.textViewAppDetailsEmail.text = app?.appEmail ?: "implement their email for their login"
        binding.textViewAppDetailsUser.text = app?.appUser ?: "implement their user for their login"
        binding.textViewAppDetailsPassword.text = app?.appPass ?: "empty?"


    }
}