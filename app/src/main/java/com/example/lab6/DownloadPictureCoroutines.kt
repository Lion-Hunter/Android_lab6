package com.example.lab6


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.DownloadLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class DownloadPictureCoroutines : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DownloadLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            scope.launch(Dispatchers.IO) {
                var mIcon11: Bitmap? = null
                try {
                    val stream = URL("https://wallbox.ru/wallpapers/main2" +
                            "/202046/16050262595faac1d3d9aec1.70131582.jpg").openStream()
                    mIcon11 = BitmapFactory.decodeStream(stream)
                } catch (e: Exception) {
                    e.message?.let { Log.e("Error", it) }
                    e.printStackTrace()
                }

                launch(Dispatchers.Main) {
                    binding.imageView.setImageBitmap(mIcon11)
                }
            }
        }
    }
}