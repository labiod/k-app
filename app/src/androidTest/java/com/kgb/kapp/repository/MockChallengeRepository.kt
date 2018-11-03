package com.kgb.kapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.StepProgress
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.db.entity.UserProgressEntity
import java.util.Date
import kotlin.collections.ArrayList

/**
 * Mock class for [ChallengesRepository] to test view model
 */
class MockChallengeRepository : ChallengesRepository {
    private val challenges = ArrayList<ChallengeEntity>()
    private val liveData = MutableLiveData<List<ChallengeEntity>>()

    init {
        challenges.add(ChallengeEntity(1, ChallengeType.BRIDGE, 1, StepProgress.BEGINNER, Date(), 1, 10))
        liveData.postValue(challenges)
    }

    override fun getChallengeById(noteId: Long): ChallengeEntity? {
        return challenges.first { it.id == noteId }
    }

    override fun update(challengeEntity: ChallengeEntity) {
        // do nothing for update
    }

    override fun getChallengeProgress(challengeType: ChallengeType): UserProgressEntity? {
        return UserProgressEntity.createNew(challengeType)
    }

    override fun getDayChallenges(date: Date): LiveData<List<ChallengeEntity>> {
        return liveData
    }

    override fun getTemplates(): LiveData<List<TemplateEntity>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteChallenge(challenge: ChallengeEntity) {
        // do nothig for delete
    }

    override fun loadDataFromTemplate(templateEntity: TemplateEntity, date: Date) {
        // do nothing for loading data from template
    }

    override fun updateUserProgress(challenge: ChallengeEntity) {
        // do nothing for updateUsereProgress
    }

    override fun deleteAll() {
        // do nothing for deleteAll
    }
}