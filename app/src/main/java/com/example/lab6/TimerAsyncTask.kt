package com.example.lab6

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.timer_layout.*
import java.util.concurrent.TimeUnit


class TimerAsyncTask : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var asyncTask: MyAsyncTask


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_layout)

        if (savedInstanceState != null) {
            secondsElapsed = savedInstanceState.getInt(WATCH_STATE, 0)
        }
    }

    override fun onStart() {
        asyncTask = MyAsyncTask()
        asyncTask.execute()
        super.onStart()
    }

    override fun onStop() {
        asyncTask.cancel(true)
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(WATCH_STATE, secondsElapsed)
    }

    inner class MyAsyncTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            while (!isCancelled) {
                TimeUnit.SECONDS.sleep(1)
                publishProgress()
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
            textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
        }
    }
}