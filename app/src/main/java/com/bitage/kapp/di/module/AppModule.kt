package com.bitage.kapp.di.module

import android.content.Context
import com.bitage.kapp.KApplication
import com.bitage.kapp.daychallenges.di.DayChallengesActivityComponent
import com.bitage.kapp.editchallenge.di.EditChallengeActivityComponent
import com.bitage.kapp.home.di.HomeActivityComponent
import com.bitage.kapp.template.di.TemplateActivityComponent
import com.bitage.kapp.templatelist.di.TemplateListActivityComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger application module that provides objects which will live during the application lifecycle.
 */
@Module(subcomponents = [
    DayChallengesActivityComponent::class,
    EditChallengeActivityComponent::class,
    TemplateActivityComponent::class,
    HomeActivityComponent::class,
    TemplateListActivityComponent::class
])
class AppModule {
    /**
     * provide application context
     * @param app - application instance
     * @return application context instance
     */
    @Provides
    @Singleton
    fun provideApplication(app: KApplication): Context = app
}