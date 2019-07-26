package com.bitage.kapp.template

import com.bitage.kapp.interactor.ParamLessUseCaseFlowable
import com.bitage.kapp.model.Template
import com.bitage.kapp.repository.TemplateRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetTemplateListUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: TemplateRepository
) : ParamLessUseCaseFlowable<List<Template>>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(): Flowable<List<Template>> {
        return repository.getTemplates()
    }
}