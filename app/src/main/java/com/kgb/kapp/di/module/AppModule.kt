package com.kgb.kapp.di.module

import android.content.Context
import com.kgb.kapp.KApplication
import com.kgb.kapp.di.component.DayChallengesActivityComponent
import com.kgb.kapp.di.component.EditChallengeActivityComponent
import com.kgb.kapp.di.component.TemplateActivityComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
    DayChallengesActivityComponent::class,
    EditChallengeActivityComponent::class,
    TemplateActivityComponent::class
])
class AppModule {
    @Provides
    @Singleton
    fun provideApplication(app: KApplication): Context = app
}