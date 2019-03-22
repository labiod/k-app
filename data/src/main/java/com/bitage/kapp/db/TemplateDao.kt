package com.bitage.kapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.bitage.kapp.entity.TemplateEntity
import com.bitage.kapp.db.entity.TemplateChallengesEntity

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
    suspend fun insertTemplate(templateEntity: TemplateEntity): Long

    /**
     * Insert challenges added to current template
     */
    @Insert
    suspend fun insertChallengeForTemplate(challengesForTemplate: TemplateChallengesEntity)

    /**
     * Query all templates from database
     * @return all templates
     */
    @Query("SELECT * FROM templates")
    suspend fun getAll(): List<TemplateEntity>

    /**
     * Query templates for given id from database
     * @return template
     */
    @Query("SELECT * FROM templates WHERE id = :id")
    suspend fun getTemplateById(id: Long): TemplateEntity

    /**
     * Query all challenges assigned to given template
     * @return list of challenges type
     */
    @Query("SELECT * FROM template_challenges WHERE templateId = :templateId")
    suspend fun loadTemplateChallenges(templateId: Long): List<TemplateChallengesEntity>

    /**
     * Delate all challenges for give tempplate
     * @param templateId - given template id
     */
    @Query("DELETE FROM template_challenges WHERE templateId = :templateId")
    suspend fun deleteAllChallengesForTemplate(templateId: Long)

    /**
     * Delete template for give template id
     * @param templateId - give template id
     */
    @Query("DELETE FROM templates WHERE id = :templateId")
    suspend fun deleteTemplate(templateId: Long)
}