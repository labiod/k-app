package com.bitage.kapp

import com.bitage.kapp.db.ChallengeDB
import com.bitage.kapp.di.ApplicationComponent
import com.bitage.kapp.di.DaggerApplicationComponent
import com.bitage.kapp.di.module.RepositoryModule
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.TemplateRepository

/**
 * Test application class
 */
class KApplicationTest : KApplication() {
    var repositoryModule = RepositoryModule(this)
    private val wrapperRepositoryModule = object : RepositoryModule(this) {
        override fun provideChallengesRepository(db: ChallengeDB): ChallengeRepository {
            return repositoryModule.provideChallengesRepository(db)
        }

        override fun provideTemplateRepository(db: ChallengeDB): TemplateRepository {
            return repositoryModule.provideTemplateRepository(db)
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