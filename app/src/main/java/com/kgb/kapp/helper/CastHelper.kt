package com.kgb.kapp.helper

class CastHelper {
    companion object {

        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        fun<T> castUnsafe(obj: Any): T {
            return obj as T
        }
    }
}