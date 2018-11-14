package com.bitage.kapp.db.converter

import android.arch.persistence.room.TypeConverter
import com.bitage.kapp.model.ChallengeType

/**
 * Converter class using by database for [ChallengeType] enum
 */
class ChallengeTypeConverter {
    /**
     * Convert [String] to [ChallengeType]
     * @param name - item to convert
     * @return [ChallengeType] for given string
     */
    @TypeConverter
    fun toChallengeType(name: String): ChallengeType {
        return ChallengeType.valueOf(name)
    }

    /**
     * Convert [ChallengeType] to [String]
     * @param challengeType - item to convert
     * @return [String] for given challenge type
     */
    @TypeConverter
    fun toChallengeName(challengeType: ChallengeType): String {
        return challengeType.name
    }
}