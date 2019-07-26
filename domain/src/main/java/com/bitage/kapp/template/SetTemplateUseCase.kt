package com.bitage.kapp.template

import com.bitage.kapp.interactor.UseCaseCompletable
import com.bitage.kapp.model.Template
import com.bitage.kapp.repository.TemplateRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class SetTemplateUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: TemplateRepository
) : UseCaseCompletable<SetTemplateUseCase.Params>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(params: Params): Completable {
        return repository.insertTemplate(params.template)
    }

    class Params(val template: Template)
}