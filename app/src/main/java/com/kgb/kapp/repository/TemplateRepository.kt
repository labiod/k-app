package com.kgb.kapp.repository

import android.content.Context
import com.kgb.kapp.OnExecuteListener
import com.kgb.kapp.db.ChallengeRoomDB
import com.kgb.kapp.db.entity.TemplateChallengesEntity
import com.kgb.kapp.db.entity.TemplateEntity
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.Executors

class TemplateRepository private constructor(context: Context) {
    val db = ChallengeRoomDB.getInstance(context)
    val executor = Executors.newSingleThreadExecutor()

    fun addTemplate(template: TemplateEntity, callback: OnExecuteListener) {
        executor.execute {
            try {
                val id = db.templateDao().insertTemplate(template)
                template.challenges.forEach {
                    db.templateDao().insertChallengeForTemplate(TemplateChallengesEntity(null, id, it))
                }
                callback.onSucessed()
            } catch (ex: Exception) {
                callback.onFailer(ex)
            }

        }
    }

    companion object {
        @Volatile
        var instance: TemplateRepository? = null
        val lock = Any()

        fun getInstance(context: Context): TemplateRepository {
            if (instance == null) {
                synchronized(lock) {
                    if (instance == null) {
                        instance = TemplateRepository(context)
                    }
                }
            }
            return instance!!
        }
    }
}