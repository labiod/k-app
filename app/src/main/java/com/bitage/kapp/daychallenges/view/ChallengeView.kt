package com.bitage.kapp.daychallenges.view

import android.app.ActionBar
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.OnChallengeActionListener
import com.bitage.kapp.presentation.KFragmentView
import com.bitage.kapp.presentation.KView

/**
 * List of challenges view
 */
interface ChallengeView : KFragmentView<DayChallengeViewModel, ChallengeSubView> {

    /**
     * Load list of template
     */
    fun loadTemplateData()

    fun setChallengeActionListener(listener: OnChallengeActionListener)
}