package com.bitage.kapp.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.bitage.kapp.repository.DBChallengesRepository
import com.bitage.kapp.repository.TemplateDBRepository
import com.bitage.kapp.db.ChallengeDB
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.TemplateRepository
import com.bitage.kapp.db.ChallengeRoomDB
import dagger.Module
import dagger.Provides
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
        return Room.databaseBuilder(
            application,
            ChallengeRoomDB::class.java,
            ChallengeRoomDB.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
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
    open fun provideTemplateRepository(db: ChallengeDB): TemplateRepository = TemplateDBRepository(db)
}