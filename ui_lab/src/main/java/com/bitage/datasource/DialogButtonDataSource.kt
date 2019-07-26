package com.bitage.datasource

import android.content.DialogInterface

data class DialogButtonDataSource(
    val textRes: Int,
    val clickListener: (dialog: DialogInterface, res: Int) -> Unit
) : DataSource