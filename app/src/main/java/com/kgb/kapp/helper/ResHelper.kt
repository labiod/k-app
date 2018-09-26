package com.kgb.kapp.helper

import android.content.Context
import android.os.Build

class ResHelper {
    companion object {
        @Suppress("DEPRECATION")
        @JvmStatic
        fun getColor(context: Context, colorRes: Int): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.resources.getColor(colorRes, context.theme)
            } else {
                context.resources.getColor(colorRes)
            }
        }
    }
}