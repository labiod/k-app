package com.bitage.kapp.converter

import androidx.room.TypeConverter
import com.bitage.kapp.model.UserInfoType

class UserInfoConverter {
    /**
     * Convert [String] to [UserInfoType]
     * @param name - item to convert
     * @return [UserInfoType]
     */
    @TypeConverter
    fun toUserInfoType(name: String): UserInfoType {
        return UserInfoType.valueOf(name.toUpperCase())
    }

    /**
     * Convert [UserInfoType] to [String]
     * @param userInfoType - item to convert
     * @return [String]
     */
    @TypeConverter
    fun toStepProgressName(userInfoType: UserInfoType): String {
        return userInfoType.name
    }
}