package com.kgb.kapp.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.bitage.kapp.entity.TemplateEntity
import com.bitage.kapp.model.ChallengeType

/**
 * Entity class for challenges assign to template that will be retrieve from database
 * @property id - primary key
 */
@Entity(tableName = "template_challenges")
data class TemplateChallengesEntity(
    /**
     * entity primary key
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    /**
     * template id, foreign key to templates table
     */
    @ForeignKey(entity = TemplateEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("templateId"))
    val templateId: Long,
    /**
     * Challenge type
     */
    val challengeType: ChallengeType
)