package com.bitage.naw_views

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import androidx.fragment.app.DialogFragment
import com.bitage.datasource.DialogButtonDataSource

class KDialog {
        companion object {
            fun create(
                context: Context,
                alertTextRes: Int,
                cancelButtonDataSource: DialogButtonDataSource,
                confirmButtonDataSource: DialogButtonDataSource
            ): Dialog {
                return AlertDialog.Builder(context)
                    .setTitle(alertTextRes)
                    .setNegativeButton(cancelButtonDataSource.textRes, cancelButtonDataSource.clickListener)
                    .setPositiveButton(confirmButtonDataSource.textRes, confirmButtonDataSource.clickListener)
                    .create()
            }
        }
}