package com.bitage.kapp.daychallenges.view

import android.app.ActionBar
import androidx.fragment.app.Fragment
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.OnChallengeActionListener
import com.bitage.kapp.presentation.KView

/**
 * List of challenges view
 */
interface ChallengeListView : KView<DayChallengeViewModel> {

    /**
     * Load list of template
     */
    fun loadTemplateData()

    fun challengeSubmit(success: Boolean)

    fun showAddEditChallengeFragment(challengeId: Long? = null)

    fun attachFragment(fragment: Fragment)
}