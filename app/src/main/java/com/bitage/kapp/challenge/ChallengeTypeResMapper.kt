package com.bitage.kapp.challenge

import com.bitage.kapp.R

/**
 * Enum for mapped ChallengeType to android resources
 */
enum class ChallengeTypeResMapper(val resId: Int) {
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
    HANDSTAND_PUSHUP(R.string.challenge_handstand_pushup_name)
}