package com.kgb.kapp.challenge

import com.kgb.kapp.R

/**
 * Enum class of all challenges type
 */
enum class ChallengeType(
    /**
     * res id for challenge type to proper display name for supported languages
     */
    val challengeResId: Int
) {
    /**
     * pushup challenge type
     */
    PUSHUP(R.string.challenge_pushup_name),
    /**
     * pullup challenge type
     */
    PULLUP(R.string.challenge_pullup_name),
    /**
     * squat challenge type
     */
    SQUAT(R.string.challenge_squat_name),
    /**
     * leg raise challenge type
     */
    LEG_RAISE(R.string.challenge_leg_raise_name),
    /**
     * bridge challenge type
     */
    BRIDGE(R.string.challenge_bridge_name),
    /**
     * hand stand pushup challenge type
     */
    HANDSTAND_PUSHUP(R.string.challenge_handstand_pushup_name),
}