package com.bitage.kapp.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
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