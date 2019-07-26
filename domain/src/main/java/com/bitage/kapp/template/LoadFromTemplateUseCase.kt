package com.bitage.kapp.template

import com.bitage.kapp.interactor.UseCaseCompletable
import com.bitage.kapp.model.Template
import com.bitage.kapp.repository.TemplateRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import java.util.Date

class LoadFromTemplateUseCase(
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler,
    private val repository: TemplateRepository
) : UseCaseCompletable<LoadFromTemplateUseCase.Params>(subscribeScheduler, observeScheduler) {
    override fun buildUseCase(params: Params): Completable {
        return repository.loadDataFromTemplate(params.template, params.date)
    }

    class Params(val template: Template, val date: Date)
}