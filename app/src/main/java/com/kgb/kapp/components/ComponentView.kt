package com.kgb.kapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.kgb.kapp.R

class ComponentView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val root : View

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?)
        : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
        : this(context, null, 0, 0)

    init {
        root = LayoutInflater.from(context).inflate(R.layout.component_layout, this)
    }
}