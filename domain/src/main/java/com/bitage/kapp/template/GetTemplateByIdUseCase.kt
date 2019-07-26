package com.bitage.kapp.template

import com.bitage.kapp.interactor.UseCaseFlowable
import com.bitage.kapp.model.Template
import com.bitage.kapp.repository.TemplateRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetTemplateByIdUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: TemplateRepository
) : UseCaseFlowable<GetTemplateByIdUseCase.Params, Template>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(params: Params): Flowable<Template> {
        return repository.getTemplateById(params.templateId)
    }

    class Params(val templateId: Long)
}