package com.example.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.timer_layout.*

const val WATCH_STATE = "watch_state"


class TimerThreads : AppCompatActivity() {
    private var secondsElapsed: Int = 0

    private var backgroundThread = Thread {
        try {
            while (true) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
                }

                if (Thread.currentThread().isInterrupted) break
            }
        } catch (e: InterruptedException) {}
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
        super.onStart()
    }

    override fun onStop() {
        backgroundThread.interrupt()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(WATCH_STATE, secondsElapsed)
    }
}