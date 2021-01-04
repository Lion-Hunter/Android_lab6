package com.example.lab6


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.DownloadLayoutBinding
import com.squareup.picasso.Picasso

class DownloadPicasso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DownloadLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Picasso.get().load("https://wallbox.ru/wallpapers/main2" +
                    "/202046/16050262595faac1d3d9aec1.70131582.jpg").into(binding.imageView)
        }
    }
}