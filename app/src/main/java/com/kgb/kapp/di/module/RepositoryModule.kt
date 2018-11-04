package com.kgb.kapp.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.bitage.kapp.repository.DBChallengesRepository
import com.bitage.kapp.repository.TemplateDBRepository
import com.bitage.kapp.db.ChallengeDB
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.TemplateRepository
import com.kgb.kapp.db.ChallengeRoomDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule(private val application: Application) {

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


    @Provides
    @Singleton
    open fun provideChallengesRepository(db: ChallengeDB): ChallengeRepository {
        return DBChallengesRepository(db)
    }

    @Provides
    @Singleton
    open fun provideTemplateRepository(db: ChallengeDB): TemplateRepository {
        return TemplateDBRepository(db)
    }
}