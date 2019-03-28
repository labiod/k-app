package com.bitage.kapp.repository

import com.bitage.kapp.db.ChallengeDB
import com.bitage.kapp.mapper.EntityMapperDsl
import com.bitage.kapp.model.UserInfo
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDBRepository(private val challengeDB: ChallengeDB) : UserRepository {
    private var isUserSetup = false

    override fun setupUser(userInfo: UserInfo): Completable {
        return Completable.create {
            isUserSetup = true
            GlobalScope.launch {
                challengeDB.userDao().insertUserInfo(EntityMapperDsl.mapListToUserInfoEntities(userInfo))
                it.onComplete()
            }
        }
    }

    override fun isUserSetup(): Single<Boolean> {
        return Single.create<Boolean> {
            it.onSuccess(isUserSetup)
        }
    }

    override fun getUserInfo(): Flowable<UserInfo> {
        return Flowable.create( { e ->
            GlobalScope.launch {
                val userInfo = challengeDB.userDao().getUserInfo()
                e.onNext(EntityMapperDsl.mapListOfUserInfoEntityToUserInfo(userInfo))
            }
        }, BackpressureStrategy.LATEST)
    }
}