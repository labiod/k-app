package com.kgb.kapp.helper

import android.content.Context
import com.kgb.kapp.challenge.ChallengeType
import java.util.*
import kotlin.collections.ArrayList

class CastHelper {
    companion object {

        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        fun<T> castUnsafe(obj: Any): T {
            return obj as T
        }
    }
}