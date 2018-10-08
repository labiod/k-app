package com.kgb.kapp.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.kgb.kapp.challenge.ChallengeType

@Entity(tableName = "template_challenges")
data class TemplateChallengesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ForeignKey(entity = TemplateEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("templateId"))
    val templateId: Long,
    val challengeType: ChallengeType
)