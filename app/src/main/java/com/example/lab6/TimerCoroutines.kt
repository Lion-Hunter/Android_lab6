package com.example.lab6


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.timer_layout.*
import kotlinx.coroutines.*

class TimerCoroutines : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var coroutine: Job
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_layout)

        if (savedInstanceState != null) {
            secondsElapsed = savedInstanceState.getInt(WATCH_STATE, 0)
        }
    }

    override fun onStart() {
        coroutine = scope.launch {
            while (true) {
                delay(1000)
                textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
            }
        }

        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        coroutine.cancel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(WATCH_STATE, secondsElapsed)
    }
}