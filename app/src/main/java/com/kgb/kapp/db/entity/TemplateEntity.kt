package com.kgb.kapp.db.entity

import android.arch.persistence.room.*
import com.kgb.kapp.challenge.ChallengeType

@Entity(tableName = "templates")
data class TemplateEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long? = null,
    val templateName: String
) {
    @Ignore
    val challenges = ArrayList<ChallengeType>()
}