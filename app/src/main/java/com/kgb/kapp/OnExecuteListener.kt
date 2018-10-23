package com.kgb.kapp

/**
 * OnExecuteListener callback class
 */
interface OnExecuteListener {
    /**
     * Call when action was finished with no error
     */
    fun onSucessed()

    /**
     * Call when during some action error was occur
     */
    fun onFailer(ex: Throwable)
}