package com.kgb.kapp

interface OnExecuteListener {
    fun onSucessed()

    fun onFailer(ex: Throwable)
}