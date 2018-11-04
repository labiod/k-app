package com.bitage.kapp.model

/**
 * Template class
 */
data class Template(
    /**
     * template id
     */
    val id: Long? = null,
    /**
     * template name
     */
    val templateName: String
) {
    /**
     * List of challenges added to template.
     */
    val challenges = ArrayList<ChallengeType>()
}