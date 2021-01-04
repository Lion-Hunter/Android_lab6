package com.example.lab6

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.DownloadLayoutBinding
import java.net.URL

class DownloadPictureAsyncTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DownloadLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            DownloadImageTask(binding.imageView)
                .execute("https://wallbox.ru/wallpapers/main2" +
                        "/202046/16050262595faac1d3d9aec1.70131582.jpg")
        }
    }

    inner class DownloadImageTask(private val bmImage: ImageView) :
        AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg urls: String?): Bitmap? {
            val urlDisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                val download = URL(urlDisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(download)
            } catch (e: Exception) {
                Log.e("Error", e.message!!)
                e.printStackTrace()
            }
            return mIcon11 ?: throw IllegalStateException("Wrong")
        }

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }
    }
}