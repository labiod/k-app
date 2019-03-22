package com.bitage.kapp.daychallenges

import android.app.ActionBar
import com.bitage.kapp.presentation.KView

/**
 * List of challenges view
 */
interface ChallengeListView : KView<DayChallengeViewModel> {

    /**
     * Load list of template
     */
    fun loadTemplateData()

    fun setChallengeActionListener(listener: OnChallengeActionListener)

    fun onResume()

    fun onPause()
}