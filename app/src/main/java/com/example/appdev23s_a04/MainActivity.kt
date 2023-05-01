package com.example.appdev23s_a04

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GestureDetectorCompat
import com.google.android.material.snackbar.Snackbar
import java.util.Random

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private lateinit var gestureDetector: GestureDetectorCompat

    private lateinit var linearLayoutActivityButton: Button
    private lateinit var tableLayoutActivityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gestureDetector = GestureDetectorCompat(this, this)
        gestureDetector.setOnDoubleTapListener(this)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)

        linearLayoutActivityButton = findViewById(R.id.linearLayoutActivityButton)
        linearLayoutActivityButton.setOnClickListener {
            val intent = Intent(this, LinearLayoutActivity::class.java)
            startActivity(intent)
        }

        tableLayoutActivityButton = findViewById(R.id.tableLayoutActivityButton)
        tableLayoutActivityButton.setOnClickListener {
            val intent = Intent(this, TableLayoutActivity::class.java)
            startActivity(intent)
        }
    }

    class TouchTrackerView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent): Boolean {
        Snackbar.make(findViewById(android.R.id.content), "onDown", Snackbar.LENGTH_SHORT).show()
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        Snackbar.make(findViewById(android.R.id.content), "onShowPress", Snackbar.LENGTH_SHORT).show()
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        Snackbar.make(findViewById(android.R.id.content), "onSingleTapUp", Snackbar.LENGTH_SHORT)
            .show()
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        Snackbar.make(findViewById(android.R.id.content), "onSingleTapConfirmed", Snackbar.LENGTH_SHORT).show()
                return true
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        Snackbar.make(findViewById(android.R.id.content), "onDoubleTap", Snackbar.LENGTH_SHORT)
            .show()
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        Snackbar.make(findViewById(android.R.id.content), "onDoubleTapEvent", Snackbar.LENGTH_SHORT).show()
                return true
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Snackbar.make(findViewById(android.R.id.content), "onScroll", Snackbar.LENGTH_SHORT).show()
        return true
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Snackbar.make(findViewById(android.R.id.content), "onFling", Snackbar.LENGTH_SHORT).show()
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        Snackbar.make(findViewById(android.R.id.content), "onLongPress", Snackbar.LENGTH_SHORT)
            .show()
    }

}

class TouchTrackerView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    // A map to keep track of touch pointers
    private val pointerMap = mutableMapOf<Int, PointF>()

    // Paint objects to draw circles
    private val paintMap = mutableMapOf<Int, Paint>()

    // A paint object for drawing the finger count
    private val textPaint = Paint().apply {
        textSize = 100f
        color = Color.BLACK
        textAlign = Paint.Align.CENTER
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Iterate through each pointer in the event
        for (i in 0 until event.pointerCount) {
            val pointerId = event.getPointerId(i)
            val action = event.actionMasked

            // Update pointer position
            val x = event.getX(i)
            val y = event.getY(i)
            val pointF = pointerMap[pointerId] ?: PointF()
            pointF.set(x, y)
            pointerMap[pointerId] = pointF

            // Update paint object for this pointer
            val paint = paintMap.getOrPut(pointerId) {
                Paint().apply {
                    color = generateRandomColor()
                    style = Paint.Style.FILL
                }
            }

            // Update circle position and invalidate view
            if (action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_DOWN) {
                invalidate()
            }

            // Remove pointer and paint object if pointer is up
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) {
                pointerMap.remove(pointerId)
                paintMap.remove(pointerId)
                invalidate()
            }
        }

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw circles for each pointer
        for ((pointerId, pointF) in pointerMap) {
            val radius = 100f
            canvas.drawCircle(pointF.x, pointF.y, radius, paintMap[pointerId] ?: Paint())
        }

        // Draw finger count
        val count = pointerMap.size
        val centerX = width / 2f
        val centerY = height / 2f
        canvas.drawText(count.toString(), centerX, centerY, textPaint)
    }

    // Helper function to generate random colors
    private fun generateRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}