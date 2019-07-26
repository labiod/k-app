package com.bitage.kapp.daychallenges

import com.bitage.kapp.model.Challenge

interface OnChallengeActionListener {
    fun onChallengeFinish(challenge: Challenge)
    fun onChallengeDelete(challenge: Challenge)
    fun onChallengeEdit(challenge: Challenge)
}