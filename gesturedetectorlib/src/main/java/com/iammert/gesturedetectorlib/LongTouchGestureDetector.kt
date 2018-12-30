package com.iammert.gesturedetectorlib

import android.graphics.PointF
import android.os.Handler
import android.view.MotionEvent

class LongTouchGestureDetector(private val longTouchListener: LongTouchListener,
                               private val longPressDuration: Long = LONG_PRESS_DURATION,
                               private val touchSlop: Int = DEFAULT_TOUCH_SLOP) {

    interface LongTouchListener {
        fun onLongTouchEvent(event: MotionEvent)
    }

    private var downTouchPoint = PointF()
    private var currentDownMotion: MotionEvent? = null
    private var onLongPressed = false
    private var handler = Handler()

    private var longPressRunnable = Runnable {
        longTouchListener.let {
            it.onLongTouchEvent(currentDownMotion!!)
            onLongPressed = true
        }
    }

    fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downTouchPoint.set(event.rawX, event.rawY)
                currentDownMotion = MotionEvent.obtain(event)
                handler.postDelayed(longPressRunnable, longPressDuration)
            }
            MotionEvent.ACTION_MOVE -> {
                if (onLongPressed) {
                    longTouchListener.onLongTouchEvent(event)
                    return true
                }

                if (insideTheTouchSlop(event).not()) {
                    cancel()
                }
            }
            MotionEvent.ACTION_UP -> {
                if (onLongPressed) {
                    longTouchListener.onLongTouchEvent(event)
                    cancel()
                    return true
                }
                cancel()
            }
            else -> cancel()
        }

        return true
    }

    private fun insideTheTouchSlop(motionEvent: MotionEvent) =
            Math.abs(motionEvent.rawX - downTouchPoint.x) <= touchSlop &&
                    Math.abs(motionEvent.rawY - downTouchPoint.y) <= touchSlop

    private fun cancel() {
        handler.removeCallbacks(longPressRunnable)
        onLongPressed = false
        currentDownMotion = null
    }

    companion object {
        private const val LONG_PRESS_DURATION = 1000L
        private const val DEFAULT_TOUCH_SLOP = 50
    }
}