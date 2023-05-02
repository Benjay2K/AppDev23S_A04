package com.example.appdev23s_a04

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class AboutActivity : AppCompatActivity() {

    private lateinit var closeAboutActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        closeAboutActivity = findViewById(R.id.closeAboutActivity)
        closeAboutActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val twitterImageButton = findViewById<ImageButton>(R.id.twitterImageButton)
        twitterImageButton.setOnClickListener {
            val url = "https://twitter.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        val facebookImageButton = findViewById<ImageButton>(R.id.facebookImageButton)
        facebookImageButton.setOnClickListener {
            val url = "https://www.facebook.com"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        val instagramImageButton = findViewById<ImageButton>(R.id.instagramImageButton)
        instagramImageButton.setOnClickListener {
            val url = "https://www.instagram.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}