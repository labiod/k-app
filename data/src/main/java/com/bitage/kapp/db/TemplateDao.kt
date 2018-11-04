package com.kgb.kapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.bitage.kapp.entity.TemplateEntity
import com.kgb.kapp.db.entity.TemplateChallengesEntity
import io.reactivex.Flowable

/**
 * Dao class that retrieve and manipulate (update/insert/delete) template data from database
 */
@Dao
interface TemplateDao {
    /**
     * Insert template to database
     * @return id for inserted row
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTemplate(templateEntity: TemplateEntity): Long

    /**
     * Insert challenges added to current template
     */
    @Insert
    fun insertChallengeForTemplate(challengesForTemplate: TemplateChallengesEntity)

    /**
     * Query all templates from database
     * @return all templates
     */
    @Query("SELECT * FROM templates")
    fun getAll(): List<TemplateEntity>

    /**
     * Query all challenges assigned to given template
     * @return list of challenges type
     */
    @Query("SELECT * FROM template_challenges WHERE templateId = :templateId")
    fun loadTemplateType(templateId: Long): List<TemplateChallengesEntity>
}