package com.kgb.kapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.db.entity.TemplateChallengesEntity
import com.kgb.kapp.db.entity.TemplateEntity

@Dao
interface TemplateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTemplate(templateEntity: TemplateEntity) : Long

    @Insert
    fun insertChallengeForTemplate(challengesForTemplate: TemplateChallengesEntity)

    @Query("SELECT * FROM templates")
    fun getAll(): LiveData<List<TemplateEntity>>

    @Query("SELECT * FROM template_challenges WHERE templateId = :templateId")
    fun loadTemplateType(templateId: Long): List<TemplateChallengesEntity>
}