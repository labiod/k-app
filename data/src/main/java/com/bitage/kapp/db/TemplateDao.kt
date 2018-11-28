package com.bitage.kapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.bitage.kapp.entity.TemplateEntity
import com.bitage.kapp.db.entity.TemplateChallengesEntity
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
    fun getAll(): Flowable<List<TemplateEntity>>

    /**
     * Query templates for given id from database
     * @return template
     */
    @Query("SELECT * FROM templates WHERE id = :id")
    fun getTemplateById(id: Long): Flowable<TemplateEntity>

    /**
     * Query all challenges assigned to given template
     * @return list of challenges type
     */
    @Query("SELECT * FROM template_challenges WHERE templateId = :templateId")
    fun loadTemplateChallenges(templateId: Long): List<TemplateChallengesEntity>

    /**
     * Delate all challenges for give tempplate
     * @param templateId - given template id
     */
    @Query("DELETE FROM template_challenges WHERE templateId = :templateId")
    fun deleteAllChallengesForTemplate(templateId: Long)

    /**
     * Delete template for give template id
     * @param templateId - give template id
     */
    @Query("DELETE FROM templates WHERE id = :templateId")
    fun deleteTemplate(templateId: Long)
}