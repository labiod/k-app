package com.kgb.kapp

import com.kgb.kapp.di.component.ApplicationComponent
import com.kgb.kapp.di.component.DaggerApplicationComponent
import com.kgb.kapp.di.module.RepositoryModule
import com.kgb.kapp.repository.ChallengesRepository
import com.kgb.kapp.repository.TemplateRepository

/**
 * Test application class
 */
class KApplicationTest : KApplication() {
    var repositoryModule = RepositoryModule(this)
    private val wrapperRepositoryModule = object : RepositoryModule(this) {
        override fun provideChallengesRepository(): ChallengesRepository {
            return repositoryModule.provideChallengesRepository()
        }

        override fun provideTemplateRepository(): TemplateRepository {
            return repositoryModule.provideTemplateRepository()
        }
    }

    var testComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun getComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .application(this)
                .plus(wrapperRepositoryModule)
                .build()
    }

    companion object {
        lateinit var instance: KApplicationTest
    }
}