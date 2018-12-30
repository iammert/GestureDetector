package com.iammert.gesturedetector

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.iammert.gesturedetectorlib.LongTouchGestureDetector

class MainActivity : AppCompatActivity() {

    private val longTouchGestureDetector = LongTouchGestureDetector(object : LongTouchGestureDetector.LongTouchListener {
        override fun onLongTouchEvent(event: MotionEvent) {
            // gets every touch events
            Log.v("test", "Event!" + event.action)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.view).setOnTouchListener { _, event -> longTouchGestureDetector.onTouchEvent(event) }
    }
}
