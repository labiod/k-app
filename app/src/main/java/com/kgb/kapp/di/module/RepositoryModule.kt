package com.kgb.kapp.di.module

import android.app.Application
import com.kgb.kapp.repository.ChallengesRepository
import com.kgb.kapp.repository.TemplateRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideChallengesRepository(): ChallengesRepository {
        return ChallengesRepository.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideTemplateRepository(): TemplateRepository {
        return TemplateRepository.getInstance(application)
    }
}