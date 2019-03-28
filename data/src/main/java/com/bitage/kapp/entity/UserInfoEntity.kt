package com.bitage.kapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bitage.kapp.model.UserInfoType

@Entity(tableName = "user_info")
data class UserInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val fieldName: UserInfoType,
    val fieldValue: String
)