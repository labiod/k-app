package com.bitage.kapp.di

import com.bitage.kapp.challenge.GetChallengeByIdUseCase
import com.bitage.kapp.challenge.GetChallengeListUseCase
import com.bitage.kapp.challenge.GetDefaultChallengeTypeValueUseCase
import com.bitage.kapp.challenge.RemoveAllChallengeUseCase
import com.bitage.kapp.challenge.RemoveChallengeUseCase
import com.bitage.kapp.challenge.SetChallengeUseCase
import com.bitage.kapp.repository.ChallengeRepository
import com.bitage.kapp.repository.TemplateRepository
import com.bitage.kapp.repository.UserRepository
import com.bitage.kapp.template.GetTemplateByIdUseCase
import com.bitage.kapp.template.GetTemplateListUseCase
import com.bitage.kapp.template.LoadFromTemplateUseCase
import com.bitage.kapp.template.RemoveTemplateUseCase
import com.bitage.kapp.template.SetTemplateUseCase
import com.bitage.kapp.user.CheckUserInfoUseCase
import com.bitage.kapp.user.GetUserInfoUseCase
import com.bitage.kapp.user.SetUserInfoUseCase
import com.bitage.kapp.user.UpdateUserProgressUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class DataModule {
    @Provides
    fun provideGetUserInfoUseCase(userRepository: UserRepository): GetUserInfoUseCase {
        return GetUserInfoUseCase(userRepository, AndroidSchedulers.mainThread(), Schedulers.io())
    }

    @Provides
    fun provideSetUserInfoUseCase(userRepository: UserRepository): SetUserInfoUseCase {
        return SetUserInfoUseCase(userRepository, AndroidSchedulers.mainThread(), Schedulers.io())
    }

    @Provides
    fun provideCheckUserInfoUseCase(userRepository: UserRepository): CheckUserInfoUseCase {
        return CheckUserInfoUseCase(userRepository, AndroidSchedulers.mainThread(), Schedulers.io())
    }

    @Provides
    fun provideGetChallengeByIdUseCase(challengeRepository: ChallengeRepository): GetChallengeByIdUseCase {
        return GetChallengeByIdUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), challengeRepository)
    }

    @Provides
    fun provideGetChallengeListUseCase(challengeRepository: ChallengeRepository): GetChallengeListUseCase {
        return GetChallengeListUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), challengeRepository)
    }

    @Provides
    fun provideSetChallengeUseCase(challengeRepository: ChallengeRepository): SetChallengeUseCase {
        return SetChallengeUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), challengeRepository)
    }

    @Provides
    fun provideUpdateUserProgressUseCase(challengeRepository: ChallengeRepository): UpdateUserProgressUseCase {
        return UpdateUserProgressUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), challengeRepository)
    }

    @Provides
    fun provideRemoveChallengeUseCase(challengeRepository: ChallengeRepository): RemoveChallengeUseCase {
        return RemoveChallengeUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), challengeRepository)
    }

    @Provides
    fun provideRemoveAllChallengeUseCase(challengeRepository: ChallengeRepository): RemoveAllChallengeUseCase {
        return RemoveAllChallengeUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), challengeRepository)
    }

    @Provides
    fun provideGetDefaultChallengeTypeValueUseCase(challengeRepository: ChallengeRepository): GetDefaultChallengeTypeValueUseCase {
        return GetDefaultChallengeTypeValueUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), challengeRepository)
    }

    @Provides
    fun provideGetTemplateByIdUseCase(templateRepository: TemplateRepository): GetTemplateByIdUseCase {
        return GetTemplateByIdUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), templateRepository)
    }

    @Provides
    fun provideGetTemplateListUseCase(templateRepository: TemplateRepository): GetTemplateListUseCase {
        return GetTemplateListUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), templateRepository)
    }

    @Provides
    fun provideSetTemplateUseCase(templateRepository: TemplateRepository): SetTemplateUseCase {
        return SetTemplateUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), templateRepository)
    }

    @Provides
    fun provideRemoveTemplateUseCase(templateRepository: TemplateRepository): RemoveTemplateUseCase {
        return RemoveTemplateUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), templateRepository)
    }

    @Provides
    fun provideLoadFromTemplateUseCase(templateRepository: TemplateRepository): LoadFromTemplateUseCase {
        return LoadFromTemplateUseCase(AndroidSchedulers.mainThread(), Schedulers.io(), templateRepository)
    }
}