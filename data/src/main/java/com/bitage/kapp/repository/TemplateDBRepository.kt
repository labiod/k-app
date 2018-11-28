package com.bitage.kapp.repository

import com.bitage.kapp.mapper.EntityMapper
import com.bitage.kapp.db.ChallengeDB
import com.bitage.kapp.db.entity.TemplateChallengesEntity
import com.bitage.kapp.entity.ChallengeEntity
import com.bitage.kapp.entity.UserProgressEntity
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.Template
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.Date
import java.util.concurrent.Executors

/**
 * Repository that used [ChallengeDB] to retrieve and manipulate template data
 * This class have private constructor and will be create only by [getInstance] method
 */
class TemplateDBRepository(private val db: ChallengeDB) : TemplateRepository {
    private val executor = Executors.newSingleThreadExecutor()

    /**
     * Insert template to database
     * @param template - template to be insert
     * @param callback - listener
     */
    override fun insertTemplate(template: Template): Completable {
        return Completable.create { c ->
            try {
                template.id?.let {
                    db.templateDao().deleteAllChallengesForTemplate(it)
                }
                val id = db.templateDao().insertTemplate(EntityMapper.mapToTemplateEntity(template))
                template.challenges.forEach {
                    db.templateDao().insertChallengeForTemplate(TemplateChallengesEntity(null, id, it))
                }
                c.onComplete()
            } catch (ex: IOException) {
                c.onError(ex)
            }
        }.subscribeOn(Schedulers.io())
    }

    /**
     * Get list of templates from database
     * @return list ofr templates
     */
    override fun getTemplates(): Flowable<List<Template>> {
        val result: Flowable<List<Template>> = Flowable.create({ e ->
            db.templateDao().getAll()
                .subscribe { next ->
                    e.onNext(EntityMapper.mapToTemplateList(next))
                }

        }, BackpressureStrategy.LATEST)
        return result.subscribeOn(Schedulers.io())
    }

    /**
     * Load data from given template and save them to challenges with given date
     * @param templateEntity - load template
     * @param date - given date
     */
    override fun loadDataFromTemplate(template: Template, date: Date) {
        template.id?.let {
            executor.execute {
                val challengesType = db.templateDao().loadTemplateChallenges(it)
                val challengesList = ArrayList<ChallengeEntity>()
                for (type in challengesType) {
                    val challengeProgress = db.userDao().getChallengeProgress(type.challengeType)
                        ?: createUserProgress(type.challengeType)
                    challengesList.add(ChallengeEntity(challengeProgress, date))
                }
                db.noteDao().insertAll(challengesList)
            }
        }
    }

    override fun getTemplateById(id: Long): Flowable<Template> {
        return Flowable.create({ e ->
            db.templateDao().getTemplateById(id)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    val next = EntityMapper.mapToTemplate(it)
                    val challenges = db.templateDao().loadTemplateChallenges(id)
                    challenges.forEach { ch ->
                        next.challenges.add(ch.challengeType)
                    }
                    e.onNext(next)
                }
        }, BackpressureStrategy.LATEST)
    }

    override fun deleteTemplate(template: Template): Completable {
        return Completable.create { c ->
            template.id?.let { id ->
                db.templateDao().deleteAllChallengesForTemplate(id)
                db.templateDao().deleteTemplate(id)
            }
        }
    }

    private fun createUserProgress(challengeType: ChallengeType): UserProgressEntity {
        val result = UserProgressEntity.createNew(challengeType)
        db.userDao().update(result)
        return result
    }
}