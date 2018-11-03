package com.kgb.kapp.di.module

import android.app.Application
import com.kgb.kapp.repository.ChallengesRepository
import com.kgb.kapp.repository.DBChallengesRepository
import com.kgb.kapp.repository.TemplateRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule(private val application: Application) {
    @Provides
    @Singleton
    open fun provideChallengesRepository(): ChallengesRepository {
        return DBChallengesRepository.getInstance(application)
    }

    @Provides
    @Singleton
    open fun provideTemplateRepository(): TemplateRepository {
        return TemplateRepository.getInstance(application)
    }
}