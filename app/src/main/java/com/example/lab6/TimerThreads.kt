package com.example.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.timer_layout.*

const val WATCH_STATE = "watch_state"


class TimerThreads : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private var onScreen = true

    private var backgroundThread = Thread {
        while (!Thread.currentThread().isInterrupted) {
            Thread.sleep(1000)
            if (onScreen) {
                textSecondsElapsed.post {
                    textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
                }
            } else Thread.currentThread().interrupt()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_layout)

        if (savedInstanceState != null) {
            secondsElapsed = savedInstanceState.getInt(WATCH_STATE, 0)
        }
    }

    override fun onStart() {
        backgroundThread.start()
        onScreen = true
        super.onStart()
    }

    override fun onStop() {
        onScreen = false
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(WATCH_STATE, secondsElapsed)
    }
}