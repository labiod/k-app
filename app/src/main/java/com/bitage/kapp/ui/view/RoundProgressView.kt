package com.bitage.kapp.ui.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.bitage.kapp.R
import com.bitage.naw_views.VProgress

/**
 * Android view with calendar to display current date in circle
 */
class RoundProgressView(context: Context, attributeSet: AttributeSet?, defStyle: Int)
    : LinearLayout(context, attributeSet, defStyle) {

    private val progressText: TextView
    private val progressView: VProgress

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, 0)

    init {
        inflate(context, R.layout.round_progress_layout, this)
        progressText = findViewById(R.id.progressText)
        progressView = findViewById(R.id.progressView)
        attributeSet?.let {
            var args = context.obtainStyledAttributes(attributeSet, R.styleable.VProgress)
            val progressColor = args.getColor(R.styleable.VProgress_progressColor, Color.BLUE)
            val progressTextColor = args.getColor(R.styleable.VProgress_progressTextColor, Color.BLACK)
            progressView.setColor(progressColor)
            progressText.setTextColor(progressTextColor)
        }
    }

    /**
     * Set progress for current date
     * @param actualProgress - actual progress for date
     * @param max - max value for date
     */
    fun setProgress(actualProgress: Int, max: Int) {
        progressText.text = resources.getString(R.string.show_progress_templ_text, actualProgress, max)
        progressView.setProgress(actualProgress, max)
    }
}