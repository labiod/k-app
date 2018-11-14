package com.bitage.kapp.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.bitage.kapp.model.ChallengeType

/**
 * Entity class for template that will be retrieve from database
 * @property id - primary key
 * @property templateName - template name
 */
@Entity(tableName = "templates")
data class TemplateEntity(
    /**
     * primary key for templates table
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    /**
     * template name
     */
    val templateName: String
) {
    /**
     * List of challenges added to template.
     */
    @Ignore
    val challenges = ArrayList<ChallengeType>()
}