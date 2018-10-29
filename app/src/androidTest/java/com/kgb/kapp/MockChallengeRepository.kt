package com.kgb.kapp

import android.app.Application
import android.arch.lifecycle.LiveData
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.db.entity.UserProgressEntity
import com.kgb.kapp.repository.ChallengesRepository
import java.util.*
import kotlin.collections.ArrayList

class MockChallengeRepository() : ChallengesRepository {
    private val challengeEntity = ArrayList<ChallengeEntity>()

    init {
        challengeEntity.add(ChallengeEntity(1, ChallengeType.BRIDGE, 1, StepProgress.BEGINNER, Date(), 1, 10))
    }

    override fun getChallengeById(noteId: Long): ChallengeEntity? {
        return challengeEntity.first { it.id == noteId }
    }

    override fun update(challengeEntity: ChallengeEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChallengeProgress(challengeType: ChallengeType): UserProgressEntity? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDayChallenges(date: Date): LiveData<List<ChallengeEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTemplates(): LiveData<List<TemplateEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteChallenge(item: ChallengeEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDataFromTemplate(templateEntity: TemplateEntity, date: Date) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserProgress(item: ChallengeEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}