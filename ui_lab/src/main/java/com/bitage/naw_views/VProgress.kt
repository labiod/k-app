package com.bitage.naw_views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.random.Random

class VProgress(context: Context, attributeSet: AttributeSet?, defStyle: Int)
    : View(context, attributeSet, defStyle) {
    private val paint: Paint = Paint()
    private val bubblePaint: Paint = Paint()
    private val borderPaint: Paint
    private var angle: Float = 0f
    private val minAngleDiff = -90f
    private val maxAngleDiff = 90f
    private var diffAngle = 90f
    private val bubbles: ArrayList<PointF> = ArrayList()
    private var progress: Int = 0
    private var max: Int = 0
    private val rand = Random(System.currentTimeMillis())
    private val animationHandler = Handler(Looper.getMainLooper())
    private var mod = 1
    private val rect = RectF()
    private val animationRunnable = object : Runnable {
        private var lastCreateBubbleTime = 0L
        override fun run() {
            val toRemove = arrayListOf<PointF>()
            if (bubbles.size < MAX_BUBBLES && System.currentTimeMillis() - lastCreateBubbleTime > BUBBLES_TIME_ELAPSE) {
                val x = calculateNewPosition()
                val minHeight = Math.abs(x - width/2)
                bubbles.add(PointF(x, height.toFloat() - minHeight))
                lastCreateBubbleTime = System.currentTimeMillis()
            }
            bubbles.forEach {
                it.y -= BUBBLES_SPEED
                val minHeight = 5
                if (it.y < minHeight) {
                    toRemove.add(it)
                }
            }
            bubbles.removeAll(toRemove)

            invalidate()
            animationHandler.postDelayed(this, 30)
        }
    }

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    init {
        paint.color = Color.BLUE
        bubblePaint.color = Color.BLUE
        bubblePaint.style = Paint.Style.STROKE
        bubblePaint.strokeWidth = 2f
        borderPaint = Paint()
        borderPaint.color = Color.RED
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = BORDER_SIZE
        animationRunnable.run()
    }

    fun setProgress(progress: Int, max: Int) {
        val maxAngle = 360f
        val vp = progress.toFloat() / max
        this.progress = progress
        this.max = max
        angle = maxAngle * vp
        diffAngle = calculateDiff(vp)
        Log.d("kgb", "diff = $diffAngle")
    }

    override fun onDraw(canvas: Canvas?) {
        rect.set(0f, 0f, width.toFloat(), height.toFloat())
        canvas?.drawArc(rect, diffAngle, angle, false, paint)
        bubbles.forEach { bubble ->
            rect.set(bubble.x, bubble.y, bubble.x + 20, bubble.y + 20)
            canvas?.drawOval(rect, bubblePaint)
        }
        canvas?.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, borderPaint)
    }

    fun setColor(color: Int) {
        paint.color = color
        bubblePaint.color = color
    }

    private fun calculateDiff(progressPercentage: Float): Float {
        if (progressPercentage == 0.0f) {
            return maxAngleDiff
        }
        if (progressPercentage == 1f) {
            return minAngleDiff
        }
        // y = a * x + b
        // y1 = a * x1 + b => b = y1 - a * x1
        // y2 = a * x2 + b => y2 = a * x2 + y1 - a * x1 => y2 - y1 = a * (x2 - x1)
        // a = (y2 - y1) / (x2 - x1)
        val a = (minAngleDiff - maxAngleDiff) / (1f - 0.0)
        val b = minAngleDiff - a
        return (progressPercentage * a + b).toFloat()
    }

    private fun calculateNewPosition(): Float {
        mod = -mod
        if (width == 0) {
            return 0f
        }
        val diffWidth = width / 14
        val baseX = width / 2
        val diff = rand.nextFloat()

        return baseX + diffWidth * diff * mod

    }

    companion object {
        const val BORDER_SIZE = 1f
        const val MAX_BUBBLES = 12
        const val BUBBLES_SPEED = 5
        const val BUBBLES_TIME_ELAPSE = 800
    }
}