package com.kgb.kapp.db.converter

import android.arch.persistence.room.TypeConverter
import com.kgb.kapp.challenge.ChallengeType

class ChallengeTypeConverter {
    @TypeConverter
    fun toChallengeType(name: String): ChallengeType {
        return ChallengeType.valueOf(name)
    }

    @TypeConverter
    fun toChallengeName(challengeType: ChallengeType): String {
        return challengeType.name
    }
}