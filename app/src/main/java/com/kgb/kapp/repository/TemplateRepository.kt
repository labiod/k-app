package com.kgb.kapp.repository

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.kgb.kapp.OnExecuteListener
import com.kgb.kapp.db.ChallengeRoomDB
import com.kgb.kapp.db.entity.TemplateChallengesEntity
import com.kgb.kapp.db.entity.TemplateEntity
import java.util.concurrent.Executors

/**
 * Repository that used [ChallengeRoomDB] to retrieve and manipulate template data
 * This class have private constructor and will be create only by [getInstance] method
 */
class TemplateRepository private constructor(context: Context) {
    private val db = ChallengeRoomDB.getInstance(context)
    private val executor = Executors.newSingleThreadExecutor()

    /**
     * Insert template to database
     * @param template - template to be insert
     * @param callback - listener
     */
    fun insertTemplate(template: TemplateEntity, callback: OnExecuteListener) {
        executor.execute {
            try {
                val id = db.templateDao().insertTemplate(template)
                template.challenges.forEach {
                    db.templateDao().insertChallengeForTemplate(TemplateChallengesEntity(null, id, it))
                }
                callback.onSucessed()
            } catch (ex: SQLiteConstraintException) {
                callback.onFailer(ex)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: TemplateRepository? = null
        private val lock = Any()

        /**
         * Get instance of [TemplateRepository] if instance is null, create new one
         * @param context - context object
         * @return instance of [TemplateRepository]
         */
        fun getInstance(context: Context): TemplateRepository {
            return instance ?: createSync(context)
        }

        private fun createSync(context: Context): TemplateRepository {
            synchronized(lock) {
                return instance ?: run {
                    val result = TemplateRepository(context)
                    instance = result
                    result
                }
            }
        }
    }
}