package com.bitage.kapp.di.module

import android.app.Application
import com.bitage.kapp.repository.DBChallengesRepository
import com.bitage.kapp.repository.TemplateDBRepository
import com.bitage.kapp.db.ChallengeDB
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.TemplateRepository
import com.bitage.kapp.db.ChallengeRoomDB
import com.bitage.kapp.repository.UserDBRepository
import com.bitage.kapp.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Dagger module which provide challenge and template repositories for application
 */
@Module
open class RepositoryModule(private val application: Application) {

    /**
     * Provide channel database
     * @return implementation of [ChallengeDB]
     */
    @Provides
    @Singleton
    open fun provideChallengeDB(): ChallengeDB {
        return ChallengeRoomDB.getInstance(application)
    }

    /**
     * Provide test channel database
     * @return implementation of [ChallengeDB]
     */
    @Provides
    @Singleton
    @Named("TestChallengeDB")
    open fun provideTestChallengeDB(): ChallengeDB {
        return ChallengeRoomDB.getInstance(application)
    }

    /**
     * Provide challenge repository instance
     * @param db - challenge data base
     * @return implementation of [ChallengeRepository]
     */
    @Provides
    @Singleton
    open fun provideChallengesRepository(db: ChallengeDB): ChallengeRepository = DBChallengesRepository(db)

    /**
     * Provide template repository instance
     * @param db - challenge data base
     * @return implementation of [TemplateRepository]
     */
    @Provides
    @Singleton
    open fun provideTemplateRepository(@Named("TestChallengeDB") db: ChallengeDB): TemplateRepository = TemplateDBRepository(db)

    /**
     * Provide user repository instance
     * @return implementation of [TemplateRepository]
     */
    @Provides
    @Singleton
    open fun provideUserRepository(@Named("TestChallengeDB") db: ChallengeDB): UserRepository = UserDBRepository(db)
}