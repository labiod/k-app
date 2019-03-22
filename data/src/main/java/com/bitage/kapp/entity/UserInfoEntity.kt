package com.bitage.kapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val fieldName: String,
    val fieldValue: String
)